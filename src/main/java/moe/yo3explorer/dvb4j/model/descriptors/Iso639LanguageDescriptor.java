package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.charset.StandardCharsets;

public class Iso639LanguageDescriptor implements Descriptor {

    private String langCode;

    @Override
    public int getTag() {
        return 10;
    }

    @Override
    public void readFrom(byte[] buffer) {
        langCode = new String(buffer,StandardCharsets.US_ASCII);
    }

    public String getLangCode() {
        return langCode;
    }

    @Override
    public String toString() {
        return "Iso639LanguageDescriptor{" +
                "langCode='" + langCode + '\'' +
                '}';
    }
}
