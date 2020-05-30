package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.text.UsedCharsets;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ShortEventDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x4D;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        byte[] raw = new byte[3];
        wrap.get(raw);
        iso639languageCode = new String(raw, StandardCharsets.US_ASCII);

        int eventNameLength = wrap.get() & 0xff;

        raw = new byte[eventNameLength];
        wrap.get(raw);
        eventName = new String(raw, UsedCharsets.DVB);

        int textLength = wrap.get() & 0xff;

        raw = new byte[textLength];
        wrap.get(raw);
        text = new String(raw, UsedCharsets.DVB);
    }

    private String iso639languageCode;
    private String eventName;
    private String text;

    public String getIso639languageCode() {
        return iso639languageCode;
    }

    public String getEventName() {
        return eventName;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "ShortEventDescriptor{" +
                "iso639languageCode='" + iso639languageCode + '\'' +
                ", eventName='" + eventName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
