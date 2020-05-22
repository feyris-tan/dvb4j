package moe.yo3explorer.dvb4j.model.extededDescriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.EditorialClassification;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SupplementaryAudioDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 6;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        byte flags = wrap.get();
        isIndependantAudioStream = (flags & 0x80) != 0;

        int rawEditorialClassification = flags >> 2;
        rawEditorialClassification &= 0x1F;
        editorialClassification = EditorialClassification.decode(rawEditorialClassification);

        languageCodePresent = (flags & 0x01) != 0;

        if (languageCodePresent)
        {
            byte[] lcBuffer = new byte[3];
            wrap.get(lcBuffer);
            iso639LanguageCode = new String(lcBuffer, StandardCharsets.US_ASCII);
        }
    }

    private boolean isIndependantAudioStream;
    private EditorialClassification editorialClassification;
    private boolean languageCodePresent;
    private String iso639LanguageCode;

    public boolean isIndependantAudioStream() {
        return isIndependantAudioStream;
    }

    public EditorialClassification getEditorialClassification() {
        return editorialClassification;
    }

    public boolean isLanguageCodePresent() {
        return languageCodePresent;
    }

    public String getIso639LanguageCode() {
        return iso639LanguageCode;
    }

    @Override
    public String toString() {
        return "SupplementaryAudioDescriptor{" +
                "isIndependantAudioStream=" + isIndependantAudioStream +
                ", editorialClassification=" + editorialClassification +
                ", iso639LanguageCode='" + iso639LanguageCode + '\'' +
                '}';
    }
}
