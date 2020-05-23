package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class ExternalEsDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 32;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        esId = wrap.getShort() & 0xffff;
    }

    private int esId;

    public int getEsId() {
        return esId;
    }

    @Override
    public String toString() {
        return "ExternalEsDescriptor{" +
                "esId=" + esId +
                '}';
    }
}
