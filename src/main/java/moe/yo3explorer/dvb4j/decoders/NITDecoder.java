package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptors.SatelliteDeliverySystemDescriptor;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.*;

public class NITDecoder implements PSIDecoder {
    public NITDecoder(DvbReceiver dvbReceiver)
    {
        this.dvbReceiver = dvbReceiver;
    }

    private DvbReceiver dvbReceiver;

    @Override
    public int[] getTableIds() {
        return new int[] {0x40, 0x41};
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        int psiTableId = psiSection.getTableId();

        List<Descriptor> networkDescriptors = new ArrayList<>();
        if (satelliteDeliverySystemDescriptors == null)
            satelliteDeliverySystemDescriptors = new HashSet<>();

        ByteBuffer payload = psiSection.getPayload();
        int nwDescriptorsRemain = payload.getShort() & 0x0fff;
        while (nwDescriptorsRemain > 0)
        {
            int nwDescriptorId = payload.get() & 0xff;
            int nwDescriptorLen = payload.get() & 0xff;
            nwDescriptorsRemain -= 2;

            byte[] nwDescriptorBuffer = new byte[nwDescriptorLen];
            payload.get(nwDescriptorBuffer);
            networkDescriptors.add(DescriptorDecoder.autoDecode(nwDescriptorId,psiTableId,nwDescriptorBuffer));
            nwDescriptorsRemain -= nwDescriptorLen;
        }

        int tsLoopRemain = payload.getShort() & 0x0fff;
        while (tsLoopRemain > 0)
        {
            ArrayList<Descriptor> tsDescriptors = new ArrayList<>();
            int transportStreamId = payload.getShort() & 0xffff;
            int originalNetworkId = payload.getShort() & 0xffff;
            int transportDescriptorsRemain = payload.getShort() & 0x0fff;
            tsLoopRemain -= 6;

            while (transportDescriptorsRemain > 0)
            {
                int tDescriptorId = payload.get() & 0xff;
                int tDescriptorLen = payload.get() & 0xff;
                transportDescriptorsRemain -= 2;
                tsLoopRemain -= 2;

                byte[] tDescriptorBuffer = new byte[tDescriptorLen];
                payload.get(tDescriptorBuffer);
                tsDescriptors.add(DescriptorDecoder.autoDecode(tDescriptorId,psiTableId,tDescriptorBuffer));
                transportDescriptorsRemain -= tDescriptorLen;
                tsLoopRemain -= tDescriptorLen;
            }

            Optional<SatelliteDeliverySystemDescriptor> first = tsDescriptors.stream().filter(x -> x.getTag() == 0x43).map(x -> (SatelliteDeliverySystemDescriptor) x).findFirst();
            if (first.isPresent())
            {
                SatelliteDeliverySystemDescriptor satelliteDeliverySystemDescriptor = first.get();
                tsDescriptors.remove(satelliteDeliverySystemDescriptor);
                if (satelliteDeliverySystemDescriptors.add(satelliteDeliverySystemDescriptor))
                    dvbReceiver.onNetworkInformation(satelliteDeliverySystemDescriptor,tsDescriptors,networkDescriptors);
            }
        }
    }

    private HashSet<SatelliteDeliverySystemDescriptor> satelliteDeliverySystemDescriptors;
}
