package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class AdaptionFieldDataDescriptor implements Descriptor {
    private boolean announcementSwitchingData;
    private boolean auInformationData;
    private boolean pvrAssistInformationData;

    @Override
    public int getTag() {
        return 0x70;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        byte flags = buffer[0];

        announcementSwitchingData = (flags & 0x01) != 0;
        auInformationData = (flags & 0x02) != 0;
        pvrAssistInformationData = (flags & 0x04) != 0;
    }

    public boolean isAnnouncementSwitchingData() {
        return announcementSwitchingData;
    }

    public boolean isAuInformationData() {
        return auInformationData;
    }

    public boolean isPvrAssistInformationData() {
        return pvrAssistInformationData;
    }

    @Override
    public String toString() {
        return "AdaptionFieldDataDescriptor{" +
                "announcementSwitchingData=" + announcementSwitchingData +
                ", auInformationData=" + auInformationData +
                ", pvrAssistInformationData=" + pvrAssistInformationData +
                '}';
    }
}
