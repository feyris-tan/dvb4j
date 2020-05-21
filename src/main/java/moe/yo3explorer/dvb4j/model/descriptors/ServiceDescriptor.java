package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.ServiceType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static moe.yo3explorer.dvb4j.model.enums.ServiceType.decodeServiceType;

public class ServiceDescriptor implements Descriptor {
    private ServiceType serviceType;
    private String serviceProviderName;
    private String serviceName;

    @Override
    public int getTag() {
        return 0x48;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        int rawServiceType = wrap.get() & 0xff;

        serviceType = decodeServiceType(rawServiceType);

        int serviceProviderNameLength = wrap.get() & 0xff;
        byte[] serviceProviderNameRaw = new byte[serviceProviderNameLength];
        wrap.get(serviceProviderNameRaw);
        this.serviceProviderName = new String(serviceProviderNameRaw, StandardCharsets.US_ASCII);

        int serviceNameLength = wrap.get() & 0xff;
        byte[] serviceNameRaw = new byte[serviceNameLength];
        wrap.get(serviceNameRaw);
        this.serviceName = new String(serviceNameRaw, StandardCharsets.US_ASCII);
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "serviceType=" + serviceType +
                ", serviceProviderName='" + serviceProviderName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
