package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class VideoStreamDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 2;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        multipleFramerate = (buffer[0] & 0x80) != 0;
        framerateCode = (framerateCode >> 3) & 0x0f;
        mpeg1only = (buffer[0] & 0x04) != 0;
        constrainedParameterFlag = (buffer[0] & 0x02) != 0;
        stillPictureFlag = (buffer[0] & 0x01) != 0;
        if (!mpeg1only)
        {
            profileAndLevelFlags = buffer[1];
            chromaFormat = buffer[2] >> 6;
            frameRateExtension = (buffer[2] & 0x20) != 0;
        }
    }

    private boolean multipleFramerate;
    private int framerateCode;
    private boolean mpeg1only;
    private boolean constrainedParameterFlag;
    private boolean stillPictureFlag;
    private byte profileAndLevelFlags;
    private int chromaFormat;
    private boolean frameRateExtension;

}