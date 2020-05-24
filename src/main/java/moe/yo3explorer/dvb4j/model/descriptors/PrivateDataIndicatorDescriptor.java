package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;

public class PrivateDataIndicatorDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 15;
    }

    @Override
    public void readFrom(byte[] buffer) {
        indicator = buffer;
    }

    private byte[] indicator;

    @Override
    public String toString() {
        return "PrivateDataIndicatorDescriptor{" +
                "indicator=" + DvbTimeConverter.bytesToHex(indicator) +
                '}';
    }
}
