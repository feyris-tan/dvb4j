package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class UserDefinedDescriptor implements Descriptor {

    public UserDefinedDescriptor(int tag) {
        this.tag = tag;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        data = new byte[buffer.length];
        System.arraycopy(buffer,0,data,0,buffer.length);
    }

    private int tag;
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        String hexString = DvbTimeConverter.bytesToHex(data);
        return "UserDefinedDescriptor{" +
                "data=" + hexString +
                '}';
    }

}
