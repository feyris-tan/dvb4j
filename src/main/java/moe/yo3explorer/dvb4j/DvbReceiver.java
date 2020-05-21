package moe.yo3explorer.dvb4j;

import moe.yo3explorer.dvb4j.model.*;
import moe.yo3explorer.dvb4j.model.descriptors.CaDescriptor;

import java.util.ArrayList;
import java.util.Date;

public interface DvbReceiver
{
    void onNewPatEntry(PATEntry patEntry);
    void onNewPmtEntry(int pmtPid, PMTEntry pmtEntry);
    void onTdtTime(Date date);
    void onSdtEntry(SDTEntry sdtEntry);
    void onNewCaDescriptor(CaDescriptor caDescriptor);
    void onTotTime(Date date, ArrayList<Descriptor> descriptors);
    void onBouquetAssociation(BATEntry batEntry);
}
