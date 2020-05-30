package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class ServiceMoveDescriptor implements Descriptor {
    private int newOriginalNetworkId;
    private int newTransportStreamId;
    private int newServiceId;

    @Override
    public int getTag() {
        return 0x60;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        newOriginalNetworkId = wrap.getShort() & 0xffff;
        newTransportStreamId = wrap.getShort() & 0xffff;
        newServiceId = wrap.getShort() & 0xffff;
    }

    public int getNewOriginalNetworkId() {
        return newOriginalNetworkId;
    }

    public int getNewTransportStreamId() {
        return newTransportStreamId;
    }

    public int getNewServiceId() {
        return newServiceId;
    }

    @Override
    public String toString() {
        return "ServiceMoveDescriptor{" +
                "newOriginalNetworkId=" + newOriginalNetworkId +
                ", newTransportStreamId=" + newTransportStreamId +
                ", newServiceId=" + newServiceId +
                '}';
    }
}
