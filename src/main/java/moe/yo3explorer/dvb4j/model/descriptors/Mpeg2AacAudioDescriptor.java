package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class Mpeg2AacAudioDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 43;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        aacProfileIndex = buffer[0] & 0xff;
        aacChannelConfigurationIndex = buffer[1] & 0xff; //see ISO/IEC 13818-7:2004
        aacAdditionalInformation = buffer[2] & 0xff;
    }

    private int aacProfileIndex;
    private int aacChannelConfigurationIndex;
    private int aacAdditionalInformation;

    public int getAacProfileIndex() {
        return aacProfileIndex;
    }

    public int getAacChannelConfigurationIndex() {
        return aacChannelConfigurationIndex;
    }

    public int getAacAdditionalInformation() {
        return aacAdditionalInformation;
    }

    @Override
    public String toString() {
        return "Mpeg2AacAudioDescriptor{" +
                "aacProfileIndex=" + aacProfileIndex +
                ", aacChannelConfigurationIndex=" + aacChannelConfigurationIndex +
                ", aacAdditionalInformation=" + aacAdditionalInformation +
                '}';
    }
}
