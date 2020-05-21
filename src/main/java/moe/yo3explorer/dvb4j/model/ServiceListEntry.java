package moe.yo3explorer.dvb4j.model;

import moe.yo3explorer.dvb4j.model.enums.ServiceType;

import java.util.Objects;

public class ServiceListEntry
{
    public ServiceListEntry(int serviceId, byte serviceType) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
    }

    private int serviceId;
    private byte serviceType;

    public int getServiceId() {
        return serviceId;
    }

    public ServiceType getServiceType()
    {
        return ServiceType.decodeServiceType(serviceType & 0xff);
    }

    @Override
    public String toString() {
        return "ServiceListEntry{" +
                "serviceId=" + serviceId +
                ", serviceType=" + getServiceType() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceListEntry)) return false;
        ServiceListEntry that = (ServiceListEntry) o;
        return getServiceId() == that.getServiceId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceId());
    }
}
