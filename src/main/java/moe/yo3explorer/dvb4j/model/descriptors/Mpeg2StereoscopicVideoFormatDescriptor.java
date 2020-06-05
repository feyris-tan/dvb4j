package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class Mpeg2StereoscopicVideoFormatDescriptor implements Descriptor {
    private boolean stereoVideoPresent;
    private int arrangementType;

    @Override
    public int getTag() {
        return 52;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        byte flags = buffer[0];
        stereoVideoPresent = (flags & 0x80) != 0;
        if (stereoVideoPresent)
        {
            arrangementType = (flags & 0x7f);
        }
    }

    public boolean isStereoVideoPresent() {
        return stereoVideoPresent;
    }

    public int getArrangementType() {
        return arrangementType;
    }

    @Override
    public String toString() {
        return "Mpeg2StereoscopicVideoFormatDescriptor{" +
                "stereoVideoPresent=" + stereoVideoPresent +
                ", arrangementType=" + arrangementType +
                '}';
    }
}
