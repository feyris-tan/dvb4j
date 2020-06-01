package moe.yo3explorer.dvb4j.model.extendedDescriptors63;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class HevcTimingAndHrdDescriptor implements Descriptor {
    private boolean hdrManagementValid;
    private boolean targetScheduleIdxNotPresent;
    private int targetScheduleIdx;
    private boolean pictureAndTimingInfoPresent;
    private boolean _90khz;
    private long n;
    private long k;
    private long numUnitsInTick;

    @Override
    public int getTag() {
        return 3;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        byte flags = wrap.get();
        hdrManagementValid = (flags & 0x80) != 0;
        targetScheduleIdxNotPresent = (flags & 0x40) != 0;
        targetScheduleIdx = (flags & 0x3E) >> 1;
        pictureAndTimingInfoPresent = (flags & 0x01) != 0;
        if (pictureAndTimingInfoPresent)
        {
            flags = wrap.get();
            _90khz = (flags & 0x80) != 0;
            if (!_90khz)
            {
                n = wrap.getInt() & 0xFFFFFFFFL;
                k = wrap.getInt() & 0xFFFFFFFFL;
            }
            numUnitsInTick = wrap.getInt() & 0xFFFFFFFFL;
        }
    }

    @Override
    public String toString() {
        return "HevcTimingAndHrdDescriptor{" +
                "hdrManagementValid=" + hdrManagementValid +
                ", targetScheduleIdxNotPresent=" + targetScheduleIdxNotPresent +
                ", targetScheduleIdx=" + targetScheduleIdx +
                ", pictureAndTimingInfoPresent=" + pictureAndTimingInfoPresent +
                '}';
    }

    public boolean isHdrManagementValid() {
        return hdrManagementValid;
    }

    public boolean isTargetScheduleIdxNotPresent() {
        return targetScheduleIdxNotPresent;
    }

    public int getTargetScheduleIdx() {
        return targetScheduleIdx;
    }

    public boolean isPictureAndTimingInfoPresent() {
        return pictureAndTimingInfoPresent;
    }

    public boolean is_90khz() {
        return _90khz;
    }

    public long getN() {
        return n;
    }

    public long getK() {
        return k;
    }

    public long getNumUnitsInTick() {
        return numUnitsInTick;
    }
}
