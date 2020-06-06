package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.descriptorEntities.CRID;
import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ContentIdentifierDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x76;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        while (wrap.limit() >= wrap.position())
        {
            if (cridList == null)
                cridList = new ArrayList<>();

            byte cridFlags = wrap.get();
            int cridType = cridFlags & 0xfc;
            int cridLocation = cridFlags & 0x03;
            if (cridLocation == 0)
            {
                int cridLength = wrap.get() & 0xff;
                if (wrap.limit() - wrap.position() < cridLength)
                    return;
                byte[] cridBuffer = new byte[cridLength];
                wrap.get(cridBuffer);
                CRID crid = new CRID(cridType,cridLocation,cridBuffer);
                cridList.add(crid);
            }
            else if (cridLocation == 1)
            {
                int cridRef = wrap.getShort() & 0xffff;
                CRID crid = new CRID(cridType,cridLocation,cridRef);
                cridList.add(crid);
            }
        }
    }

    private List<CRID> cridList;
}
