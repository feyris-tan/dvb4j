package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class AudioStreamDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 3;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        freeFormat = (buffer[0] & 0x80) != 0;
        id = (buffer[0] & 0x40) != 0;
        layer = (buffer[0] >> 4) & 0x03;
        variableRateAudio = (buffer[0] & 0x08) != 0;
    }

    private boolean freeFormat;
    private boolean id;
    private int layer;
    private boolean variableRateAudio;
}
