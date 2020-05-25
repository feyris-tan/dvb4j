package moe.yo3explorer.dvb4j.model;

import moe.yo3explorer.dvb4j.model.descriptors.ServiceDescriptor;
import moe.yo3explorer.dvb4j.model.enums.RunningStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SDTEntry {
    private final int serviceId;
    private final boolean eitSchedule;
    private final boolean eitPresentFollowing;
    private final RunningStatus runningStatus;
    private final boolean freeCaMode;
    private final List<Descriptor> descriptors;
    private final int originalNetworkId;

    public SDTEntry(int serviceId, boolean eitScheduleFlag, boolean eitPresentFollowingFlag, int runningStatus, boolean freeCaMode, ArrayList<Descriptor> descriptors, int originalNetworkId) {
        this.serviceId = serviceId;
        this.eitSchedule = eitScheduleFlag;
        this.eitPresentFollowing = eitPresentFollowingFlag;
        switch (runningStatus)
        {
            case 0:
                this.runningStatus = RunningStatus.UNDEFINED;
                break;
            case 1:
                this.runningStatus = RunningStatus.NOT_RUNNING;
                break;
            case 2:
                this.runningStatus = RunningStatus.STARTS_IN_A_FEW_SECONDS;
                break;
            case 3:
                this.runningStatus = RunningStatus.PAUSING;
                break;
            case 4:
                this.runningStatus = RunningStatus.RUNNING;
                break;
            case 5:
                this.runningStatus = RunningStatus.SERVICE_OFF_AIR;
                break;
            case 6:
            case 7:
                this.runningStatus = RunningStatus.RESERVED;
                break;
            default:
                throw new IllegalArgumentException("Maximum RunningStatus is 7!");
        }
        this.freeCaMode = freeCaMode;
        this.descriptors = Collections.unmodifiableList(descriptors);
        this.originalNetworkId = originalNetworkId;
    }

    public String getChannelName()
    {
        Optional<String> first = descriptors.stream().filter(x -> x.getTag() == 0x48).map(x -> ((ServiceDescriptor) x).getServiceName()).findFirst();
        return first.orElse("???");
    }

    public int getServiceId() {
        return serviceId;
    }

    public boolean isEitSchedule() {
        return eitSchedule;
    }

    public boolean isEitPresentFollowing() {
        return eitPresentFollowing;
    }

    public RunningStatus getRunningStatus() {
        return runningStatus;
    }

    public boolean isFreeCaMode() {
        return freeCaMode;
    }

    public List<Descriptor> getDescriptors() {
        return descriptors;
    }

    public int getOriginalNetworkId() {
        return originalNetworkId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SDTEntry{");
        sb.append("serviceId=").append(serviceId);
        sb.append(", runningStatus=").append(runningStatus);
        sb.append(String.format(", originalNetworkId=%04X",originalNetworkId));
        sb.append(String.format(", channelName=%s",getChannelName()));
        sb.append('}');
        return sb.toString();
    }
}
