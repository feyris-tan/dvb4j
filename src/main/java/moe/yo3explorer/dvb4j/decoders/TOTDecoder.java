package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;

public class TOTDecoder implements PSIDecoder {

    public TOTDecoder(DvbReceiver receiver) {
        this.receiver = receiver;
    }

    private DvbReceiver receiver;

    @Override
    public int getTableId() {
        return 0x73;
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        ByteBuffer payload = psiSection.getPayload();
        Date date = DvbTimeConverter.parseTime(payload);
        int descriptorRemain = payload.getShort() & 0x0FFF;
        ArrayList<Descriptor> descriptors = new ArrayList<>();
        while (descriptorRemain > 0)
        {
            int descriptorId = payload.get() & 0xff;
            int descriptorLength = payload.get() & 0xff;
            descriptorRemain -= 2;

            byte[] descriptorBuffer = new byte[descriptorLength];
            payload.get(descriptorBuffer);
            descriptors.add(DescriptorDecoder.autoDecode(descriptorId,getTableId(),descriptorBuffer));
            descriptorRemain -= descriptorLength;
        }
        receiver.onTotTime(date,descriptors);
    }
}
