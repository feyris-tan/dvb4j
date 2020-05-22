package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class ExtensionDescriptor implements Descriptor
{
    @Override
    public int getTag() {
        return 0x7F;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        tagExtension = buffer[0] & 0xff;

        int len = buffer.length -1;
        selectorBytes = new byte[len];
        System.arraycopy(buffer,1,selectorBytes,0,len);
    }

    private int tagExtension;
    private byte[] selectorBytes;

    public int getTagExtension() {
        return tagExtension;
    }

    public byte[] getSelectorBytes() {
        return selectorBytes;
    }

    @Override
    public String toString() {
        return "ExtensionDescriptor{" +
                "tagExtension=" + tagExtension +
                '}';
    }
}
