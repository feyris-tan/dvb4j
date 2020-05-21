package moe.yo3explorer.dvb4j.model.enums;

public enum ServiceType {
    DIGITAL_TELEVISION, DIGITAL_RADIO, TELETEXT, NVOD_REFERENCE, NVOD_TIME_SHIFTED, MOSAIC, FM_RADIO, DVB_SRM,
    ADVANCED_CODEC_DIGITAL_RADIO, H264_MOSAIC, DATA_BROADCAST, RESERVED_FOR_COMMON_INTERFACE, RCS_MAP, RCS_FLS,
    DVB_MHP, MPEG2_HD_DIGITAL_TELEVISION, H264_SD_DIGITAL_TELEVISION, H264_SD_NVOD_TIME_SHIFTED,
    H264_SD_NVOD_REFERENCE, H264_HD_DIGITAL_TELEVISION, H264_HD_NVOD_TIME_SHIFTED, H264_HD_NVOD_REFERENCE,
    H264_HD_3D_DIGITAL_TELEVISION, H264_HD_3D_NVOD_TIME_SHIFTED, H264_HD_3D_NVOD_REFERENCE, HEVC_DIGITAL_TELEVISION,
    HEVC_UHD_DIGITAL_TELEVISION, USER_DEFINED, RESERVED;

    public static ServiceType decodeServiceType(int rawServiceType) {
        if (rawServiceType >= 0x80 && rawServiceType <= 0xFE)
            return ServiceType.USER_DEFINED;
        else
            switch (rawServiceType)
            {
                case 0x01:
                    return ServiceType.DIGITAL_TELEVISION;
                case 0x02:
                    return ServiceType.DIGITAL_RADIO;
                case 0x03:
                    return ServiceType.TELETEXT;
                case 0x04:
                    return ServiceType.NVOD_REFERENCE;
                case 0x05:
                    return ServiceType.NVOD_TIME_SHIFTED;
                case 0x06:
                    return ServiceType.MOSAIC;
                case 0x07:
                    return ServiceType.FM_RADIO;
                case 0x08:
                    return ServiceType.DVB_SRM;     // ETSI TS 102 770 [41])
                case 0x0A:
                    return ServiceType.ADVANCED_CODEC_DIGITAL_RADIO;
                case 0x0B:
                    return ServiceType.H264_MOSAIC;
                case 0x0C:
                    return ServiceType.DATA_BROADCAST;
                case 0x0D:
                    return ServiceType.RESERVED_FOR_COMMON_INTERFACE;
                case 0x0E:
                    return ServiceType.RCS_MAP;     // ETSI EN 301 790 [7]
                case 0x0F:
                    return ServiceType.RCS_FLS;     // ETSI EN 301 790 [7];
                case 0x10:
                    return ServiceType.DVB_MHP;
                case 0x11:
                    return ServiceType.MPEG2_HD_DIGITAL_TELEVISION;
                case 0x16:
                    return ServiceType.H264_SD_DIGITAL_TELEVISION;
                case 0x17:
                    return ServiceType.H264_SD_NVOD_TIME_SHIFTED;
                case 0x18:
                    return ServiceType.H264_SD_NVOD_REFERENCE;
                case 0x19:
                    return ServiceType.H264_HD_DIGITAL_TELEVISION;
                case 0x1A:
                    return ServiceType.H264_HD_NVOD_TIME_SHIFTED;
                case 0x1B:
                    return ServiceType.H264_HD_NVOD_REFERENCE;
                case 0x1C:
                    return ServiceType.H264_HD_3D_DIGITAL_TELEVISION;
                case 0x1D:
                    return ServiceType.H264_HD_3D_NVOD_TIME_SHIFTED;
                case 0x1E:
                    return ServiceType.H264_HD_3D_NVOD_REFERENCE;
                case 0x1F:
                    return ServiceType.HEVC_DIGITAL_TELEVISION;
                case 0x20:
                    return ServiceType.HEVC_UHD_DIGITAL_TELEVISION;
                default:
                    return ServiceType.RESERVED;
            }
    }

}
