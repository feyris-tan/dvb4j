package moe.yo3explorer.dvb4j.model;

import moe.yo3explorer.dvb4j.model.enums.TvAnywhereRunningStatus;

public class TvAnywhereId {
    private final int tvaId;
    private final TvAnywhereRunningStatus runningStatus;

    public TvAnywhereId(int tvaId, TvAnywhereRunningStatus runningStatus) {
        this.tvaId = tvaId;
        this.runningStatus = runningStatus;
    }

    public int getTvaId() {
        return tvaId;
    }

    public TvAnywhereRunningStatus getRunningStatus() {
        return runningStatus;
    }

    @Override
    public String toString() {
        return "TvAnywhereId{" +
                "tvaId=" + tvaId +
                ", runningStatus=" + runningStatus +
                '}';
    }
}
