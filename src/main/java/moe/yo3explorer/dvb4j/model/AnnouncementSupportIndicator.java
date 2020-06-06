package moe.yo3explorer.dvb4j.model;

public class AnnouncementSupportIndicator
{
    private final boolean emergencyAlarm;
    private final boolean roadTrafficFlash;
    private final boolean publicTransportFlash;
    private final boolean warningMessage;
    private final boolean newsFlash;
    private final boolean weatherFlash;
    private final boolean eventAnnouncement;
    private final boolean personalCall;

    public AnnouncementSupportIndicator(short values)
    {
        emergencyAlarm = (values & 0x0001) != 0;
        roadTrafficFlash = (values & 0x0002) != 0;
        publicTransportFlash = (values & 0x0004) != 0;
        warningMessage = (values & 0x0008) != 0;
        newsFlash = (values & 0x0010) != 0;
        weatherFlash = (values & 0x0020) != 0;
        eventAnnouncement = (values & 0x0040) != 0;
        personalCall = (values & 0x0080) != 0;
    }

    public boolean isEmergencyAlarm() {
        return emergencyAlarm;
    }

    public boolean isRoadTrafficFlash() {
        return roadTrafficFlash;
    }

    public boolean isPublicTransportFlash() {
        return publicTransportFlash;
    }

    public boolean isWarningMessage() {
        return warningMessage;
    }

    public boolean isNewsFlash() {
        return newsFlash;
    }

    public boolean isWeatherFlash() {
        return weatherFlash;
    }

    public boolean isEventAnnouncement() {
        return eventAnnouncement;
    }

    public boolean isPersonalCall() {
        return personalCall;
    }

    @Override
    public String toString() {
        return "AnnouncementSupportIndicator{" +
                "emergencyAlarm=" + emergencyAlarm +
                ", roadTrafficFlash=" + roadTrafficFlash +
                ", publicTransportFlash=" + publicTransportFlash +
                ", warningMessage=" + warningMessage +
                ", newsFlash=" + newsFlash +
                ", weatherFlash=" + weatherFlash +
                ", eventAnnouncement=" + eventAnnouncement +
                ", personalCall=" + personalCall +
                '}';
    }
}
