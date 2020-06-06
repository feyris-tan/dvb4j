package moe.yo3explorer.dvb4j.model.descriptorEntities;

import moe.yo3explorer.dvb4j.model.enums.AnnouncementType;
import moe.yo3explorer.dvb4j.model.enums.ReferenceType;

public class AnnouncementSupportOnDifferentStream extends AnnouncementSupport
{

    private final int originalNetworkId;
    private final int transportStreamId;
    private final int serviceId;
    private final int componentTag;

    public AnnouncementSupportOnDifferentStream(AnnouncementType announcementType, ReferenceType referenceType, int originalNetworkId, int transportStreamId, int serviceId, int componentTag) {
        super(announcementType,referenceType);
        this.originalNetworkId = originalNetworkId;
        this.transportStreamId = transportStreamId;
        this.serviceId = serviceId;
        this.componentTag = componentTag;
    }

    public int getOriginalNetworkId() {
        return originalNetworkId;
    }

    public int getTransportStreamId() {
        return transportStreamId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public int getComponentTag() {
        return componentTag;
    }

    @Override
    public String toString() {
        return "AnnouncementSupportOnDifferentStream{" +
                "originalNetworkId=" + originalNetworkId +
                ", transportStreamId=" + transportStreamId +
                ", serviceId=" + serviceId +
                ", componentTag=" + componentTag +
                '}';
    }
}
