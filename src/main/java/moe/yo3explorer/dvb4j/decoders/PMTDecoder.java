package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbContext;
import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.PMTEntry;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class PMTDecoder implements PSIDecoder {
    public PMTDecoder(DvbContext context)
    {
        this.context = context;
    }

    DvbContext context;

    @Override
    public int getTableId() {
        return 2;
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        if (seenPids == null)
            seenPids = new boolean[8192];

        if (!psiSection.check(2))
            return;

        ByteBuffer payload = psiSection.getPayload();
        byte discard0 = payload.get();
        byte discard1 = payload.get();
        int lenA = payload.get() & 0x0f;
        lenA <<= 8;
        int lenB = payload.get() & 0xff;
        int len = lenA | lenB;
        ArrayList<Descriptor> globalDescriptors = new ArrayList<>();
        while (len > 0)
        {
            int descriptorTag = payload.get() & 0xff;
            int descriptorLength = payload.get() & 0xff;
            len -= 2;
            if (descriptorLength <= len) {
                byte[] descriptorBuffer = new byte[descriptorLength];
                payload.get(descriptorBuffer);
                globalDescriptors.add(DescriptorDecoder.autoDecode(descriptorTag,getTableId(),descriptorBuffer));
            }
            len -= descriptorLength;
        }

        while (payload.position() < payload.limit())
        {
            int type = payload.get() & 0xff;
            int a = payload.get() & 0x1f;
            a <<= 8;
            int b = payload.get() & 0xff;
            int pid = a | b;
            a = payload.get() & 0xf;
            a <<= 8;
            b = payload.get();
            int esLength = a | b;
            if (esLength > (payload.limit() - payload.position()))
                esLength = (payload.limit() - payload.position());
            PMTEntry pmtEntry = new PMTEntry(type,pid);
            while (esLength > 0 )
            {
                int tag = payload.get() & 0xff;
                int tagLength = payload.get() & 0xff;
                esLength -= 2;
                if (esLength >= tagLength)
                {
                    byte[] tagContent = new byte[tagLength];
                    payload.get(tagContent);
                    pmtEntry.addDescriptor(DescriptorDecoder.autoDecode(tag,getTableId(),tagContent));
                }
                esLength -= tagLength;
            }
            if (!seenPids[pid])
            {
                globalDescriptors.stream().forEach(x -> pmtEntry.addDescriptor(x));
                seenPids[pid] = true;
                DvbReceiver dvbReceiver = context.getDvbReceiver();
                if (dvbReceiver != null)
                    dvbReceiver.onNewPmtEntry(context.getLastProcessedPid(),pmtEntry);
            }
        }
    }

    private boolean[] seenPids;
}
