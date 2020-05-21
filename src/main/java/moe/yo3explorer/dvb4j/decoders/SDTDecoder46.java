package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.SDTEntry;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SDTDecoder46 extends SDTDecoder {

    public SDTDecoder46(DvbReceiver dvbReceiver) {
        super(dvbReceiver);
    }

    @Override
    public int getTableId() {
        return 0x46;
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        super.handlePsi(psiSection);
    }
}
