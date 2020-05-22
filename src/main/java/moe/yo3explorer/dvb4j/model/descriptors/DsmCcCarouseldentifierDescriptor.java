package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

public class DsmCcCarouseldentifierDescriptor implements Descriptor
{
    /*
    This one is neither describen in ISO 13818-1 nor in ETSI 300468.
    This is a part of ISO 13818-6 or one of it's amendments, which I have no access to.
    If anyone is willing to share/lend a copy, please message me!
 */
    @Override
    public int getTag() {
        return 0x13;
    }

    @Override
    public void readFrom(byte[] buffer) {
        dsmCcCarouselIdentifiers++;
    }

    public static int dsmCcCarouselIdentifiers;

}
