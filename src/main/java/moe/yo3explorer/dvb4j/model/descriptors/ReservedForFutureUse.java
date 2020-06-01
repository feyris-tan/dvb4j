package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbTimeConverter;

public class ReservedForFutureUse extends UserDefinedDescriptor {
    public ReservedForFutureUse() {
        super(0xff);
    }

    public String toString() {
        return "Unimplemented for future use descriptor.";
    }
}
