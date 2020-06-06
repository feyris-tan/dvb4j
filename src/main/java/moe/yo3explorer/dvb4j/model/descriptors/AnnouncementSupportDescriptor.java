package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.AnnouncementSupport;
import moe.yo3explorer.dvb4j.model.AnnouncementSupportIndicator;
import moe.yo3explorer.dvb4j.model.AnnouncementSupportOnDifferentStream;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.AnnouncementType;
import moe.yo3explorer.dvb4j.model.enums.ReferenceType;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AnnouncementSupportDescriptor implements Descriptor {
    private AnnouncementSupportIndicator announcementSupportIndicator;

    @Override
    public int getTag() {
        return 0x6e;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        announcementSupportIndicator = new AnnouncementSupportIndicator(wrap.getShort());
        while (wrap.limit() > wrap.position())
        {
            byte flags = wrap.get();
            int announcementTypeRaw = flags & 0xf0;
            int referenceTypeRaw = flags & 0x07;

            AnnouncementType announcementType;
            if (announcementTypeRaw > 7)
                announcementType = AnnouncementType.RESERVED;
            else
                announcementType = AnnouncementType.values()[announcementTypeRaw];

            ReferenceType referenceType;
            if (referenceTypeRaw > 3)
                referenceType = ReferenceType.RESERVED;
            else
                referenceType = ReferenceType.values()[referenceTypeRaw];

            AnnouncementSupport child;
            if (referenceTypeRaw == 0x01 || referenceTypeRaw == 0x02 || referenceTypeRaw == 0x03)
            {
                int originalNetworkId = wrap.getShort() & 0xffff;
                int transportStreamId = wrap.getShort() & 0xffff;
                int serviceId = wrap.getShort() & 0xffff;
                int componentTag = wrap.get() & 0xff;
                child = new AnnouncementSupportOnDifferentStream(announcementType,referenceType,originalNetworkId,transportStreamId,serviceId,componentTag);
            }
            else
            {
                child = new AnnouncementSupport(announcementType,referenceType);
            }
            if (announcementSupportList == null)
                announcementSupportList = new LinkedList<>();
            announcementSupportList.add(child);
        }
    }

    private List<AnnouncementSupport> announcementSupportList;

    public AnnouncementSupportIndicator getAnnouncementSupportIndicator() {
        return announcementSupportIndicator;
    }

    public List<AnnouncementSupport> getAnnouncementSupportList() {
        return Collections.unmodifiableList(announcementSupportList);
    }

    @Override
    public String toString() {
        return "AnnouncementSupportDescriptor{" +
                "announcementSupportIndicator=" + announcementSupportIndicator +
                ", announcementSupportList=" + announcementSupportList +
                '}';
    }
}
