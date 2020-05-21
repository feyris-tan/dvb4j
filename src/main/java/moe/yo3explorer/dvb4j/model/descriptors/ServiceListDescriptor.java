package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.ServiceListEntry;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ServiceListDescriptor implements Descriptor
{
    public ServiceListDescriptor() {
        services = new ArrayList<>();
    }

    @Override
    public int getTag() {
        return 0x41;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        for (; wrap.limit() > wrap.position();)
        {
            int serviceId = wrap.getShort() & 0xffff;
            byte serviceType = wrap.get();
            ServiceListEntry serviceListEntry = new ServiceListEntry(serviceId,serviceType);
            services.add(serviceListEntry);
        }
    }

    private ArrayList<ServiceListEntry> services;

    public ArrayList<ServiceListEntry> getServices() {
        return services;
    }

    @Override
    public String toString() {
        return "ServiceListDescriptor{" +
                "numServices=" + services.size() +
                '}';
    }
}
