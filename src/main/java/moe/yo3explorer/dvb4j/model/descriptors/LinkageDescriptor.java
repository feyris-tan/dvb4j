package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbException;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.LinkageType;

import java.nio.ByteBuffer;

public class LinkageDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x4A;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        transportStreamId = wrap.getShort() & 0xffff;
        originalNetworkId = wrap.getShort() & 0xffff;
        serviceId = wrap.getShort() & 0xffff;
        linkageType = wrap.get() & 0xff;
        if (linkageType == 0x08)
        {
            readMobileHandoverInfo();
        }
        else if (linkageType == 0x0D)
        {
            readEventLinkageInfo();
        }
        else if (linkageType >= 0x0E && linkageType <= 0x1F)
        {
            readExtendedEventLinkageInfo();
        }
    }

    private void readMobileHandoverInfo()
    {
        throw new DvbException("Mobile Hand-over not implemented.");
    }

    private void readEventLinkageInfo()
    {
        throw new DvbException("Event Linkage Info not implemented.");
    }

    private void readExtendedEventLinkageInfo()
    {
        throw new DvbException("Extended Event Linkage Info not implemented.");
    }


    private int transportStreamId;
    private int originalNetworkId;
    private int serviceId;
    private int linkageType;

    public int getTransportStreamId() {
        return transportStreamId;
    }

    public int getOriginalNetworkId() {
        return originalNetworkId;
    }

    public LinkageType getLinkageType()
    {
        if (linkageType >= 0x0E && linkageType <= 0x1F)
            return LinkageType.EXTENDED_EVENT_LINKAGE;
        if (linkageType >= 0x80 && linkageType <= 0xFE)
            return LinkageType.USER_DEFINED;

        switch (linkageType)
        {
            case 0x01:
                return LinkageType.INFORMATION;
            case 0x02:
                return LinkageType.EPG;
            case 0x03:
                return LinkageType.CA_REPLACEMENT;
            case 0x04:
                return LinkageType.TS_CONTAINING_SI;
            case 0x05:
                return LinkageType.SERVICE_REPLACEMENT;
            case 0x06:
                return LinkageType.DATA_BROADCAST;
            case 0x07:
                return LinkageType.RCS_MAP;
            case 0x08:
                return LinkageType.MOBILE_HANDOVER;
            case 0x09:
                return LinkageType.SYSTEM_SOFTWARE_UPDATE;
            case 0x0A:
                return LinkageType.TS_CONTAINING_SSU;
            case 0x0B:
                return LinkageType.IP_MAC_NOTIFICATION;
            case 0x0C:
                return LinkageType.TS_CONTAINING_INT;
            case 0x0D:
                return LinkageType.EVENT_LINKAGE;
            case 0x20:
                return LinkageType.DOWNLOADABLE_FONT_INFO;
            default:
                return LinkageType.RESERVED;
        }
    }

    @Override
    public String toString() {
        return String.format("Linkage Descriptor TSID: %04X, Original Network ID: %04X, Linkage Type: %s",transportStreamId,originalNetworkId,getLinkageType());
    }
}
