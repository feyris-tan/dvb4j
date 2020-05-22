package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.ApplicationType;
import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationSignalingDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x6f;
    }

    @Override
    public void readFrom(byte[] buffer) {
        applicationTypeList = new ArrayList<>();
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        while (wrap.position() < wrap.limit())
        {
            int applicationType = wrap.getShort() & 0x7FFF;
            int versionNumber = wrap.get() & 0x1f;
            applicationTypeList.add(new ApplicationType(applicationType,versionNumber));
        }
    }

    private List<ApplicationType> applicationTypeList;

    public List<ApplicationType> getApplicationTypeList() {
        return Collections.unmodifiableList(applicationTypeList);
    }

    @Override
    public String toString() {
        return "ApplicationSignalingDescriptor{" +
                "numApplicationTypeList=" + applicationTypeList.size() +
                '}';
    }
}
