package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbException;
import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

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
        String hexString = bytesToHex(data);
        return "UserDefinedDescriptor{" +
                "data=" + hexString +
                '}';
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    @NotNull
    private static String bytesToHex(byte[] bytes) {
        if (bytes == null)
            return "";
        // stolen from https://stackoverflow.com/a/9855338
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
