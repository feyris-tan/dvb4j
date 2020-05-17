package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbContext;
import moe.yo3explorer.dvb4j.PsiSection;
import moe.yo3explorer.dvb4j.model.PATEntry;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Objects;

public class PATDecoder implements PSIDecoder
{
    public PATDecoder(DvbContext dvbContext)
    {
        this.context = dvbContext;
    }

    private DvbContext context;

    @Override
    public int getTableId() {
        return 0;
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        if (!psiSection.check(0))
            return;

        ByteBuffer payload = psiSection.getPayload();
        for (int i = 0; i < payload.limit(); i += 4)
        {
            int packed = payload.getInt();
            int programNumber = packed >> 16;
            int pid = packed & 0x00001fff;
            PATEntry pat = new PATEntry(programNumber,pid);
            if (patEntries == null)
                patEntries = new HashSet<>();
            boolean newPat = patEntries.add(pat);
            if (newPat)
            {
                context.attachPatEntry(pat);
            }
        }
    }

    private HashSet<PATEntry> patEntries;
}

