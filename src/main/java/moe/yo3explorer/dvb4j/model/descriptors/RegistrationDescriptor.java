package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RegistrationDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 5;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        formatIdentifier = wrap.getInt() & 0xffffffffL;

        int remain = buffer.length - 4;
        additionalIdentificationInfo = new byte[remain];
        wrap.get(additionalIdentificationInfo);
    }

    private long formatIdentifier;
    private byte[] additionalIdentificationInfo;

    @Override
    public String toString() {
        return String.format("RegistrationDescriptor %08X",formatIdentifier);
    }
}
