package moe.yo3explorer.dvb4j.model;

public class NITMetadata {
    private final int transportStreamId;
    private final int originalNetworkId;

    public NITMetadata(int transportStreamId, int originalNetworkId) {

        this.transportStreamId = transportStreamId;
        this.originalNetworkId = originalNetworkId;
    }

    public int getTransportStreamId() {
        return transportStreamId;
    }

    public int getOriginalNetworkId() {
        return originalNetworkId;
    }

    @Override
    public String toString() {
        return "NITMetadata{" +
                "transportStreamId=" + transportStreamId +
                ", originalNetworkId=" + originalNetworkId +
                '}';
    }
}
