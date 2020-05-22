package moe.yo3explorer.dvb4j.decoders;

import moe.yo3explorer.dvb4j.DvbReceiver;

public class NITDecoder41 extends NITDecoder{
    public NITDecoder41(DvbReceiver dvbReceiver) {
        super(dvbReceiver);
    }

    @Override
    public int getTableId() {
        return 0x41;
    }


}
