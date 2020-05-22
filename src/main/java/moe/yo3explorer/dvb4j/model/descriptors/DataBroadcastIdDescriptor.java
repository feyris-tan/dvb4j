package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DataBroadcastIdDescriptor implements Descriptor
{
    @Override
    public int getTag() {
        return 0x66;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        dataBroadcastId = wrap.getShort();

        int size = buffer.length - 2;
        idSelectorBytes = new byte[size];
        wrap.get(idSelectorBytes);
    }

    private short dataBroadcastId;
    private byte[] idSelectorBytes;

    public short getDataBroadcastId() {
        return dataBroadcastId;
    }

    public byte[] getIdSelectorBytes() {
        return idSelectorBytes;
    }

    @Override
    public String toString() {
        return String.format("DataBroadcastId %04X",dataBroadcastId);
    }
}
