package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.PsiSection;

public interface PSIDecoder
{
    int getTableId();
    void handlePsi(PsiSection psiSection);
}
