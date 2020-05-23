package moe.yo3explorer.dvb4j;

import moe.yo3explorer.dvb4j.model.*;
import moe.yo3explorer.dvb4j.model.descriptors.CaDescriptor;
import moe.yo3explorer.dvb4j.model.descriptors.SatelliteDeliverySystemDescriptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DvbReceiver
{
    void onNewPatEntry(PATEntry patEntry);
    void onNewPmtEntry(int pmtPid, PMTEntry pmtEntry);
    void onTdtTime(Date date);
    void onSdtEntry(SDTEntry sdtEntry);
    void onNewCaDescriptor(CaDescriptor caDescriptor);
    void onTotTime(Date date, List<Descriptor> descriptors);
    void onBouquetAssociation(BATEntry batEntry);
    void onNetworkInformation(SatelliteDeliverySystemDescriptor satelliteDeliverySystemDescriptor, List<Descriptor> tsDescriptors, List<Descriptor> networkDescriptors);
    void onScheduledEvent(EITEvent eitEvent);
}
