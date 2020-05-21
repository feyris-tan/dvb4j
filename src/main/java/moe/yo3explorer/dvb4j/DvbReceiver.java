package moe.yo3explorer.dvb4j;

import moe.yo3explorer.dvb4j.model.PATEntry;
import moe.yo3explorer.dvb4j.model.PMTEntry;
import moe.yo3explorer.dvb4j.model.SDTEntry;

import java.util.Date;

public interface DvbReceiver
{
    void onNewPatEntry(PATEntry patEntry);
    void onNewPmtEntry(int pmtPid, PMTEntry pmtEntry);
    void onTdtTime(Date date);
    void onSdtEntry(SDTEntry sdtEntry);
}
