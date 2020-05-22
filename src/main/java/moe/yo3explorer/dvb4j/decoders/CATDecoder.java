package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbException;
import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptors.CaDescriptor;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.HashSet;

public class CATDecoder implements PSIDecoder
{
    public CATDecoder(DvbReceiver dvbReceiver) {
        this.receiver = dvbReceiver;
    }

    private DvbReceiver receiver;

    @Override
    public int getTableId() {
        return 1;
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        ByteBuffer payload = psiSection.getPayload();
        for (; payload.position() < payload.limit();)
        {
            int descriptorId = payload.get() & 0xff;
            int descriptorLength = payload.get() & 0xff;
            if (descriptorLength <= payload.limit() - payload.position())
            {
                byte[] descriptorBuffer = new byte[descriptorLength];
                payload.get(descriptorBuffer);
                Descriptor descriptor = DescriptorDecoder.autoDecode(descriptorId, getTableId(), descriptorBuffer);
                if (descriptor.getTag() == 9)
                {
                    if (knownDescriptors == null)
                        knownDescriptors = new HashSet<>();
                    CaDescriptor caDescriptor = (CaDescriptor)descriptor;
                    if (knownDescriptors.add(caDescriptor))
                    {
                        receiver.onNewCaDescriptor(caDescriptor);
                    }
                }
                else
                {
                    throw new DvbException(String.format("Don't know what to do with Descriptor %02X in CAT",descriptor.getTag()));
                }
            }
        }
    }

    private HashSet<CaDescriptor> knownDescriptors;
}