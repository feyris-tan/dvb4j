package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class ScramblingDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x65;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        scramblingMode = buffer[0] & 0xff;
    }

    //TODO: Get ATIS 0800006 to decipher this properly.
    private int scramblingMode;

    public int getScramblingMode() {
        return scramblingMode;
    }

    @Override
    public String toString() {
        return "ScramblingDescriptor{" +
                "scramblingMode=" + scramblingMode +
                '}';
    }
}
