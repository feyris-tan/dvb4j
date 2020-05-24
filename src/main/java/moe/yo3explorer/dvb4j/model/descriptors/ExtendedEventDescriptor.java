package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.text.UsedCharsets;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtendedEventDescriptor implements Descriptor {

    private int descriptorNumber;
    private int lastDescriptorNumber;
    private String iso639LangCode;
    private HashMap<String, String> items;
    private String text;

    @Override
    public int getTag() {
        return 0x4E;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        byte numberFlags = wrap.get();
        descriptorNumber = (numberFlags >> 4) & 0xff;

        lastDescriptorNumber = numberFlags & 0x0f;

        byte[] strBuffer = new byte[3];
        wrap.get(strBuffer);
        iso639LangCode = new String(strBuffer, StandardCharsets.US_ASCII);

        int itemLenRemain = wrap.get() & 0xff;
        while (itemLenRemain > 0)
        {
            int itemDescLen = wrap.get() & 0xff;
            itemLenRemain--;

            strBuffer = new byte[itemDescLen];
            wrap.get(strBuffer);
            String itemDescription = new String(strBuffer, UsedCharsets.DVB);
            itemLenRemain -= itemDescLen;

            int itemLen = wrap.get() & 0xff;
            itemLenRemain--;

            strBuffer = new byte[itemLen];
            wrap.get(strBuffer);
            String item = new String(strBuffer,UsedCharsets.DVB);
            itemLenRemain -= itemLen;
            items.put(itemDescription,item);
        }

        int textLen = wrap.get() & 0xff;

        strBuffer = new byte[textLen];
        wrap.get(strBuffer);
        text = new String(strBuffer,UsedCharsets.DVB);
    }

    public int getDescriptorNumber() {
        return descriptorNumber;
    }

    public int getLastDescriptorNumber() {
        return lastDescriptorNumber;
    }

    public String getIso639LangCode() {
        return iso639LangCode;
    }

    public Map<String, String> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "ExtendedEventDescriptor{" +
                "descriptorNumber=" + descriptorNumber +
                ", lastDescriptorNumber=" + lastDescriptorNumber +
                ", iso639LangCode='" + iso639LangCode + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
