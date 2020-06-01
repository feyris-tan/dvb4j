package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.ComponentType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ComponentDescriptor implements Descriptor {

    private ComponentType componentType;
    private int componentTag;
    private String iso639langCode;
    private String text;

    @Override
    public int getTag() {
        return 0x50;
    }

    @Override
    public void readFrom(byte[] buffer) {
        if (buffer.length < 2)
            return;
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        byte componentTypeA = wrap.get();
        byte componentTypeB = wrap.get();
        componentType = ComponentType.decode(componentTypeA,componentTypeB);

        componentTag = wrap.get() & 0xff;

        byte[] strBuffer = new byte[3];
        wrap.get(strBuffer);
        iso639langCode = new String(strBuffer, StandardCharsets.US_ASCII);

        int textLen = wrap.limit() - wrap.position();
        strBuffer = new byte[textLen];
        wrap.get(strBuffer);
        text = new String(strBuffer,StandardCharsets.US_ASCII);
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public int getComponentTag() {
        return componentTag;
    }

    public String getIso639langCode() {
        return iso639langCode;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "ComponentDescriptor{" +
                "componentType=" + componentType +
                ", componentTag=" + componentTag +
                ", iso639langCode='" + iso639langCode + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
