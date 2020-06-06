package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class FlexMuxTimingDescriptor implements Descriptor {
    private int fcrEsId;
    private long fcrResolution;
    private int fcrLength;
    private int fmxRateLength;

    @Override
    public int getTag() {
        return 44;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        fcrEsId = wrap.getShort() & 0xffff;
        fcrResolution = wrap.getInt() & 0xffffffffL;
        fcrLength = wrap.get() & 0xff;
        fmxRateLength = wrap.get() & 0xff;
    }

    public int getFcrEsId() {
        return fcrEsId;
    }

    public long getFcrResolution() {
        return fcrResolution;
    }

    public int getFcrLength() {
        return fcrLength;
    }

    public int getFmxRateLength() {
        return fmxRateLength;
    }

    @Override
    public String toString() {
        return "FlexMuxTimingDescriptor{" +
                "fcrEsId=" + fcrEsId +
                ", fcrResolution=" + fcrResolution +
                ", fcrLength=" + fcrLength +
                ", fmxRateLength=" + fmxRateLength +
                '}';
    }
}
