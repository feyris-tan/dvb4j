package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptorEntities.TvAnywhereId;
import moe.yo3explorer.dvb4j.model.enums.TvAnywhereRunningStatus;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TvAnywhereIdDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x75;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        int max = buffer.length / 4;
        for (int i = 0; i < max; i++)
        {
            int tvaId = wrap.getShort() & 0xffff;
            TvAnywhereRunningStatus runningStatus = TvAnywhereRunningStatus.values()[wrap.get() & 0x07];
            TvAnywhereId child = new TvAnywhereId(tvaId,runningStatus);
            if (ids == null)
                ids = new ArrayList<>();
            ids.add(child);
        }
    }

    private List<TvAnywhereId> ids;

    public List<TvAnywhereId> getIds() {
        return Collections.unmodifiableList(ids);
    }
}
