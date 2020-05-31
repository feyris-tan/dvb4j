package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.SDTEntry;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SDTDecoder implements PSIDecoder {
    private DvbReceiver dvbReceiver;
    protected static boolean[] foundServices;

    public SDTDecoder(DvbReceiver dvbReceiver) {
        this.dvbReceiver = dvbReceiver;
        if (foundServices == null)
            this.foundServices = new boolean[65536];
    }


    @Override
    public int[] getTableIds() {
        return new int[] {0x42,0x46};
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        ByteBuffer psiRawWrap = ByteBuffer.wrap(psiSection.getData());
        int tsId = psiRawWrap.getShort(3);

        int psiTableId = psiSection.getTableId();
        ByteBuffer payload = psiSection.getPayload();
        int originalNetworkId = payload.getShort() & 0xffff;
        byte reservedFutureUsed = payload.get();
        for (; payload.position() < payload.limit();)
        {
            int serviceId = payload.getShort() & 0xffff;
            byte eitFlags = payload.get();
            boolean eitScheduleFlag = (eitFlags & 0x02) != 0;
            boolean eitPresentFollowingFlag = (eitFlags & 0x01) != 0;
            short flags2 = payload.getShort();
            int runningStatus = (flags2 >> 13) & 0x07;
            boolean freeCaMode = (flags2 & 0x1000) != 0;
            int descriptorsLength = (flags2 & 0x0FFF);

            if (descriptorsLength > payload.limit())
                return;

            ArrayList<Descriptor> descriptors = new ArrayList<>();
            while (descriptorsLength > 0)
            {
                int descriptorId = payload.get() & 0xff;
                int descriptorLength = payload.get() & 0xff;
                descriptorsLength -= 2;

                if (descriptorId == 0x61 || descriptorId == 0x40 || descriptorId == 0x63)
                {
                    //Laut ETSI 300468 Seite 39 kommen die nicht in SDT vor
                    return;
                }
                if (descriptorId == 0x00 || descriptorId == 0x01)
                {
                    //reserviert und verboten!
                    return;
                }
                if (descriptorLength <= descriptorsLength)
                {
                    byte[] descriptorBuffer = new byte[descriptorLength];
                    payload.get(descriptorBuffer);
                    descriptors.add(DescriptorDecoder.autoDecode(descriptorId,psiTableId,descriptorBuffer));
                    descriptorsLength -= descriptorLength;
                }
            }
            if (!foundServices[serviceId])
            {
                SDTEntry sdtEntry = new SDTEntry(serviceId,eitScheduleFlag,eitPresentFollowingFlag, runningStatus,freeCaMode,descriptors,originalNetworkId,tsId);
                dvbReceiver.onSdtEntry(sdtEntry);
                foundServices[serviceId] = true;
            }
        }
    }
}
