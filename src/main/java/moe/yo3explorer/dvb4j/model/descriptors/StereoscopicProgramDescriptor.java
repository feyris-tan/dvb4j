package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.StereoscopicServiceType;
import org.jetbrains.annotations.NotNull;

public class StereoscopicProgramDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 53;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        int flags = buffer[0] & 0x07;

        switch (flags)
        {
            case 0:
                serviceType = StereoscopicServiceType.UNSPECIFIED;
                return;
            case 1:
                serviceType = StereoscopicServiceType._2D;
                return;
            case 2:
                serviceType = StereoscopicServiceType.FRAME_COMPATIBLE_3D;
                return;
            case 3:
                serviceType = StereoscopicServiceType.SERVICE_COMPATIBLE_3D;
                return;
            default:
                return;
        }
    }

    private StereoscopicServiceType serviceType;

    public StereoscopicServiceType getServiceType() {
        return serviceType;
    }

    @Override
    public String toString() {
        return "StereoscopicProgramDescriptor{" +
                "serviceType=" + serviceType +
                '}';
    }
}
