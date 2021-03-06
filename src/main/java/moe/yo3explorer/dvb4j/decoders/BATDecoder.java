package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.BATEntry;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptorEntities.ServiceListEntry;
import moe.yo3explorer.dvb4j.model.descriptors.ServiceListDescriptor;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

public class BATDecoder implements PSIDecoder {

    public BATDecoder(DvbReceiver dvbReceiver) {
        this.dvbReceiver = dvbReceiver;
        this.batEntries = new HashSet<>();
        this.batHits = 0;
    }

    private DvbReceiver dvbReceiver;

    @Override
    public int[] getTableIds() {
        return new int[] {0x4A};
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        ByteBuffer payload = psiSection.getPayload();
        int descriptorsRemain = (int)payload.getShort() & 0x0fff;

        if (payload.limit() < descriptorsRemain)
            return;

        ArrayList<Descriptor> descriptors = new ArrayList<>();
        while (descriptorsRemain > 0)
        {
            int descriptorId = payload.get() & 0xff;
            int descriptorLength = payload.get() & 0xff;
            descriptorsRemain -= 2;

            if (descriptorId == 0x5A || descriptorId == 0x6b)
            {
                //Kann nicht in einer BAT vorkommen.
                return;
            }
            if (descriptorId == 41)
            {
                //Nicht sicher, ob dies in einer BAT vorkommen kann.
                return;
            }

            if (descriptorsRemain >= descriptorLength)
            {
                byte[] descriptorBuffer = new byte[descriptorLength];
                payload.get(descriptorBuffer);
                descriptors.add(DescriptorDecoder.autoDecode(descriptorId,0x4A,descriptorBuffer));
                descriptorsRemain -= descriptorLength;
            }
        }

        int tsLoopRemain = (int)payload.getShort() & 0x0fff;
        while (tsLoopRemain > 0)
        {
            int transportStreamId = payload.getShort() & 0xffff;
            int originalNetworkId = payload.getShort() & 0xffff;
            int transportDescriptorRemain = payload.getShort() & 0x0fff;
            tsLoopRemain -= 6;
            ArrayList<Descriptor> tsDescriptors = new ArrayList<>();
            while (transportDescriptorRemain > 0)
            {
                int descriptorId = payload.get() & 0xff;
                int descriptorLength = payload.get() & 0xff;
                tsLoopRemain -= 2;

                if(descriptorId == 0x55)    //Darf nicht in BAT vorkommen.
                    return;

                transportDescriptorRemain -= 2;
                if (transportDescriptorRemain >= descriptorLength)
                {
                    byte[] descriptorBuffer = new byte[descriptorLength];
                    payload.get(descriptorBuffer);
                    tsDescriptors.add(DescriptorDecoder.autoDecode(descriptorId,0x4A,descriptorBuffer));
                    tsLoopRemain -= descriptorBuffer.length;
                    transportDescriptorRemain -= descriptorBuffer.length;
                }
            }

            Optional<ArrayList<ServiceListEntry>> firstServiceList = tsDescriptors.stream()
                    .filter(Objects::nonNull)
                    .filter(x -> x.getTag() == 0x41)
                    .map(x -> ((ServiceListDescriptor) x).getServices())
                    .findFirst();

            if (firstServiceList.isPresent())
            {
                ArrayList<ServiceListEntry> serviceListEntries = firstServiceList.get();
                for (ServiceListEntry serviceListEntry : serviceListEntries) {
                    BATEntry batEntry = new BATEntry(serviceListEntry.getServiceId(),serviceListEntry.getServiceType(),tsDescriptors,transportStreamId,originalNetworkId,descriptors);
                    if (batEntries.add(batEntry))
                        dvbReceiver.onBouquetAssociation(batEntry);
                    else
                        batHits++;
                }
            }
        }
    }

    private HashSet<BATEntry> batEntries;
    private long batHits;
}
