package moe.yo3explorer.dvb4j.model.descriptorEntities;

public class CRID {
    private final int cridType;
    private final int cridLocation;
    private int cridRef;
    private byte[] cridBuffer;

    public CRID(int cridType, int cridLocation, byte[] cridBuffer) {

        this.cridType = cridType;
        this.cridLocation = cridLocation;
        this.cridBuffer = cridBuffer;
    }

    public CRID(int cridType, int cridLocation, int cridRef) {

        this.cridType = cridType;
        this.cridLocation = cridLocation;
        this.cridRef = cridRef;
    }

    public int getCridType() {
        return cridType;
    }

    public int getCridLocation() {
        return cridLocation;
    }

    public int getCridRef() {
        return cridRef;
    }

    public byte[] getCridBuffer() {
        return cridBuffer;
    }

    @Override
    public String toString() {
        return "CRID{" +
                "cridType=" + cridType +
                ", cridLocation=" + cridLocation +
                '}';
    }
}
