package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class TimeShiftedServiceDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x4C;
    }

    @Override
    public void readFrom(byte[] buffer) {
        referenceServiceId = ByteBuffer.wrap(buffer).getShort() & 0xff;
    }

    private int referenceServiceId;

    public int getReferenceServiceId() {
        return referenceServiceId;
    }

    @Override
    public String toString() {
        return "TimeShiftedServiceDescriptor{" +
                "referenceServiceId=" + referenceServiceId +
                '}';
    }
}
