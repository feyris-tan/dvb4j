package moe.yo3explorer.dvb4j.model.extendedDescriptors;

import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.model.descriptors.UserDefinedDescriptor;

public class ReservedExtendedDescriptor extends UserDefinedDescriptor
{
    public ReservedExtendedDescriptor(int tag) {
        super(tag);
    }

    public String toString() {
        String hexString = DvbTimeConverter.bytesToHex(super.getData());
        return "ReservedExtendedDescriptor{" +
                "data=" + hexString +
                '}';
    }
}
