package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public class SmoothingBufferDescriptor implements Descriptor {
    private int leakRate;
    private int size;

    public int getLeakRate() {
        return leakRate;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int getTag() {
        return 16;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        leakRate = read2_22Integer(wrap);
        size = read2_22Integer(wrap);
    }

    private int read2_22Integer(@NotNull ByteBuffer byteBuffer)
    {
        byte[] buffer = new byte[3];
        byteBuffer.get(buffer);
        byte[] altBuffer = new byte[4];
        System.arraycopy(buffer,0,altBuffer,1,3);
        ByteBuffer wrap = ByteBuffer.wrap(altBuffer);
        int units = wrap.getInt() & 0x3FFFFF;
        return units;
    }

    @Override
    public String toString() {
        return "SmoothingBufferDescriptor{" +
                "leakRate=" + leakRate +
                ", size=" + size +
                '}';
    }
}
