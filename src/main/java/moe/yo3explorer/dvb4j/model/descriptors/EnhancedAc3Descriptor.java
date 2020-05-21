package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class EnhancedAc3Descriptor extends Ac3Descriptor
{
    private Integer substream1;
    private Integer substream2;
    private Integer substream3;

    @Override
    public int getTag() {
        return 0x7a;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        int flags = buffer[0] & 0xff;
        boolean componentTypeFlags = (flags & 0x80) != 0;
        boolean bsidTypeFlags = (flags & 0x40) != 0;
        boolean mainIdFlags = (flags & 0x20) != 0;
        boolean asvcFlags = (flags & 0x10) != 0;
        boolean mixInfoExistsFlag = (flags & 0x08) != 0;
        boolean substream1Flag = (flags & 0x04) != 0;
        boolean substream2Flag = (flags & 0x02) != 0;
        boolean substream3Flag = (flags & 0x01) != 0;
        int offset = 1;
        if (componentTypeFlags)
            componentType = ((int)buffer[offset++]) & 0xff;
        if (bsidTypeFlags)
            bsidType = ((int)buffer[offset++]) & 0xff;
        if (mainIdFlags)
            mainId = ((int)buffer[offset++]) & 0xff;
        if (asvcFlags)
            asvc = ((int)buffer[offset++]) & 0xff;
        if (substream1Flag)
            substream1 = ((int)buffer[offset++]) & 0xff;
        if (substream2Flag)
            substream2 = ((int)buffer[offset++]) & 0xff;
        if (substream3Flag)
            substream3 = ((int)buffer[offset++]) & 0xff;
    }

    public Integer getSubstream1() {
        return substream1;
    }

    public Integer getSubstream2() {
        return substream2;
    }

    public Integer getSubstream3() {
        return substream3;
    }

    @Override
    public String toString() {
        return "EnhancedAc3Descriptor{" +
                "substream1=" + substream1 +
                ", substream2=" + substream2 +
                ", substream3=" + substream3 +
                '}';
    }
}
