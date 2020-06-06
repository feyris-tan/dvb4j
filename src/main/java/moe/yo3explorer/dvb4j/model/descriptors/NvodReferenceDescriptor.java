package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptorEntities.NvodPointer;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class NvodReferenceDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x4B;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        nvodPointers = new NvodPointer[buffer.length / 6];
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        for (int i = 0; i < buffer.length; i += 6)
        {
            int transportStreamId = wrap.getShort() & 0xffff;
            int originalNetworkId = wrap.getShort() & 0xffff;
            int serviceId = wrap.getShort() & 0xffff;
            nvodPointers[i / 6] = new NvodPointer(transportStreamId,originalNetworkId,serviceId);
        }
    }

    private NvodPointer[] nvodPointers;

    public NvodPointer[] getNvodPointers() {
        return nvodPointers;
    }

    @Override
    public String toString() {
        return "NvodReferenceDescriptor{" +
                "nvodPointers=" + Arrays.toString(nvodPointers) +
                '}';
    }
}
