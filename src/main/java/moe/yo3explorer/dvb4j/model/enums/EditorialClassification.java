package moe.yo3explorer.dvb4j.model.enums;

import moe.yo3explorer.dvb4j.model.extededDescriptors.SupplementaryAudioDescriptor;

public enum EditorialClassification
{
    MAIN_AUDIO, AUDIO_DESCRIPTION_VISUALLY_IMPAIRED, CLEAN_AUDIO_HEARING_IMPAIRED, SPOKEN_SUBTITLES, DEPENDENT_PARAMETIC_DATA_STREAM, RESERVED, UNSPECIFIC_SUPPLEMENTARY_AUDIO, USER_DEFINED;

    public static EditorialClassification decode(int a)
    {
        if (a >= 0x05 && a <= 0x16)
            return RESERVED;

        switch (a)
        {
            case 0x00:
                return MAIN_AUDIO;
            case 0x01:
                return AUDIO_DESCRIPTION_VISUALLY_IMPAIRED;
            case 0x02:
                return CLEAN_AUDIO_HEARING_IMPAIRED;
            case 0x03:
                return SPOKEN_SUBTITLES;
            case 0x04:
                return DEPENDENT_PARAMETIC_DATA_STREAM;
            case 0x17:
                return UNSPECIFIC_SUPPLEMENTARY_AUDIO;
            default:
                return USER_DEFINED;
        }
    }
}
