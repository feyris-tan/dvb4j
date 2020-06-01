package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class TimeShiftedEventDescriptor implements Descriptor {
    private int referenceServiceId;
    private int referenceEventId;

    @Override
    public int getTag() {
        return 0x4f;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        referenceServiceId = wrap.getShort() & 0xffff;
        referenceEventId = wrap.getShort() & 0xffff;
    }

    public int getReferenceServiceId() {
        return referenceServiceId;
    }

    public int getReferenceEventId() {
        return referenceEventId;
    }

    @Override
    public String toString() {
        return "TimeShiftedEventDescriptor{" +
                "referenceServiceId=" + referenceServiceId +
                ", referenceEventId=" + referenceEventId +
                '}';
    }
}
