package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class PdcDescriptor implements Descriptor {

    private int pdc;

    @Override
    public int getTag() {
        return 0x69;
    }

    @Override
    public void readFrom(byte[] buffer) {
        byte[] bytes = Arrays.copyOf(buffer, 4);
        pdc = ByteBuffer.wrap(bytes).getInt();
    }

    public int getPdc() {
        return pdc;
    }

    @Override
    public String toString() {
        return "PdcDescriptor{" +
                "pdc=" + pdc +
                '}';
    }
}
