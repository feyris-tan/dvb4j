package moe.yo3explorer.dvb4j;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PidSplitter implements Closeable
{
    private FileOutputStream[] fos = new FileOutputStream[8192];

    public void pushPacket(byte[] buffer, int pid) throws IOException {
        if (fos[pid] == null) {
            String fname = String.format("%05X.pid", pid);
            FileOutputStream child = new FileOutputStream(fname);
            fos[pid] = child;
        }
        fos[pid].write(buffer,0,188);
    }

    @Override
    public void close() throws IOException {
        for (FileOutputStream fo : fos) {
            if (fo != null)
            {
                fo.flush();
                fo.close();
            }
        }
    }
}
