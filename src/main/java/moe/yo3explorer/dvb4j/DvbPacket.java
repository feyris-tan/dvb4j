package moe.yo3explorer.dvb4j;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DvbPacket
{
    @Contract(pure = true)
    public DvbPacket(@NotNull byte[] rawBytes)
    {
        if (rawBytes.length != 188)
            throw new IllegalArgumentException("A DVB Packet should be 188-bytes long.");

        ByteBuffer buffer = ByteBuffer.wrap(rawBytes);
        buffer.order(ByteOrder.BIG_ENDIAN);

        int anInt = buffer.getInt();
        int syncBytes = (anInt & 0xff000000) >> 24;
        if (syncBytes != 'G')
        {
            throw new DvbException("invalid DVB packet!");
        }
        tei = ((anInt & 0x800000)) >> 23;
        payloadUnitStart = (((anInt & 0x400000)) >> 22) != 0;
        priority = ((anInt & 0x200000)) >> 21;
        pid = ((anInt & 0x1fff00)) >> 8;
        tsc = ((anInt & 0xc0)) >> 6;
        adaptionFieldControl = ((anInt & 0x30)) >> 4;
        continuity = ((anInt & 0xf));

        int payloadOffset = 4;
        if (payloadUnitStart) {
            payloadStartOffset = rawBytes[4] & 0xff;
            payloadOffset++;
        }

        switch (adaptionFieldControl)
        {
            case 1:
                payload = ByteBuffer.wrap(rawBytes,payloadOffset,188-payloadOffset);
                break;
            case 3:
                int adaptionSize = parseAdaption(buffer);
                adaptionSize += payloadOffset;
                payload = ByteBuffer.wrap(rawBytes,adaptionSize,188 - adaptionSize);
                break;
            case 2:
                parseAdaption(buffer);
                break;
            default:
                throw new RuntimeException("Invalid adaption!");
        }
    }

    private int parseAdaption(@NotNull ByteBuffer buffer)
    {
        DvbAdaptionField adaptionField = new DvbAdaptionField();
        adaptionField.length = buffer.get() & 0xff;
        if (adaptionField.length == 0)
            return 1;

        byte bitmask = buffer.get();
        adaptionField.discontinuity = (bitmask & 0x80) >> 7;
        adaptionField.randomAccess = (bitmask & 0x40) >> 6;
        adaptionField.priorityIndic = (bitmask & 0x20) >> 5;
        adaptionField.pcrPresent = (bitmask & 0x10) >> 4;
        adaptionField.opcrPresent = (bitmask & 0x08) >> 3;
        adaptionField.splicingPointPresent = (bitmask & 0x04) >> 2;
        adaptionField.transportPrivateDataPresent = (bitmask &0x02) >> 1;
        adaptionField.adaptionFieldExtensionPresent = (bitmask & 0x01);

        if (adaptionField.pcrPresent != 0)
        {
            int pcrA = buffer.getInt();
            short pcrB = buffer.getShort();
            adaptionField.pcr = (((long)pcrA) << 16) | (long)pcrB;
        }
        if (adaptionField.opcrPresent != 0)
        {
            int opcrA = buffer.getInt();
            short opcrB = buffer.getShort();
            long opcr = opcrA << 32;
            adaptionField.opcr = (((long)opcrA) << 16) | (long)opcrB;
        }
        if (adaptionField.splicingPointPresent != 0)
        {
            adaptionField.splicingPoint = buffer.get();
        }
        if (adaptionField.transportPrivateDataPresent != 0)
        {
            adaptionField.transportPrivateDataLength = buffer.get() & 0xff;
            adaptionField.transportPrivateData = new byte[adaptionField.transportPrivateDataLength];
            buffer.get(adaptionField.transportPrivateData);
        }
        if (adaptionField.adaptionFieldExtensionPresent != 0)
        {
            throw new RuntimeException();
        }

        dvbAdaptionField = adaptionField;

        return (adaptionField.length + 1);
    }

    private ByteBuffer payload;
    private int pid;
    private int continuity;
    private boolean payloadUnitStart;
    private int adaptionFieldControl;
    private int tei;
    private int priority;
    private int tsc;
    private int payloadStartOffset;
    private DvbAdaptionField dvbAdaptionField;

    public int getPid() {
        return pid;
    }

    public int getContinuity() {
        return continuity;
    }

    public boolean isPayloadUnitStart() {
        return payloadUnitStart;
    }

    public boolean isPayloadFlagSet() {
        return adaptionFieldControl == 1 || adaptionFieldControl == 3;
    }

    public DvbAdaptionField getDvbAdaptionField() {
        return dvbAdaptionField;
    }

    public ByteBuffer getPayload() {
        return payload.slice();
    }

    public int getPayloadStartOffset() {
        return payloadStartOffset;
    }
}

