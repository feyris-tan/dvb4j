package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class AacDescriptor implements Descriptor {
    private int profileAndLevel;
    private boolean aacTypeFlag;
    private boolean saocDeFlag;
    private int aacType;

    @Override
    public int getTag() {
        return 0x7c;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        profileAndLevel = wrap.get() & 0xff;
        if (buffer.length > 1)
        {
            byte flags = wrap.get();
            aacTypeFlag = (flags & 0x80) != 0;
            saocDeFlag = (flags & 0x40) != 0;
            if (aacTypeFlag)
            {
                aacType = wrap.get() & 0xff;
            }
        }
    }

}
