package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public class MetadataStdDescriptor implements Descriptor {
    private int metadataInputLeakRate;
    private int metadataBufferSize;
    private int metadataOutputLeakRate;

    @Override
    public int getTag() {
        return 39;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        metadataInputLeakRate = readInt24(wrap) & 0x3fffff;
        metadataBufferSize = readInt24(wrap) & 0x3fffff;
        metadataOutputLeakRate = readInt24(wrap) & 0x3fffff;
    }

    private int readInt24(@NotNull ByteBuffer wrap)
    {
        int result = 0;
        result += (wrap.get() & 0xff) << 16;
        result += (wrap.getShort() & 0xffff);
        return result;
    }

    public int getMetadataInputLeakRate() {
        return metadataInputLeakRate;
    }

    public int getMetadataBufferSize() {
        return metadataBufferSize;
    }

    public int getMetadataOutputLeakRate() {
        return metadataOutputLeakRate;
    }

    @Override
    public String toString() {
        return "MetadataStdDescriptor{" +
                "metadataInputLeakRate=" + metadataInputLeakRate +
                ", metadataBufferSize=" + metadataBufferSize +
                ", metadataOutputLeakRate=" + metadataOutputLeakRate +
                '}';
    }
}
