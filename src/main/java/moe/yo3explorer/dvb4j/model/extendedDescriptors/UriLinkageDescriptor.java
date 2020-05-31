package moe.yo3explorer.dvb4j.model.extendedDescriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class UriLinkageDescriptor implements Descriptor {
    private int uriLinkageType;
    private String uri;
    private int minPollingInterval;

    @Override
    public int getTag() {
        return 0x13;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        uriLinkageType = wrap.get() & 0xff;
        int uriLength = wrap.get() & 0xff;

        byte[] uriBuffer = new byte[uriLength];
        wrap.get(uriBuffer);
        uri = new String(uriBuffer, StandardCharsets.US_ASCII);

        if ((uriLinkageType == 0) || uriLinkageType == 1)
            minPollingInterval = wrap.getShort() & 0xffff;
    }

    private boolean isHbbTv()
    {
        return uriLinkageType == 0x60;
    }

    public int getUriLinkageType() {
        return uriLinkageType;
    }

    public String getUri() {
        return uri;
    }

    public int getMinPollingInterval() {
        return minPollingInterval;
    }

    @Override
    public String toString() {
        return "UriLinkageDescriptor{" +
                "uriLinkageType=" + uriLinkageType +
                ", uri='" + uri + '\'' +
                '}';
    }
}
