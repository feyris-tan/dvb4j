package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;
import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.PsiSection;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//Guter Stoff -> https://www.etsi.org/deliver/etsi_en/300400_300499/300468/01.03.01_60/en_300468v010301p.pdf
public class TDTDecoder implements PSIDecoder
{
    public TDTDecoder(DvbReceiver dvbReceiver)
    {
        this.receiver = dvbReceiver;
    }
    private DvbReceiver receiver;

    @Override
    public int[] getTableIds() {
        return new int[] {0x70};
    }

    @Override
    public void handlePsi(@NotNull PsiSection psiSection) {
        ByteBuffer payload = psiSection.getPayload();
        Date date = DvbTimeConverter.parseTime(payload);
        if (receiver != null)
            if (date != null)
                receiver.onTdtTime(date);
    }
}
