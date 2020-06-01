package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class VideoWindowDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 7;
    }

    @Override
    public void readFrom(byte[] buffer) {
        int packedBits = ByteBuffer.wrap(buffer).getInt();

        horizontalOffset = (packedBits & 0xfffc0000);
        horizontalOffset >>= 18;
        verticalOffset   = (packedBits & 0x0003fff0);
        verticalOffset >>= 4;
        windowPriority = (packedBits   & 0x0000000f);
    }

    private long horizontalOffset, verticalOffset;
    private int windowPriority;

    public long getHorizontalOffset() {
        return horizontalOffset;
    }

    public long getVerticalOffset() {
        return verticalOffset;
    }

    public int getWindowPriority() {
        return windowPriority;
    }

    @Override
    public String toString() {
        return "VideoWindowDescriptor{" +
                "horizontalOffset=" + horizontalOffset +
                ", verticalOffset=" + verticalOffset +
                ", windowPriority=" + windowPriority +
                '}';
    }
}
