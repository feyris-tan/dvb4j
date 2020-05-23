package moe.yo3explorer.dvb4j.model.enums;

public enum ComponentType
{
    RESERVED, MPEG2_VIDEO_4_3_25HZ, MPEG2_VIDEO_16_9_25HZ_PAN, MPEG2_VIDEO_16_9_25HZ, MPEG2_VIDEO_4_3_30HZ,
    MPEG2_VIDEO_16_9_30HZ_PAN, MPEG2_VIDEO_16_9_30HZ, MPEG2_VIDEO_OVER_16_9_30HZ, MPEG2_HD_4_3_25HZ,
    MPEG2_HD_16_9_25HZ_PAN, MPEG2_HD_16_9_25HZ, MPEG2_HD_OVER_16_9_25HZ, MPEG2_HD_4_3_30HZ, MPEG2_HD_16_9_30HZ,
    MPEG2_HD_16_9_30HZ_PAN, MPEG2_HD_OVER_16_9_30HZ, USER_DEFINED, MP2_AUDIO_SINGLE_MONO, MP2_AUDIO_DUAL_MONO,
    MP2_AUDIO_STEREO, MP2_AUDIO_MULTICHANNEL, MP2_AUDIO_SURROUND, MP2_AUDIO_VISUALLY_IMPAIRED,
    MP2_AUDIO_HEARING_IMPAIRED, RECEIVER_MIX_SUPPLEMENTARY_AUDIO, MP2_AUDIO_RECEIVER_MIX_AUDIO_DESCRIPTION,
    MP2_AUDIO_BROADCAST_MIX_AUDIO_DESCRIPTION, EBU_TELETEXT_SUBTITLES, ASSOCIATED_EBU_TELETEXT, VBI_DATA,
    DVB_SUBTITLES_NO_ASPECT, DVB_SUBTITLES_4_3, DVB_SUBTITLES_16_9, DVB_SUBTITLES_2d21_1, DVB_SUBTITLES_HD,
    DVB_SUBTITLES_3D, DVB_SUBTITLES_NO_ASPECT_HEARING_IMPAIRED, DVB_SUBTITLES_4_3_HEARING_IMPAIRED,
    DVB_SUBTITLES_16_9_HEARING_IMPAIRED, DVB_SUBTITLES_2d21_1_HEARING_IMPAIRED, DVB_SUBTITLES_HD_HEARING_IMPAIRED,
    DVB_SUBTITLES_3D_HEARING_IMPAIRED, OPEN_SIGN_LANGUAGE, CLOSED_SIGN_LANGUAGE, SPATIAL_RESOLUTION_UPSCALED, VIDEO_SDR,
    VIDEO_HDR_REMAPPED, VIDEO_HDR_CONVERTED, VIDEO_STANDARD_FRAMERATE, VIDEO_HIGH_FRAMERATE, DEPENDENT_SAOC_DE,
    MPEG2_VIDEO_OVER_16_9_25HZ, AC3_AUDIO, ENHANCED_AC3_AUDIO, H264_SD_4_3_25HZ, H264_SD_16_9_25HZ,
    H264_SD_OVER_16_9_25HZ, H264_SD_4_3_30HZ, H264_SD_16_9_30HZ, H264_SD_OVER_16_9_30HZ, H264_HD_16_9_25HZ,
    H264_HD_OVER_16_9_25HZ, H264_HD_16_9_30HZ, H264_HD_OVER_16_9_30HZ, H264_3D_SIDE_BY_SIDE_16_9_25HZ,
    H264_3D_TOP_AND_BOTTOM_16_9_25HZ, H264_3D_TOP_AND_BOTTOM_16_9_30HZ, H264_3D_SIDE_BY_SIDE_16_9_30HZ,
    H264_DEPENDENT_VIEW;

    public static ComponentType decode(byte streamContent, byte componentType)
    {
        int stream_content = (streamContent  & 0x0F);
        int stream_content_ext = (streamContent & 0xF0) >> 4;
        int component_type = componentType & 0xFF;

        if (stream_content == 0)
            return RESERVED;
        else if (stream_content == 1)
        {
            if (component_type == 0x00)
                return RESERVED;
            else if (component_type == 0x01)
                return MPEG2_VIDEO_4_3_25HZ;
            else if (component_type == 0x02)
                return MPEG2_VIDEO_16_9_25HZ_PAN;
            else if (component_type == 0x03)
                return MPEG2_VIDEO_16_9_25HZ;
            else if (component_type == 0x04)
                return MPEG2_VIDEO_OVER_16_9_25HZ;
            else if (component_type == 0x05)
                return MPEG2_VIDEO_4_3_30HZ;
            else if (component_type == 0x06)
                return MPEG2_VIDEO_16_9_30HZ_PAN;
            else if (component_type == 0x07)
                return MPEG2_VIDEO_16_9_30HZ;
            else if (component_type == 0x08)
                return MPEG2_VIDEO_OVER_16_9_30HZ;
            else if (component_type == 0x09)
                return MPEG2_HD_4_3_25HZ;
            else if (component_type == 0x0A)
                return MPEG2_HD_16_9_25HZ_PAN;
            else if (component_type == 0x0B)
                return MPEG2_HD_16_9_25HZ;
            else if (component_type == 0x0C)
                return MPEG2_HD_OVER_16_9_25HZ;
            else if (component_type == 0x0D)
                return MPEG2_HD_4_3_30HZ;
            else if (component_type == 0x0E)
                return MPEG2_HD_16_9_30HZ_PAN;
            else if (component_type == 0x0F)
                return MPEG2_HD_16_9_30HZ;
            else if (component_type == 0x10)
                return MPEG2_HD_OVER_16_9_30HZ;
            else if (component_type >= 0x11 && component_type <= 0xAF)
                return RESERVED;
            else if (component_type >= 0xB0 && component_type <= 0xFE)
                return USER_DEFINED;
            else if (component_type == 0xFF)
                return RESERVED;
        }
        else if (stream_content == 0x02)
        {
            if (component_type == 0x00)
                return RESERVED;
            else if (component_type == 0x01)
                return MP2_AUDIO_SINGLE_MONO;
            else if (component_type == 0x02)
                return MP2_AUDIO_DUAL_MONO;
            else if (component_type == 0x03)
                return MP2_AUDIO_STEREO;
            else if (component_type == 0x04)
                return MP2_AUDIO_MULTICHANNEL;
            else if (component_type == 0x05)
                return MP2_AUDIO_SURROUND;
            else if (component_type >= 0x06 && component_type <= 0x3F)
                return RESERVED;
            else if (component_type == 0x40)
                return MP2_AUDIO_VISUALLY_IMPAIRED;
            else if (component_type == 0x41)
                return MP2_AUDIO_HEARING_IMPAIRED;
            else if (component_type == 0x42)
                return RECEIVER_MIX_SUPPLEMENTARY_AUDIO;
            else if (component_type >= 0x43 && component_type <= 0x46)
                return RESERVED;
            else if (component_type == 0x47)
                return MP2_AUDIO_RECEIVER_MIX_AUDIO_DESCRIPTION;
            else if (component_type == 0x48)
                return MP2_AUDIO_BROADCAST_MIX_AUDIO_DESCRIPTION;
            else if (component_type >= 0x49 && component_type <= 0xAF)
                return RESERVED;
            else if (component_type >= 0xB0 && component_type <= 0xFE)
                return USER_DEFINED;
            else if (component_type == 0xFF)
                return RESERVED;
        }
        else if (stream_content == 0x03)
        {
            if (component_type == 0x00)
                return RESERVED;
            else if (component_type == 0x01)
                return EBU_TELETEXT_SUBTITLES;
            else if (component_type == 0x02)
                return ASSOCIATED_EBU_TELETEXT;
            else if (component_type == 0x03)
                return VBI_DATA;
            else if (component_type >= 0x04 && component_type <= 0x0F)
                return RESERVED;
            else if (component_type == 0x10)
                return DVB_SUBTITLES_NO_ASPECT;
            else if (component_type == 0x11)
                return DVB_SUBTITLES_4_3;
            else if (component_type == 0x12)
                return DVB_SUBTITLES_16_9;
            else if (component_type == 0x13)
                return DVB_SUBTITLES_2d21_1;
            else if (component_type == 0x14)
                return DVB_SUBTITLES_HD;
            else if (component_type == 0x15)
                return DVB_SUBTITLES_3D;
            else if (component_type >= 0x16 && component_type <= 0x1F)
                return RESERVED;
            else if (component_type == 0x20)
                return DVB_SUBTITLES_NO_ASPECT_HEARING_IMPAIRED;
            else if (component_type == 0x21)
                return DVB_SUBTITLES_4_3_HEARING_IMPAIRED;
            else if (component_type == 0x22)
                return DVB_SUBTITLES_16_9_HEARING_IMPAIRED;
            else if (component_type == 0x23)
                return DVB_SUBTITLES_2d21_1_HEARING_IMPAIRED;
            else if (component_type == 0x24)
                return DVB_SUBTITLES_HD_HEARING_IMPAIRED;
            else if (component_type == 0x25)
                return DVB_SUBTITLES_3D_HEARING_IMPAIRED;
            else if (component_type >= 0x26 && component_type <= 0x2F)
                return RESERVED;
            else if (component_type == 0x30)
                return OPEN_SIGN_LANGUAGE;
            else if (component_type == 0x31)
                return CLOSED_SIGN_LANGUAGE;
            else if (component_type >= 0x32 && component_type <= 0x3F)
                return RESERVED;
            else if (component_type == 0x40)
                return SPATIAL_RESOLUTION_UPSCALED;
            else if (component_type == 0x41)
                return VIDEO_SDR;
            else if (component_type == 0x42)
                return VIDEO_HDR_REMAPPED;
            else if (component_type == 0x43)
                return VIDEO_HDR_CONVERTED;
            else if (component_type == 0x44)
                return VIDEO_STANDARD_FRAMERATE;
            else if (component_type == 0x45)
                return VIDEO_HIGH_FRAMERATE;
            else if (component_type >= 0x46 && component_type <= 0x7F)
                return RESERVED;
            else if (component_type == 0x80)
                return DEPENDENT_SAOC_DE;
            else if (component_type >= 0x81 && component_type <= 0xAF)
                return RESERVED;
            else if (component_type >= 0xB0 && component_type <= 0xFE)
                return USER_DEFINED;
            else if (component_type == 0xFF)
                return RESERVED;
        }
        else if (stream_content == 0x04)
        {
            if (component_type >= 0x00 && component_type <= 0x7F)
                return AC3_AUDIO;
            else if (component_type <= 0x80 && component_type >= 0xFF)
                return ENHANCED_AC3_AUDIO;
        }
        else if (stream_content == 0x05)
        {
            if (component_type == 0x00)
                return RESERVED;
            else if (component_type == 0x01)
                return H264_SD_4_3_25HZ;
            else if (component_type == 0x02)
                return RESERVED;
            else if (component_type == 0x03)
                return H264_SD_16_9_25HZ;
            else if (component_type == 0x04)
                return H264_SD_OVER_16_9_25HZ;
            else if (component_type == 0x05)
                return H264_SD_4_3_30HZ;
            else if (component_type == 0x06)
                return RESERVED;
            else if (component_type == 0x07)
                return H264_SD_16_9_30HZ;
            else if (component_type == 0x08)
                return H264_SD_OVER_16_9_30HZ;
            else if (component_type == 0x09 || component_type == 0x0A)
                return RESERVED;
            else if (component_type == 0x0B)
                return H264_HD_16_9_25HZ;
            else if (component_type == 0x0C)
                return H264_HD_OVER_16_9_25HZ;
            else if (component_type == 0x0D || component_type == 0x0E)
                return RESERVED;
            else if (component_type == 0x0F)
                return H264_HD_16_9_30HZ;
            else if (component_type == 0x10)
                return H264_HD_OVER_16_9_30HZ;
            else if (component_type >= 0x11 && component_type <= 0x7F)
                return RESERVED;
            else if (component_type == 0x80)
                return H264_3D_SIDE_BY_SIDE_16_9_25HZ;
            else if (component_type == 0x81)
                return H264_3D_TOP_AND_BOTTOM_16_9_25HZ;
            else if (component_type == 0x82)
                return H264_3D_SIDE_BY_SIDE_16_9_30HZ;
            else if (component_type == 0x83)
                return H264_3D_TOP_AND_BOTTOM_16_9_30HZ;
            else if (component_type == 0x84)
                return H264_DEPENDENT_VIEW;
            else if (component_type >= 0x85 && component_type <= 0xAF)
                return RESERVED;
            else if (component_type >= 0xB0 && component_type <= 0xFE)
                return USER_DEFINED;
            else if (component_type == 0xFF)
                return RESERVED;
        }
        else if (stream_content >= 0x0C && stream_content <= 0x0F)
        {
            return USER_DEFINED;
        }


        throw new RuntimeException("This component type is not implemented yet!");
    }
}
