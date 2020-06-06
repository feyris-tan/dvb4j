package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class Mpeg4AudioExtensionDescriptor implements Descriptor {
    private boolean ascPresent;
    private int[] audioProfileLevelIndications;
    private byte[] audioSpecificConfig;

    @Override
    public int getTag() {
        return 46;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        byte flags = wrap.get();

        ascPresent = (flags & 0x80) != 0;

        int numOfLoops = (flags & 0x0f);
        audioProfileLevelIndications = new int[numOfLoops];
        for (int i = 0; i < numOfLoops; i++)
        {
            audioProfileLevelIndications[i] = wrap.get() & 0xff;
        }
        if (ascPresent)
        {
            int ascSize = wrap.get() & 0xff;
            audioSpecificConfig = new byte[ascSize];
            wrap.get(audioSpecificConfig);
        }
    }
}
