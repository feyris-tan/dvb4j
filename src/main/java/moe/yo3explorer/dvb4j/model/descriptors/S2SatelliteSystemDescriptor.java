package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.TsGsMode;

import java.nio.ByteBuffer;

public class S2SatelliteSystemDescriptor implements Descriptor {
    private boolean scramblingSequenceSelector;
    private boolean multipleInputStreamFlag;
    private boolean notTimeSlice;
    private TsGsMode tsGsMode;
    private int scramblingSequenceIndex;
    private int inputStreamIdentifier;
    private int timesliceNumber;

    @Override
    public int getTag() {
        return 0x79;
    }

    @Override
    public void readFrom(byte[] buffer) {
        if (buffer.length == 0)
            return;

        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        byte flags = wrap.get();
        scramblingSequenceSelector = (flags & 0x80) != 0;

        multipleInputStreamFlag = (flags & 0x40) != 0;

        notTimeSlice = (flags & 0x10) != 0;

        int tsGsModeRaw = (flags & 0x03);
        tsGsMode = TsGsMode.values()[tsGsModeRaw];

        if (scramblingSequenceSelector)
        {
            ByteBuffer sssBuffer = ByteBuffer.allocate(4);
            sssBuffer.put(1,wrap.get());
            sssBuffer.putShort(2,wrap.getShort());
            sssBuffer.position(0);
            int sssRawInt = sssBuffer.getInt();
            scramblingSequenceIndex = sssRawInt & 0x003fffff;
        }

        if (multipleInputStreamFlag)
        {
            inputStreamIdentifier = wrap.get() & 0xff;
        }

        if (notTimeSlice)
        {
            timesliceNumber = wrap.get() & 0xff;
        }
    }

    public boolean isScramblingSequenceSelector() {
        return scramblingSequenceSelector;
    }

    public boolean isMultipleInputStreamFlag() {
        return multipleInputStreamFlag;
    }

    public boolean isNotTimeSlice() {
        return notTimeSlice;
    }

    public TsGsMode getTsGsMode() {
        return tsGsMode;
    }

    public int getScramblingSequenceIndex() {
        return scramblingSequenceIndex;
    }

    public int getInputStreamIdentifier() {
        return inputStreamIdentifier;
    }

    public int getTimesliceNumber() {
        return timesliceNumber;
    }

    @Override
    public String toString() {
        return "S2SatelliteSystemDescriptor{" +
                "tsGsMode=" + tsGsMode +
                ", scramblingSequenceIndex=" + scramblingSequenceIndex +
                ", inputStreamIdentifier=" + inputStreamIdentifier +
                ", timesliceNumber=" + timesliceNumber +
                '}';
    }
}
