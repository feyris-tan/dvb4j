package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.text.DvbCharset;
import moe.yo3explorer.dvb4j.text.UsedCharsets;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DataBroadcastDescriptor implements Descriptor {
    private int dataBroadcastId;
    private int componentTag;
    private byte[] selector;
    private String iso639languageCode;
    private String text;

    @Override
    public int getTag() {
        return 0x64;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        dataBroadcastId = wrap.getShort() & 0xffff;
        componentTag = wrap.get() & 0xff;

        int selectorLength = wrap.get() & 0xff;

        if (wrap.limit() - wrap.position() < selectorLength)
            return;

        selector = new byte[selectorLength];
        wrap.get(selector);

        if (wrap.limit() - wrap.position() < 4)
            return;

        byte[] langcodeBuffer = new byte[3];
        wrap.get(langcodeBuffer);
        iso639languageCode = new String(langcodeBuffer, StandardCharsets.US_ASCII);

        int textLength = wrap.get() & 0xff;
        byte[] textBuffer = new byte[textLength];
        wrap.get(textBuffer);
        text = new String(textBuffer, UsedCharsets.DVB);
    }

    public int getDataBroadcastId() {
        return dataBroadcastId;
    }

    public int getComponentTag() {
        return componentTag;
    }

    public byte[] getSelector() {
        return selector;
    }

    public String getIso639languageCode() {
        return iso639languageCode;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "DataBroadcastDescriptor{" +
                "dataBroadcastId=" + dataBroadcastId +
                ", iso639languageCode='" + iso639languageCode + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
