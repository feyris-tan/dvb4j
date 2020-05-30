package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class HevcVideoDescriptor implements Descriptor {
    private int profileSpace;
    private boolean tierFlags;
    private int profileIdc;
    private int profileCompabilityIndication;
    private boolean progressiveSourceFlag;
    private boolean interlacedSourceFlag;
    private boolean nonPackedConstraintFlag;
    private boolean frameOnlyConstraintFlag;
    private long copied44bits;
    private int levelIdc;
    private boolean temporalLayerSubsetFlag;
    private boolean hevcStillPresentFlag;
    private boolean hevc24hourPicturePresentFlag;
    private boolean subPicHrdParamsNotPresentFlag;
    private int hdrWcgIdc;
    private int temporalIdMin;
    private int temporalIdMax;

    @Override
    public int getTag() {
        return 56;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        byte flags = wrap.get();
        profileSpace = (flags & 0xC0) >> 6;
        tierFlags = (flags & 0x20) != 0;
        profileIdc = (flags & 0x1F);
        profileCompabilityIndication = wrap.getInt();

        flags = wrap.get();
        progressiveSourceFlag = (flags & 0x80) != 0;
        interlacedSourceFlag = (flags & 0x40) != 0;
        nonPackedConstraintFlag = (flags & 0x20) != 0;
        frameOnlyConstraintFlag = (flags & 0x10) != 0;

        copied44bits = (long)(flags & 0x0f) << 40;
        copied44bits += ((long)(wrap.getInt() & 0xFFFFFFFFL) << 8);
        copied44bits += ((int)wrap.get() & 0xFF);

        levelIdc = wrap.get() & 0xff;

        flags = wrap.get();
        temporalLayerSubsetFlag = (flags & 0x80) != 0;
        hevcStillPresentFlag = (flags & 0x40) != 0;
        hevc24hourPicturePresentFlag = (flags & 0x20) != 0;
        subPicHrdParamsNotPresentFlag = (flags & 0x10) != 0;
        hdrWcgIdc = (flags & 0x03);

        if (temporalLayerSubsetFlag)
        {
            flags = wrap.get();
            temporalIdMin = (flags & 0xE0) >> 5;

            flags = wrap.get();
            temporalIdMax = (flags & 0xE0) >> 5;
        }
    }

    public int getProfileSpace() {
        return profileSpace;
    }

    public boolean isTierFlags() {
        return tierFlags;
    }

    public int getProfileIdc() {
        return profileIdc;
    }

    public int getProfileCompabilityIndication() {
        return profileCompabilityIndication;
    }

    public boolean isProgressiveSourceFlag() {
        return progressiveSourceFlag;
    }

    public boolean isInterlacedSourceFlag() {
        return interlacedSourceFlag;
    }

    public boolean isNonPackedConstraintFlag() {
        return nonPackedConstraintFlag;
    }

    public boolean isFrameOnlyConstraintFlag() {
        return frameOnlyConstraintFlag;
    }

    public long getCopied44bits() {
        return copied44bits;
    }

    public int getLevelIdc() {
        return levelIdc;
    }

    public boolean isTemporalLayerSubsetFlag() {
        return temporalLayerSubsetFlag;
    }

    public boolean isHevcStillPresentFlag() {
        return hevcStillPresentFlag;
    }

    public boolean isHevc24hourPicturePresentFlag() {
        return hevc24hourPicturePresentFlag;
    }

    public boolean isSubPicHrdParamsNotPresentFlag() {
        return subPicHrdParamsNotPresentFlag;
    }

    public int getHdrWcgIdc() {
        return hdrWcgIdc;
    }

    public int getTemporalIdMin() {
        return temporalIdMin;
    }

    public int getTemporalIdMax() {
        return temporalIdMax;
    }
}
