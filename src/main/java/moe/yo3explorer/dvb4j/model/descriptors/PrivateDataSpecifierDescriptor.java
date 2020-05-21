package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class PrivateDataSpecifierDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x5f;
    }

    @Override
    public void readFrom(byte[] buffer) {
        int anInt = ByteBuffer.wrap(buffer).getInt();
        specifier = ((long)anInt) & 0xFFFFFFFFL;
    }

    private long specifier;

    public long getSpecifier() {
        return specifier;
    }

    @Override
    public String toString() {
        return String.format("PrivateDataSpecifier: %08X", specifier);
    }
}
