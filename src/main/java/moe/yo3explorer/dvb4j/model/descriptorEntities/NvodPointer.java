package moe.yo3explorer.dvb4j.model.descriptorEntities;

public class NvodPointer
{

    private final int transportStreamId;
    private final int originalNetworkId;
    private final int serviceId;

    public NvodPointer(int transportStreamId, int originalNetworkId, int serviceId) {

        this.transportStreamId = transportStreamId;
        this.originalNetworkId = originalNetworkId;
        this.serviceId = serviceId;
    }

    public int getTransportStreamId() {
        return transportStreamId;
    }

    public int getOriginalNetworkId() {
        return originalNetworkId;
    }

    public int getServiceId() {
        return serviceId;
    }

    @Override
    public String toString() {
        return "NvodPointer{" +
                "transportStreamId=" + transportStreamId +
                ", originalNetworkId=" + originalNetworkId +
                ", serviceId=" + serviceId +
                '}';
    }
}
