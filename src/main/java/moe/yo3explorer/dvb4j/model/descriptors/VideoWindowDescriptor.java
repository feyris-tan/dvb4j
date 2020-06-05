package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.awt.*;
import java.nio.ByteBuffer;

public class VideoWindowDescriptor implements Descriptor {
    private Point offset;

    @Override
    public int getTag() {
        return 8;
    }

    @Override
    public void readFrom(byte[] buffer) {
        int packedBits = ByteBuffer.wrap(buffer).getInt();

        int horizontalOffset = (packedBits & 0xfffc0000);
        horizontalOffset >>= 18;
        int verticalOffset   = (packedBits & 0x0003fff0);
        verticalOffset >>= 4;
        offset = new Point(horizontalOffset,verticalOffset);
        windowPriority = (packedBits   & 0x0000000f);
    }

    private int windowPriority;

    public int getWindowPriority() {
        return windowPriority;
    }

    public Point getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "VideoWindowDescriptor{" +
                "offset=" + offset +
                ", windowPriority=" + windowPriority +
                '}';
    }
}
