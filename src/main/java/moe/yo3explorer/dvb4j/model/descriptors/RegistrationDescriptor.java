package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class RegistrationDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 5;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        formatIdentifier = new byte[4];
        wrap.get(formatIdentifier);

        int remain = buffer.length - 4;
        additionalIdentificationInfo = new byte[remain];
        wrap.get(additionalIdentificationInfo);
    }

    private byte[] formatIdentifier;
    private byte[] additionalIdentificationInfo;

    /**
     * A list for these is available at https://smpte-ra.org/registered-mpeg-ts-ids
     * @return
     */
    public long getFormatIdentifierAsLong() {
        return ByteBuffer.wrap(formatIdentifier).getInt() & 0xFFFFFFFFL;
    }

    public String getFormatIdentifierAsString()
    {
        return new String(formatIdentifier, StandardCharsets.US_ASCII);
    }

    public byte[] getAdditionalIdentificationInfo() {
        return additionalIdentificationInfo;
    }

    @Override
    public String toString() {
        return String.format("RegistrationDescriptor %s",getFormatIdentifierAsString());
    }
}
