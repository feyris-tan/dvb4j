package moe.yo3explorer.dvb4j.model;

import java.util.Arrays;

public class OperationPoint {
    private final int applicableTemporalId;
    private final int numTargetOutputViews;
    private final int[] esReferences;

    public OperationPoint(int applicableTemporalId, int numTargetOutputViews, int[] esReferences) {
        this.applicableTemporalId = applicableTemporalId;
        this.numTargetOutputViews = numTargetOutputViews;
        this.esReferences = esReferences;
    }

    public int getApplicableTemporalId() {
        return applicableTemporalId;
    }

    public int getNumTargetOutputViews() {
        return numTargetOutputViews;
    }

    public int[] getEsReferences() {
        return esReferences;
    }

    @Override
    public String toString() {
        return "OperationPoint{" +
                "applicableTemporalId=" + applicableTemporalId +
                ", numTargetOutputViews=" + numTargetOutputViews +
                ", esReferences=" + Arrays.toString(esReferences) +
                '}';
    }
}
