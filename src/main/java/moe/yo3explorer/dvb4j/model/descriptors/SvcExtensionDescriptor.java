package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class SvcExtensionDescriptor implements Descriptor {
    private int width;
    private int height;
    private int frameRate;
    private int averageBitrate;
    private int minimumBitrate;
    private int dependencyId;
    private int qualityIdStart;
    private int qualityIdEnd;
    private int temporalIdStart;
    private int temporalIdEnd;
    private boolean noSeiNalUnit;

    @Override
    public int getTag() {
        return 48;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        width = wrap.getShort() & 0xffff;
        height = wrap.getShort() & 0xffff;
        frameRate = wrap.getShort() & 0xffff;
        averageBitrate = wrap.getShort() & 0xffff;
        minimumBitrate = wrap.getShort() & 0xffff;
        dependencyId = (wrap.get() & 0xe0) >> 5;

        byte flags = wrap.get();
        qualityIdStart = flags & 0xf0 >> 4;
        qualityIdEnd = flags & 0x0f;

        flags = wrap.get();
        temporalIdStart = flags & 0xe0;
        temporalIdEnd = flags & 0x1c;
        noSeiNalUnit = (flags & 0x02) != 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public int getAverageBitrate() {
        return averageBitrate;
    }

    public int getMinimumBitrate() {
        return minimumBitrate;
    }

    public int getDependencyId() {
        return dependencyId;
    }

    public int getQualityIdStart() {
        return qualityIdStart;
    }

    public int getQualityIdEnd() {
        return qualityIdEnd;
    }

    public int getTemporalIdStart() {
        return temporalIdStart;
    }

    public int getTemporalIdEnd() {
        return temporalIdEnd;
    }

    public boolean isNoSeiNalUnit() {
        return noSeiNalUnit;
    }

    @Override
    public String toString() {
        return "SvcExtensionDescriptor{" +
                "width=" + width +
                ", height=" + height +
                ", frameRate=" + frameRate +
                ", averageBitrate=" + averageBitrate +
                ", minimumBitrate=" + minimumBitrate +
                ", dependencyId=" + dependencyId +
                ", qualityIdStart=" + qualityIdStart +
                ", qualityIdEnd=" + qualityIdEnd +
                ", temporalIdStart=" + temporalIdStart +
                ", temporalIdEnd=" + temporalIdEnd +
                ", noSeiNalUnit=" + noSeiNalUnit +
                '}';
    }
}
