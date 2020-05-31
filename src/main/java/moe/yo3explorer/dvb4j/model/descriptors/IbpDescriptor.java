package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class IbpDescriptor implements Descriptor {
    private int closedGopFlags;
    private int identicalGopFlags;
    private int maxGopLength;

    @Override
    public int getTag() {
        return 0x12;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        int flags = wrap.getShort() & 0xffff;
        closedGopFlags = flags & 0x8000;
        identicalGopFlags = flags & 0x4000;
        maxGopLength = flags & 0x3fff;
    }

    public int getClosedGopFlags() {
        return closedGopFlags;
    }

    public int getIdenticalGopFlags() {
        return identicalGopFlags;
    }

    public int getMaxGopLength() {
        return maxGopLength;
    }

    @Override
    public String toString() {
        return "IbpDescriptor{" +
                "closedGopFlags=" + closedGopFlags +
                ", identicalGopFlags=" + identicalGopFlags +
                ", maxGopLength=" + maxGopLength +
                '}';
    }
}
