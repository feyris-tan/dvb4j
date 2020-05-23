package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class CaIdentifierDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x53;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        caIdentifiers = new int[buffer.length / 2];
        for (int i = 0; i < caIdentifiers.length; i++)
        {
            caIdentifiers[0] = wrap.getShort() & 0xffff;
        }
    }

    private int[] caIdentifiers;
}
