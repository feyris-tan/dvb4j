package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.MpegCarriage;

import java.nio.ByteBuffer;

public class MetadataPointerDescriptor implements Descriptor {
    private int metadataApplicationFormat;
    private long metadataApplicationFormatIdentifier;
    private int metadataFormat;
    private long metadataFormatIdentifier;
    private int metadataServiceId;
    private boolean metadataLocatorRecord;
    private MpegCarriage mpegCarriage;
    private byte[] metadataLocatorRecordBytes;
    private int programNumber;
    private int transportStreamLocation;
    private int transportStreamId;

    @Override
    public int getTag() {
        return 37;
    }

    @Override
    public String toString() {
        return "MetadataPointerDescriptor{" +
                "metadataApplicationFormat=" + metadataApplicationFormat +
                ", metadataFormat=" + metadataFormat +
                ", metadataServiceId=" + metadataServiceId +
                ", metadataLocatorRecord=" + metadataLocatorRecord +
                ", mpegCarriage=" + mpegCarriage +
                '}';
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        metadataApplicationFormat = wrap.getShort() & 0xffff;
        if (metadataApplicationFormat == 0xffff)
            metadataApplicationFormatIdentifier = wrap.getInt() & 0xffffffffL;

        metadataFormat = wrap.get() & 0xff;
        if (metadataFormat == 0xff)
            metadataFormatIdentifier = wrap.getInt() & 0xffffffffL;

        metadataServiceId = wrap.get() & 0xff;

        byte flags = wrap.get();
        metadataLocatorRecord = (flags & 0x80) != 0;

        int mpegCarriageFlagsRaw = (flags & 0x60) >> 5;
        mpegCarriage = MpegCarriage.values()[mpegCarriageFlagsRaw];
        if (metadataLocatorRecord)
        {
            int metadataLocatorRecordLength = wrap.get() & 0xff;
            if (metadataLocatorRecordLength > (wrap.limit() - wrap.position()))
                return;
            metadataLocatorRecordBytes = new byte[metadataLocatorRecordLength];
            wrap.get(metadataLocatorRecordBytes);
        }

        if (mpegCarriage == MpegCarriage.PROGRAM_STREAM || mpegCarriage == MpegCarriage.NONE)
        {
            if (wrap.limit() == wrap.position())
                return;
            programNumber = wrap.get() & 0xffff;
        }

        if (mpegCarriage == MpegCarriage.DIFFERENT_TRANSPORT_STREAM)
        {
            transportStreamLocation = wrap.getShort() & 0xffff;
            transportStreamId = wrap.getShort() & 0xffff;
        }
    }

    public int getMetadataApplicationFormat() {
        return metadataApplicationFormat;
    }

    public long getMetadataApplicationFormatIdentifier() {
        return metadataApplicationFormatIdentifier;
    }

    public int getMetadataFormat() {
        return metadataFormat;
    }

    public long getMetadataFormatIdentifier() {
        return metadataFormatIdentifier;
    }

    public int getMetadataServiceId() {
        return metadataServiceId;
    }

    public boolean isMetadataLocatorRecord() {
        return metadataLocatorRecord;
    }

    public MpegCarriage getMpegCarriage() {
        return mpegCarriage;
    }

    public byte[] getMetadataLocatorRecordBytes() {
        return metadataLocatorRecordBytes;
    }

    public int getProgramNumber() {
        return programNumber;
    }

    public int getTransportStreamLocation() {
        return transportStreamLocation;
    }

    public int getTransportStreamId() {
        return transportStreamId;
    }
}
