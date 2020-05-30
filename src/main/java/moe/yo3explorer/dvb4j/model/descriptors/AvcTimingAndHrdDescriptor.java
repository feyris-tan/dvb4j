package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class AvcTimingAndHrdDescriptor implements Descriptor {
    private boolean hrdManagementValid;
    private boolean pictureAndTimingInfoPresent;
    private boolean _90khzFlags;
    private long n;
    private long k;
    private long numUnitsInTick;
    private boolean fixedFrameRate;
    private boolean temporalPoc;
    private boolean pictureToDisplayConversion;

    @Override
    public int getTag() {
        return 42;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        byte flags = wrap.get();
        hrdManagementValid = (flags & 0x80) != 0;
        pictureAndTimingInfoPresent = (flags & 0x01) != 0;
        if (pictureAndTimingInfoPresent)
        {
            flags = wrap.get();
            _90khzFlags = (flags & 0x80) != 0;
            if (!_90khzFlags)
            {
                n = wrap.getInt() & 0xFFFFFFFFL;
                k = wrap.getInt() & 0xFFFFFFFFL;
            }
            numUnitsInTick = wrap.getInt() & 0xFFFFFFFFL;
        }

        flags = wrap.get();
        fixedFrameRate = (flags & 0x80) != 0;
        temporalPoc = (flags & 0x40) != 0;
        pictureToDisplayConversion = (flags & 0x20) != 0;
    }

    public boolean isHrdManagementValid() {
        return hrdManagementValid;
    }

    public boolean isPictureAndTimingInfoPresent() {
        return pictureAndTimingInfoPresent;
    }

    public boolean is_90khzFlags() {
        return _90khzFlags;
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

    public boolean isFixedFrameRate() {
        return fixedFrameRate;
    }

    public boolean isTemporalPoc() {
        return temporalPoc;
    }

    public boolean isPictureToDisplayConversion() {
        return pictureToDisplayConversion;
    }

    @Override
    public String toString() {
        return "AvcTimingAndHrdDescriptor{" +
                "hrdManagementValid=" + hrdManagementValid +
                ", pictureAndTimingInfoPresent=" + pictureAndTimingInfoPresent +
                ", fixedFrameRate=" + fixedFrameRate +
                ", temporalPoc=" + temporalPoc +
                ", pictureToDisplayConversion=" + pictureToDisplayConversion +
                '}';
    }
}
