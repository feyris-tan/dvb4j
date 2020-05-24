package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.PsiSection;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public class STDecoder implements PSIDecoder {
    @Override
    public int[] getTableIds() {
        return new int[] { 0x72};
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        ByteBuffer payload = psiSection.getPayload();
        int length = payload.limit() - payload.position();
        byte[] buffer = new byte[length];
        payload.get(buffer);
        timesStuffed++;
    }

    private int timesStuffed = 0;

    public int getTimesStuffed() {
        return timesStuffed;
    }
}
