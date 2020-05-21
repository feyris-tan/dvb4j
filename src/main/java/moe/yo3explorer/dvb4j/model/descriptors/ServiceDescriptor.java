package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.ServiceType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ServiceDescriptor implements Descriptor {
    private ServiceType serviceType;
    private String serviceProviderName;
    private String serviceName;

    @Override
    public int getTag() {
        return 0x48;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        int rawServiceType = wrap.get() & 0xff;

        decodeServiceType(rawServiceType);

        int serviceProviderNameLength = wrap.get() & 0xff;
        byte[] serviceProviderNameRaw = new byte[serviceProviderNameLength];
        wrap.get(serviceProviderNameRaw);
        this.serviceProviderName = new String(serviceProviderNameRaw, StandardCharsets.US_ASCII);

        int serviceNameLength = wrap.get() & 0xff;
        byte[] serviceNameRaw = new byte[serviceNameLength];
        wrap.get(serviceNameRaw);
        this.serviceName = new String(serviceNameRaw, StandardCharsets.US_ASCII);
    }

    private void decodeServiceType(int rawServiceType) {
        if (rawServiceType >= 0x80 && rawServiceType <= 0xFE)
            this.serviceType = ServiceType.USER_DEFINED;
        else
            switch (rawServiceType)
            {
                case 0x01:
                    this.serviceType = ServiceType.DIGITAL_TELEVISION;
                    break;
                case 0x02:
                    this.serviceType = ServiceType.DIGITAL_RADIO;
                    break;
                case 0x03:
                    this.serviceType = ServiceType.TELETEXT;
                    break;
                case 0x04:
                    this.serviceType = ServiceType.NVOD_REFERENCE;
                    break;
                case 0x05:
                    this.serviceType = ServiceType.NVOD_TIME_SHIFTED;
                    break;
                case 0x06:
                    this.serviceType = ServiceType.MOSAIC;
                    break;
                case 0x07:
                    this.serviceType = ServiceType.FM_RADIO;
                    break;
                case 0x08:
                    this.serviceType = ServiceType.DVB_SRM;     // ETSI TS 102 770 [41])
                    break;
                case 0x0A:
                    this.serviceType = ServiceType.ADVANCED_CODEC_DIGITAL_RADIO;
                    break;
                case 0x0B:
                    this.serviceType = ServiceType.H264_MOSAIC;
                    break;
                case 0x0C:
                    this.serviceType = ServiceType.DATA_BROADCAST;
                    break;
                case 0x0D:
                    this.serviceType = ServiceType.RESERVED_FOR_COMMON_INTERFACE;
                    break;
                case 0x0E:
                    this.serviceType = ServiceType.RCS_MAP;     // ETSI EN 301 790 [7]
                    break;
                case 0x0F:
                    this.serviceType = ServiceType.RCS_FLS;     // ETSI EN 301 790 [7];
                    break;
                case 0x10:
                    this.serviceType = ServiceType.DVB_MHP;
                    break;
                case 0x11:
                    this.serviceType = ServiceType.MPEG2_HD_DIGITAL_TELEVISION;
                    break;
                case 0x16:
                    this.serviceType = ServiceType.H264_SD_DIGITAL_TELEVISION;
                    break;
                case 0x17:
                    this.serviceType = ServiceType.H264_SD_NVOD_TIME_SHIFTED;
                    break;
                case 0x18:
                    this.serviceType = ServiceType.H264_SD_NVOD_REFERENCE;
                    break;
                case 0x19:
                    this.serviceType = ServiceType.H264_HD_DIGITAL_TELEVISION;
                    break;
                case 0x1A:
                    this.serviceType = ServiceType.H264_HD_NVOD_TIME_SHIFTED;
                    break;
                case 0x1B:
                    this.serviceType = ServiceType.H264_HD_NVOD_REFERENCE;
                    break;
                case 0x1C:
                    this.serviceType = ServiceType.H264_HD_3D_DIGITAL_TELEVISION;
                    break;
                case 0x1D:
                    this.serviceType = ServiceType.H264_HD_3D_NVOD_TIME_SHIFTED;
                    break;
                case 0x1E:
                    this.serviceType = ServiceType.H264_HD_3D_NVOD_REFERENCE;
                    break;
                case 0x1F:
                    this.serviceType = ServiceType.HEVC_DIGITAL_TELEVISION;
                    break;
                case 0x20:
                    this.serviceType = ServiceType.HEVC_UHD_DIGITAL_TELEVISION;
                    break;
                default:
                    this.serviceType = ServiceType.RESERVED;
                    break;
            }
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "serviceType=" + serviceType +
                ", serviceProviderName='" + serviceProviderName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
