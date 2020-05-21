package moe.yo3explorer.dvb4j.model;

import java.util.Date;

public class LocalTimeOffset {
    private final String countryCode;
    private final int countryRegionId;
    private final boolean localTimeOffsetPolariyNegative;
    private final int localTimeOffset;
    private final Date timeOfChange;
    private final int nextTimeOffset;

    public LocalTimeOffset(String countryCode, int countryRegionId, boolean localTimeOffsetPolariyNegative, int localTimeOffset, Date timeOfChange, int nextTimeOffset) {
        this.countryCode = countryCode;
        this.countryRegionId = countryRegionId;
        this.localTimeOffsetPolariyNegative = localTimeOffsetPolariyNegative;
        this.localTimeOffset = localTimeOffset;
        this.timeOfChange = timeOfChange;
        this.nextTimeOffset = nextTimeOffset;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getCountryRegionId() {
        return countryRegionId;
    }

    public boolean isLocalTimeOffsetPolariyNegative() {
        return localTimeOffsetPolariyNegative;
    }

    public int getLocalTimeOffset() {
        return localTimeOffset;
    }

    public Date getTimeOfChange() {
        return timeOfChange;
    }

    public int getNextTimeOffset() {
        return nextTimeOffset;
    }

    @Override
    public String toString() {
        return "LocalTimeOffset{" +
                "countryCode='" + countryCode + '\'' +
                ", countryRegionId=" + countryRegionId +
                ", localTimeOffset=" + localTimeOffset +
                ", timeOfChange=" + timeOfChange +
                ", nextTimeOffset=" + nextTimeOffset +
                '}';
    }
}
