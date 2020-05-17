package moe.yo3explorer.dvb4j;

import moe.yo3explorer.dvb4j.model.PATEntry;
import moe.yo3explorer.dvb4j.model.PMTEntry;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.FileInputStream;
import java.io.IOException;

@Category(AllTests.class)
public class DvbTest implements DvbReceiver {

    @Test
    public void dvbTest() throws IOException {

        DvbContext dvbContext = new DvbContext();
        dvbContext.setDvbReceiver(this);
        PidSplitter demuxer = new PidSplitter();

        FileInputStream fis = new FileInputStream("E:\\10964_V_14170.ts");
        byte[] buffer = new byte[188];
        while (fis.available() > 188)
        {
            int read = fis.read(buffer, 0, 188);
            if (read != 188)
                throw new IOException("incomplete read");
            DvbPacket dvbPacket = new DvbPacket(buffer);
            dvbContext.pushPacket(dvbPacket);
            //demuxer.pushPacket(buffer,dvbPacket.getPid());
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
}