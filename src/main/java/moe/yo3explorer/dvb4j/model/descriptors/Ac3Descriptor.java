package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class Ac3Descriptor implements Descriptor {
    private Integer componentType;
    private Integer bsidType;
    private Integer mainId;
    private Integer asvc;

    @Override
    public int getTag() {
        return 106;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        int flags = buffer[0] & 0xff;
        boolean componentTypeFlags = (flags & 0x80) != 0;
        boolean bsidTypeFlags = (flags & 0x40) != 0;
        boolean mainIdFlags = (flags & 0x20) != 0;
        boolean asvcFlags = (flags & 0x10) != 0;
        int offset = 1;
        if (componentTypeFlags)
            componentType = ((int)buffer[offset++]) & 0xff;
        if (bsidTypeFlags)
            bsidType = ((int)buffer[offset++]) & 0xff;
        if (mainIdFlags)
            mainId = ((int)buffer[offset++]) & 0xff;
        if (asvcFlags)
            asvc = ((int)buffer[offset++]) & 0xff;
    }

    public Integer getComponentType() {
        return componentType;
    }

    public Integer getBsidType() {
        return bsidType;
    }

    public Integer getMainId() {
        return mainId;
    }

    public Integer getAsvc() {
        return asvc;
    }

    @Override
    public String toString() {
        return "Ac3Descriptor{" +
                "componentType=" + componentType +
                ", bsidType=" + bsidType +
                ", mainId=" + mainId +
                ", asvc=" + asvc +
                '}';
    }
}
