package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class AncillaryDataDescriptor implements Descriptor {
    private boolean dvdVideo;
    private boolean extended;
    private boolean anouncementSwitching;
    private boolean dab;
    private boolean scaleFactorErrorCheck;
    private boolean mpeg4;
    private boolean rdsViaUecp;

    @Override
    public int getTag() {
        return 0x6B;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        byte flags = buffer[0];

        dvdVideo = (flags & 0x01) != 0;
        extended = (flags & 0x02) != 0;
        anouncementSwitching = (flags & 0x04) != 0;
        dab = (flags & 0x08) != 0;
        scaleFactorErrorCheck = (flags & 0x10) != 0;
        mpeg4 = (flags & 0x20) != 0;
        rdsViaUecp = (flags & 0x40) != 0;

    }
}
