package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class MultiplexBufferUtilizationDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 12;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        short flags1 = wrap.getShort();
        boundValidFlag = (flags1 & 0x8000) != 0;
        ltwOffsetLowerBound = flags1 & 0x7fff;
        ltwOffsetUpperBound = (int)wrap.getShort() & 0x7fff;
    }

    private boolean boundValidFlag;
    private int ltwOffsetLowerBound;
    private int ltwOffsetUpperBound;

    public boolean isBoundValidFlag() {
        return boundValidFlag;
    }

    public void setBoundValidFlag(boolean boundValidFlag) {
        this.boundValidFlag = boundValidFlag;
    }

    public int getLtwOffsetLowerBound() {
        return ltwOffsetLowerBound;
    }

    public void setLtwOffsetLowerBound(int ltwOffsetLowerBound) {
        this.ltwOffsetLowerBound = ltwOffsetLowerBound;
    }

    public int getLtwOffsetUpperBound() {
        return ltwOffsetUpperBound;
    }

    public void setLtwOffsetUpperBound(int ltwOffsetUpperBound) {
        this.ltwOffsetUpperBound = ltwOffsetUpperBound;
    }
}
