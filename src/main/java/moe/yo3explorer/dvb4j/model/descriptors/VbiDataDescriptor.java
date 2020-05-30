package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.FieldParityAndLineOffset;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VbiDataDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x45;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        dataServices = new HashMap<>();
        while (wrap.position() < wrap.limit())
        {
            int dataServiceId = wrap.get() & 0xff;
            int dataDescriptorLength = wrap.get() & 0xff;
            if (dataServiceId == 0x01 || dataServiceId == 0x02 || dataServiceId == 0x04 ||
                dataServiceId == 0x05 || dataServiceId == 0x06 || dataServiceId == 0x07)
            {
                FieldParityAndLineOffset[] fpLos = new FieldParityAndLineOffset[dataDescriptorLength];
                for (int i = 0; i < dataDescriptorLength; i++) {
                    byte flags = wrap.get();
                    boolean fieldParity = (flags & 0x20) != 0;
                    int lineOffset = (flags & 0x1f);
                    fpLos[i] = new FieldParityAndLineOffset(fieldParity, lineOffset);
                }
                dataServices.put(dataServiceId,fpLos);
            }
            else
            {
                byte[] reservedBytes = new byte[dataDescriptorLength];
                wrap.get(reservedBytes);
                dataServices.put(dataServiceId,reservedBytes);
            }
        }
    }

    private HashMap<Integer,Object> dataServices;

    public Map<Integer, Object> getDataServices() {
        return Collections.unmodifiableMap(dataServices);
    }

    @Override
    public String toString() {
        return "VbiDataDescriptor{" +
                "dataServices=" + dataServices.keySet().toString() +
                '}';
    }
}
