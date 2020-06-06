package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class HierarchyDescriptor implements Descriptor {

    private boolean noViewScalability;
    private boolean noTemporalScalability;
    private boolean noSpatialScalability;
    private boolean noQualityScalability;
    private int hierarchyType;
    private int hierarchyIndex;
    private boolean trefPresent;
    private int hierarchyEmbeddedLayerIndex;
    private int hierarchyChannel;

    @Override
    public int getTag() {
        return 4;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        byte flags = wrap.get();

        noViewScalability = (flags & 0x80) != 0;
        noTemporalScalability = (flags & 0x40) != 0;
        noSpatialScalability = (flags & 0x20) != 0;
        noQualityScalability = (flags & 0x10) != 0;
        hierarchyType = (flags & 0x0f);

        flags = wrap.get();
        hierarchyIndex = (flags & 0x3f);

        flags = wrap.get();
        trefPresent = (flags & 0x80) != 0;
        hierarchyEmbeddedLayerIndex = (flags & 0x3f);

        flags = wrap.get();
        hierarchyChannel = (flags & 0x3f);
    }

    public boolean isNoViewScalability() {
        return noViewScalability;
    }

    public boolean isNoTemporalScalability() {
        return noTemporalScalability;
    }

    public boolean isNoSpatialScalability() {
        return noSpatialScalability;
    }

    public boolean isNoQualityScalability() {
        return noQualityScalability;
    }

    public int getHierarchyType() {
        return hierarchyType;
    }

    public int getHierarchyIndex() {
        return hierarchyIndex;
    }

    public boolean isTrefPresent() {
        return trefPresent;
    }

    public int getHierarchyEmbeddedLayerIndex() {
        return hierarchyEmbeddedLayerIndex;
    }

    public int getHierarchyChannel() {
        return hierarchyChannel;
    }

    @Override
    public String toString() {
        return "HierarchyDescriptor{" +
                "noViewScalability=" + noViewScalability +
                ", noTemporalScalability=" + noTemporalScalability +
                ", noSpatialScalability=" + noSpatialScalability +
                ", noQualityScalability=" + noQualityScalability +
                ", hierarchyType=" + hierarchyType +
                ", hierarchyIndex=" + hierarchyIndex +
                ", trefPresent=" + trefPresent +
                ", hierarchyEmbeddedLayerIndex=" + hierarchyEmbeddedLayerIndex +
                ", hierarchyChannel=" + hierarchyChannel +
                '}';
    }
}
