package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class StreamIdentifierDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x52;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        componentTag = buffer[0] & 0xff;
    }

    private int componentTag;

    @Override
    public String toString() {
        return "StreamIdentifierDescriptor{" +
                "componentTag=" + componentTag +
                '}';
    }
}
