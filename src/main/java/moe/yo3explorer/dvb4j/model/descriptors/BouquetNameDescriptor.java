package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.charset.StandardCharsets;

public class BouquetNameDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x47;
    }

    @Override
    public void readFrom(byte[] buffer) {
        name = new String(buffer, StandardCharsets.US_ASCII);
    }

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BouquetNameDescriptor{" +
                "name='" + name + '\'' +
                '}';
    }
}
