package moe.yo3explorer.dvb4j.model;

import moe.yo3explorer.dvb4j.model.enums.ServiceType;

import java.util.ArrayList;
import java.util.Objects;

public class BATEntry {
    private final int serviceId;
    private final ServiceType serviceType;
    private final ArrayList<Descriptor> tsDescriptors;
    private final int transportStreamId;
    private final int originalNetworkId;
    private final ArrayList<Descriptor> descriptors;

    public BATEntry(int serviceId, ServiceType serviceType, ArrayList<Descriptor> tsDescriptors, int transportStreamId, int originalNetworkId, ArrayList<Descriptor> descriptors) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.tsDescriptors = tsDescriptors;
        this.transportStreamId = transportStreamId;
        this.originalNetworkId = originalNetworkId;
        this.descriptors = descriptors;
    }

    public int getServiceId() {
        return serviceId;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public ArrayList<Descriptor> getTsDescriptors() {
        return tsDescriptors;
    }

    public int getTransportStreamId() {
        return transportStreamId;
    }

    public int getOriginalNetworkId() {
        return originalNetworkId;
    }

    public ArrayList<Descriptor> getDescriptors() {
        return descriptors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BATEntry)) return false;
        BATEntry batEntry = (BATEntry) o;
        return getServiceId() == batEntry.getServiceId() &&
                getTransportStreamId() == batEntry.getTransportStreamId() &&
                getOriginalNetworkId() == batEntry.getOriginalNetworkId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceId(), getTransportStreamId(), getOriginalNetworkId());
    }

    @Override
    public String toString() {
        return "BATEntry{" +
                "serviceId=" + serviceId +
                ", serviceType=" + serviceType +
                ", transportStreamId=" + transportStreamId +
                ", originalNetworkId=" + originalNetworkId +
                '}';
    }
}
