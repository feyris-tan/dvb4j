package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class SLDescriptor implements Descriptor {
    private int esId;

    @Override
    public int getTag() {
        return 30;
    }

    @Override
    public void readFrom(byte[] buffer) {
        esId = ByteBuffer.wrap(buffer).getShort() & 0xffff;
    }

    public int getEsId() {
        return esId;
    }

    @Override
    public String toString() {
        return "SLDescriptor{" +
                "esId=" + esId +
                '}';
    }
}
