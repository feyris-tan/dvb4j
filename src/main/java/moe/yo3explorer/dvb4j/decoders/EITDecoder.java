package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.EITEvent;
import moe.yo3explorer.dvb4j.model.enums.RunningStatus;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.*;

public class EITDecoder implements PSIDecoder {
    public EITDecoder(DvbReceiver dvbReceiver) {
        this.dvbReceiver = dvbReceiver;
        this.events = new HashSet<>();
    }

    private DvbReceiver dvbReceiver;

    @Override
    public int[] getTableIds() {
        return new int[] {
                /* actual TS, present  */ 0x4E,
                /* other TS,  present  */ 0x4F,
                /* actual TS, schedule */ 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5A, 0x5B, 0x5C, 0x5D, 0x5E, 0x5F,
                /* other TS, schedule  */ 0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F
        };
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        if (psiSection.getRawDataLength() <= 4)
            return;

        int tableId = psiSection.getTableId();
        int serviceId = psiSection.getServiceId();

        ByteBuffer payload = psiSection.getPayload();
        if (payload.limit() <= 5)
            return;
        int transportStreamId = payload.getShort() & 0xffff;
        int originalNetworkid = payload.getShort() & 0xffff;
        int segmentLastSectionNumber = payload.get() & 0xff;
        int lastTableId = payload.get() & 0xff;
        while (payload.position() < payload.limit())
        {
            int eventId = payload.getShort() & 0xffff;
            Date startTime = DvbTimeConverter.parseTime(payload);
            if (startTime == null)
                return;
            long duration = DvbTimeConverter.parseDuration(payload);
            if (duration == -1)
                return;

            short flags = payload.getShort();
            int runningStatusRaw = (flags >> 13) & 0x07;
            RunningStatus runningStatus = RunningStatus.values()[runningStatusRaw];

            boolean encrypted = (flags & 0x1000) != 0;

            List<Descriptor> descriptorList = new LinkedList<>();
            int descriptorsRemain = flags & 0x0fff;
            while (descriptorsRemain > 0)
            {
                int descriptorId = payload.get() & 0xff;
                if (descriptorId == 0 || descriptorId == 1)
                    return;
                if (descriptorId == 34 || descriptorId == 25 || descriptorId == 41 || descriptorId == 24)
                {
                    //Der ergibt keinen Sinn in einer EIT... aber ist das korrekt?
                    return;
                }
                if (descriptorId == 0x6c || descriptorId == 0x51)
                {
                    //Ist laut ETSI 300468, Seite 39 nicht erlaubt
                    return;
                }
                int descriptorLength = payload.get() & 0xff;
                descriptorsRemain -= 2;

                int remain = payload.limit() - payload.position();
                if (descriptorLength > remain)
                    return;

                byte[] descriptorBuffer = new byte[descriptorLength];
                payload.get(descriptorBuffer);
                descriptorsRemain -= descriptorLength;
                descriptorList.add(DescriptorDecoder.autoDecode(descriptorId,tableId,descriptorBuffer));
            }

            EITEvent eitEvent = new EITEvent(transportStreamId,originalNetworkid,eventId,startTime,duration,runningStatus,encrypted,descriptorList,serviceId);
            if (events.add(eitEvent))
                dvbReceiver.onScheduledEvent(eitEvent);
        }
    }

    private HashSet<EITEvent> events;

}
