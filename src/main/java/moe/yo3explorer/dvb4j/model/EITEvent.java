package moe.yo3explorer.dvb4j.model;

import moe.yo3explorer.dvb4j.decoders.EITDecoder;
import moe.yo3explorer.dvb4j.model.enums.RunningStatus;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EITEvent
{
    private final int transportStreamId;
    private final int originalNetworkid;
    private final int eventId;
    private final Date startTime;
    private final long duration;
    private final RunningStatus runningStatus;
    private final boolean encrypted;
    private final List<Descriptor> descriptorList;
    private final int serviceId;

    public EITEvent(int transportStreamId, int originalNetworkid, int eventId, Date startTime, long duration, RunningStatus runningStatus, boolean encrypted, List<Descriptor> descriptorList, int serviceId) {

        this.transportStreamId = transportStreamId;
        this.originalNetworkid = originalNetworkid;
        this.eventId = eventId;
        this.startTime = startTime;
        this.duration = duration;
        this.runningStatus = runningStatus;
        this.encrypted = encrypted;
        this.descriptorList = descriptorList;
        this.serviceId = serviceId;
    }

    public int getTransportStreamId() {
        return transportStreamId;
    }

    public int getOriginalNetworkid() {
        return originalNetworkid;
    }

    public int getEventId() {
        return eventId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return duration;
    }

    public RunningStatus getRunningStatus() {
        return runningStatus;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public List<Descriptor> getDescriptorList() {
        return descriptorList;
    }

    public int getServiceId() {
        return serviceId;
    }

    @Override
    public String toString() {
        return "EITEvent{" +
                "transportStreamId=" + transportStreamId +
                ", serviceId=" + serviceId +
                ", originalNetworkid=" + originalNetworkid +
                ", eventId=" + eventId +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", runningStatus=" + runningStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EITEvent)) return false;
        EITEvent eitEvent = (EITEvent) o;
        return getTransportStreamId() == eitEvent.getTransportStreamId() &&
                getServiceId() == eitEvent.getServiceId() &&
                getOriginalNetworkid() == eitEvent.getOriginalNetworkid() &&
                getEventId() == eitEvent.getEventId() &&
                getStartTime().equals(eitEvent.getStartTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransportStreamId(), getOriginalNetworkid(), getEventId(), getStartTime(),getServiceId());
    }
}
