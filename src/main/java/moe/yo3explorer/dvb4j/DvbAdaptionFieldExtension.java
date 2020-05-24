package moe.yo3explorer.dvb4j;

class DvbAdaptionFieldExtension
{

    private final boolean ltwFlag;
    private final boolean piecewiseRateFlag;
    private final boolean seamlessSpliceFlag;
    private final Boolean ltwValidFlag;
    private final Integer ltwOffset;
    private final Integer piecewiseRate;
    private final Integer spliceType;
    private final Long dtsNextAccessUnit;

    public DvbAdaptionFieldExtension(boolean ltwFlag, boolean piecewiseRateFlag, boolean seamlessSpliceFlag, Boolean ltwValidFlag, Integer ltwOffset, Integer piecewiseRate, Integer spliceType, Long dtsNextAccessUnit) {

        this.ltwFlag = ltwFlag;
        this.piecewiseRateFlag = piecewiseRateFlag;
        this.seamlessSpliceFlag = seamlessSpliceFlag;
        this.ltwValidFlag = ltwValidFlag;
        this.ltwOffset = ltwOffset;
        this.piecewiseRate = piecewiseRate;
        this.spliceType = spliceType;
        this.dtsNextAccessUnit = dtsNextAccessUnit;
    }

    public boolean isLtwFlag() {
        return ltwFlag;
    }

    public boolean isPiecewiseRateFlag() {
        return piecewiseRateFlag;
    }

    public boolean isSeamlessSpliceFlag() {
        return seamlessSpliceFlag;
    }

    public Boolean getLtwValidFlag() {
        return ltwValidFlag;
    }

    public Integer getLtwOffset() {
        return ltwOffset;
    }

    public Integer getPiecewiseRate() {
        return piecewiseRate;
    }

    public Integer getSpliceType() {
        return spliceType;
    }

    public Long getDtsNextAccessUnit() {
        return dtsNextAccessUnit;
    }

    @Override
    public String toString() {
        return "DvbAdaptionFieldExtension{" +
                "ltwFlag=" + ltwFlag +
                ", piecewiseRateFlag=" + piecewiseRateFlag +
                ", seamlessSpliceFlag=" + seamlessSpliceFlag +
                ", ltwValidFlag=" + ltwValidFlag +
                '}';
    }
}
