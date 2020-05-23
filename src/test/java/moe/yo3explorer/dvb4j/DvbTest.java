package moe.yo3explorer.dvb4j;

import moe.yo3explorer.dvb4j.model.*;
import moe.yo3explorer.dvb4j.model.descriptors.CaDescriptor;
import moe.yo3explorer.dvb4j.model.descriptors.ExtendedEventDescriptor;
import moe.yo3explorer.dvb4j.model.descriptors.SatelliteDeliverySystemDescriptor;
import moe.yo3explorer.dvb4j.model.descriptors.ShortEventDescriptor;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Category(AllTests.class)
public class DvbTest implements DvbReceiver {

    @Test
    public void testTürksat10964v14170() throws IOException {
        perform("E:\\10964_V_14170.ts", false);
    }

    @Test
    public void testTürksat10980v12500() throws IOException {
        perform("E:\\10980_V_12500.ts",false);
    }

    @Test
    public void testTürksat11012v30000() throws IOException {
        perform("E:\\11012_V_30000.ts",false);
    }

    @Test
    public void testTürksat11045h4800() throws IOException {
        perform("E:\\11045_H_4800.ts",false);
    }

    @Test
    public void testTürksat11053h8000() throws IOException {
        perform("E:\\11053_H_8000.ts",false);
    }

    @Test
    public void testTürksat11054v30000() throws IOException {
        perform("E:\\11054_V_30000.ts",false);
    }

    @Test
    public void testTürksat11062v4820() throws IOException
    {
        perform("E:\\11062_V_4820.ts",false);
    }

    @Test
    public void testAstra11949h22000() throws IOException
    {
        perform("E:\\11494_H_22000.ts",false);
    }

    private void perform(String filename, boolean demux) throws IOException {
        DvbContext dvbContext = new DvbContext();
        dvbContext.setDvbReceiver(this);
        PidSplitter demuxer = new PidSplitter();

        FileInputStream fis = new FileInputStream(filename);
        byte[] buffer = new byte[188];
        while (fis.available() > 188)
        {
            int read = fis.read(buffer, 0, 188);
            if (read != 188)
                throw new IOException("incomplete read");
            DvbPacket dvbPacket = new DvbPacket(buffer);
            dvbContext.pushPacket(dvbPacket);
            if (demux)
                demuxer.pushPacket(buffer,dvbPacket.getPid());
        }
        demuxer.close();
    }

    @Override
    public void onNewPatEntry(@NotNull PATEntry patEntry) {
        System.out.printf("PAT Entry:  Program #: %d, PMT_PID %d\n",patEntry.getProgramNumber(),patEntry.getPid());
    }

    @Override
    public void onNewPmtEntry(int pmtPid, @NotNull PMTEntry pmtEntry) {
        System.out.printf("PMT Entry:  PMT# %d, Type %d, Pid %d\n",pmtPid,pmtEntry.getType(),pmtEntry.getPid());
    }

    @Override
    public void onTdtTime(@NotNull Date date) {
        System.out.printf("TDT: %s\n",date.toString());
    }

    @Override
    public void onSdtEntry(@NotNull SDTEntry sdtEntry) {
        System.out.printf("SDT: %s\n",sdtEntry.toString());
    }

    @Override
    public void onNewCaDescriptor(@NotNull CaDescriptor caDescriptor) {
        System.out.printf("Found new CA Descriptor: SystemID: %04X, PID: %04X\n",caDescriptor.getCaSystemId(),caDescriptor.getCaPid());
    }

    @Override
    public void onTotTime(@NotNull Date date, @NotNull List<Descriptor> descriptors) {
        System.out.printf("TOT Time: %s (%d descriptors)\n",date.toString(),descriptors.size());
    }

    @Override
    public void onBouquetAssociation(@NotNull BATEntry batEntry) {
        System.out.printf("New BAT Entry: %s\n",batEntry.toString());
    }

    @Override
    public void onNetworkInformation(@NotNull SatelliteDeliverySystemDescriptor satelliteDeliverySystemDescriptor, List<Descriptor> tsDescriptors, List<Descriptor> networkDescriptors) {
        System.out.println("NIT: " + satelliteDeliverySystemDescriptor.toString());
    }

    @Override
    public void onScheduledEvent(@NotNull EITEvent eitEvent) {
        Optional<String> firstShort = eitEvent.getDescriptorList().stream().filter(x -> x.getTag() == 0x4D).map(x -> ((ShortEventDescriptor) x).getEventName()).findFirst();
        String name = firstShort.orElse(null);

        if (name == null) {

            Optional<String> firstExtended = eitEvent.getDescriptorList().stream().filter(x -> x.getTag() == 0x4E).map(x -> ((ExtendedEventDescriptor) x).getText()).findFirst();
            name = firstExtended.orElse(null);
        }

        if (name == null)
            name = "???";

        System.out.printf("New EIT Event: (%s) \"%s\" \n",eitEvent,name);
    }
}
