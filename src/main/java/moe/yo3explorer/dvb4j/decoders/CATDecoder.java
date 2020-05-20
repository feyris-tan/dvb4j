package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.PsiSection;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

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
        if (payload.limit() > 0)
        {
            System.out.println("Dvb4j warning: CAT Decoder not yet implemented.");
        }
    }
}
