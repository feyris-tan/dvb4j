package moe.yo3explorer.dvb4j.model.descriptorEntities;

public class MultilingualComponent {
    private final String langCode;
    private final String text;

    public MultilingualComponent(String langCode, String text) {

        this.langCode = langCode;
        this.text = text;
    }

    public String getLangCode() {
        return langCode;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "MultilingualComponent{" +
                "langCode='" + langCode + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
