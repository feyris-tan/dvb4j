package moe.yo3explorer.dvb4j.model.descriptorEntities;

import moe.yo3explorer.dvb4j.model.enums.AnnouncementType;
import moe.yo3explorer.dvb4j.model.enums.ReferenceType;

public class AnnouncementSupport {
    private final AnnouncementType announcementType;
    private final ReferenceType referenceType;

    public AnnouncementSupport(AnnouncementType announcementType, ReferenceType referenceType) {

        this.announcementType = announcementType;
        this.referenceType = referenceType;
    }

    public AnnouncementType getAnnouncementType() {
        return announcementType;
    }

    public ReferenceType getReferenceType() {
        return referenceType;
    }

    @Override
    public String toString() {
        return "AnnouncementSupport{" +
                "announcementType=" + announcementType +
                ", referenceType=" + referenceType +
                '}';
    }
}
