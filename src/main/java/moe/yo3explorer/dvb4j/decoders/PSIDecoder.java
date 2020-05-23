package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.PsiSection;

public interface PSIDecoder
{
    int[] getTableIds();
    void handlePsi(PsiSection psiSection);
}
