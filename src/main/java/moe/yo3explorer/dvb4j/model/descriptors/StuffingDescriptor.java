package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.model.Descriptor;

import java.util.Arrays;

public class StuffingDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x42;
    }

    @Override
    public void readFrom(byte[] buffer) {
        this.buffer = buffer;
    }

    private byte[] buffer;

    public byte[] getBuffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return "StuffingDescriptor{" +
                "buffer=" + DvbTimeConverter.bytesToHex(buffer) +
                '}';
    }
}
