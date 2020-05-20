package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbException;
import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class MaximumBitrateDescriptor implements Descriptor
{
    private long value;

    public long getValue() {
        return value;
    }

    @Override
    public int getTag() {
        return 14;
    }

    @Override
    public void readFrom(byte[] buffer) {
        byte[] altBuffer = new byte[4];
        System.arraycopy(buffer,0,altBuffer,1,3);
        ByteBuffer wrap = ByteBuffer.wrap(altBuffer);
        int units = wrap.getInt() & 0x3FFFFF;
        this.value = (long)units * 50L;
    }

    @Override
    public String toString() {
        return "MaximumBitrateDescriptor{" +
                "value=" + value +
                '}';
    }
}
