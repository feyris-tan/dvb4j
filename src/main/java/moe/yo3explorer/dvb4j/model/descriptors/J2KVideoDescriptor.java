package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class J2KVideoDescriptor implements Descriptor {
    private int profileAndLevel;
    private long horizontalSize;
    private long verticalSize;
    private long maxBitRate;
    private long maxBufferSize;
    private long denFrameRate;
    private long numFrameRate;
    private int colorSpecification;
    private boolean stillMode;
    private boolean interlacedVideo;

    @Override
    public int getTag() {
        return 50;
    }

    public int getProfileAndLevel() {
        return profileAndLevel;
    }

    public long getHorizontalSize() {
        return horizontalSize;
    }

    public long getVerticalSize() {
        return verticalSize;
    }

    public long getMaxBitRate() {
        return maxBitRate;
    }

    public long getMaxBufferSize() {
        return maxBufferSize;
    }

    public long getDenFrameRate() {
        return denFrameRate;
    }

    public long getNumFrameRate() {
        return numFrameRate;
    }

    public int getColorSpecification() {
        return colorSpecification;
    }

    public boolean isStillMode() {
        return stillMode;
    }

    public boolean isInterlacedVideo() {
        return interlacedVideo;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        profileAndLevel = wrap.getShort() & 0xffff;
        if (!(profileAndLevel >= 0x0101 && profileAndLevel <= 0x04ff))
        {
            //invalid level
            return;
        }
        horizontalSize = wrap.getInt() & 0xffffffffL;
        verticalSize = wrap.getInt() & 0xffffffffL;
        maxBitRate = wrap.getInt() & 0xffffffffL;
        maxBufferSize = wrap.getInt() & 0xffffffffL;
        double limit = maxBitRate / 1.60E5;
        if (maxBufferSize > limit)
        {
            //invalid buffer size;
            return;
        }
        denFrameRate = wrap.getShort() & 0xffff;
        numFrameRate = wrap.getShort() & 0xffff;
        colorSpecification = wrap.get() & 0xff;

        byte flags = wrap.get();
        stillMode = (flags & 0x80) != 0;
        interlacedVideo = (flags & 0x40) != 0;
    }

    @Override
    public String toString() {
        return "J2KVideoDescriptor{" +
                "profileAndLevel=" + profileAndLevel +
                ", horizontalSize=" + horizontalSize +
                ", verticalSize=" + verticalSize +
                ", maxBitRate=" + maxBitRate +
                ", maxBufferSize=" + maxBufferSize +
                ", denFrameRate=" + denFrameRate +
                ", numFrameRate=" + numFrameRate +
                ", colorSpecification=" + colorSpecification +
                ", stillMode=" + stillMode +
                ", interlacedVideo=" + interlacedVideo +
                '}';
    }
}
