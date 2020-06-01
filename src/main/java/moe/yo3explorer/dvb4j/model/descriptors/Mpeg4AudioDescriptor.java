package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class Mpeg4AudioDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 28;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        audioProfileAndLevel = buffer[0];
    }

    //TODO: dissect this. See ITU H.220.0, page 104
    private byte audioProfileAndLevel;

    public byte getAudioProfileAndLevel() {
        return audioProfileAndLevel;
    }

    @Override
    public String toString() {
        return "Mpeg4AudioDescriptor{" +
                "audioProfileAndLevel=" + audioProfileAndLevel +
                '}';
    }
}
