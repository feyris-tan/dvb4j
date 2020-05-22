package moe.yo3explorer.dvb4j.model.enums;

import moe.yo3explorer.dvb4j.model.descriptors.TeletextDescriptor;

public enum TeletextType
{
    RESERVED, INITIAL_TELETEXT_PAGE, TELETEXT_SUBTITLE_PAGE, ADDITIAL_INFORMATION_PAGE, PROGRAMME_SCHEDULE_PAGE, TELETEXT_SUBTITLE_PAGE_HEARING_IMPAIRED;

    public static TeletextType parse(int i)
    {

        switch (i)
        {
            case 1:
                return INITIAL_TELETEXT_PAGE;
            case 2:
                return TELETEXT_SUBTITLE_PAGE;
            case 3:
                return ADDITIAL_INFORMATION_PAGE;
            case 4:
                return PROGRAMME_SCHEDULE_PAGE;
            case 5:
                return TELETEXT_SUBTITLE_PAGE_HEARING_IMPAIRED;
            default:
                return RESERVED;
        }
    }
}
