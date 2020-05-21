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
    public int getTableId() {
        return 0x42;
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
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
            ArrayList<Descriptor> descriptors = new ArrayList<>();
            while (descriptorsLength > 0)
            {
                int descriptorId = payload.get() & 0xff;
                int descriptorLength = payload.get() & 0xff;
                descriptorsLength -= 2;
                if (descriptorLength <= descriptorsLength)
                {
                    byte[] descriptorBuffer = new byte[descriptorLength];
                    payload.get(descriptorBuffer);
                    descriptors.add(DescriptorDecoder.autoDecode(descriptorId,getTableId(),descriptorBuffer));
                    descriptorsLength -= descriptorLength;
                }
            }
            if (!foundServices[serviceId])
            {
                SDTEntry sdtEntry = new SDTEntry(serviceId,eitScheduleFlag,eitPresentFollowingFlag, runningStatus,freeCaMode,descriptors,originalNetworkId);
                dvbReceiver.onSdtEntry(sdtEntry);
                foundServices[serviceId] = true;
            }
        }
    }
}
