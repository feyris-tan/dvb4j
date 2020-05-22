package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

public class NetworkNameDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x40;
    }

    @Override
    public void readFrom(byte[] buffer) {
        name = new String(buffer);
    }

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "NetworkNameDescriptor{" +
                "name='" + name + '\'' +
                '}';
    }
}
