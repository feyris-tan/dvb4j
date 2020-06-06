package moe.yo3explorer.dvb4j.model.descriptorEntities;

import moe.yo3explorer.dvb4j.model.enums.TeletextType;

public class TeletextPageMetadata
{
    private final String languageCode;
    private final TeletextType teletextType;
    private final int teletextMagazineNumber;
    private final int teletextPageNumber;

    public TeletextPageMetadata(String languageCode, TeletextType teletextType, int teletextMagazineNumber, int teletextPageNumber) {
        this.languageCode = languageCode;
        this.teletextType = teletextType;
        this.teletextMagazineNumber = teletextMagazineNumber;
        this.teletextPageNumber = teletextPageNumber;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public TeletextType getTeletextType() {
        return teletextType;
    }

    public int getTeletextMagazineNumber() {
        return teletextMagazineNumber;
    }

    public int getTeletextPageNumber() {
        return teletextPageNumber;
    }

    @Override
    public String toString() {
        return "TeletextPageMetadata{" +
                "languageCode='" + languageCode + '\'' +
                ", teletextType=" + teletextType +
                ", teletextMagazineNumber=" + teletextMagazineNumber +
                ", teletextPageNumber=" + teletextPageNumber +
                '}';
    }
}
