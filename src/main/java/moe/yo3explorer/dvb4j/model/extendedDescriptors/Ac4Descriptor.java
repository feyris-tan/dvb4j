package moe.yo3explorer.dvb4j.model.extendedDescriptors;

import moe.yo3explorer.dvb4j.model.enums.Ac4ChannelMode;
import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class Ac4Descriptor implements Descriptor {
    private boolean ac4ConfigFlag;
    private boolean ac4TocFlag;
    private boolean ac4DialogEnhancementEnabled;
    private Ac4ChannelMode ac4channelMode;
    private byte[] ac4Toc;

    @Override
    public int getTag() {
        return 0x15;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        byte flags = wrap.get();
        ac4ConfigFlag = (flags & 0x80) != 0;
        ac4TocFlag = (flags & 0x40) != 0;

        if (ac4ConfigFlag)
        {
            flags = wrap.get();
            ac4DialogEnhancementEnabled = (flags & 0x80) != 0;

            int channelModeRaw = (flags & 0x60) >> 5;
            ac4channelMode = Ac4ChannelMode.values()[channelModeRaw];
        }

        if (ac4TocFlag)
        {
            int tocLen = wrap.get() & 0xff;
            ac4Toc = new byte[tocLen];
            wrap.get(ac4Toc);
        }
    }

    public boolean isAc4ConfigFlag() {
        return ac4ConfigFlag;
    }

    public boolean isAc4TocFlag() {
        return ac4TocFlag;
    }

    public boolean isAc4DialogEnhancementEnabled() {
        return ac4DialogEnhancementEnabled;
    }

    public Ac4ChannelMode getAc4channelMode() {
        return ac4channelMode;
    }

    public byte[] getAc4Toc() {
        return ac4Toc;
    }
}
