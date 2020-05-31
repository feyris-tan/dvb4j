package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.DataStreamAlignment;
import org.jetbrains.annotations.NotNull;

public class DataStreamAlignmentDescriptor implements Descriptor {
    private DataStreamAlignment dataStreamAlignment;

    @Override
    public int getTag() {
        return 6;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        int flags = buffer[0] & 0xff;

        if (flags >= 5)
            dataStreamAlignment = DataStreamAlignment.UNKNOWN;
        else
            dataStreamAlignment = DataStreamAlignment.values()[flags];
    }

    public DataStreamAlignment getDataStreamAlignment() {
        return dataStreamAlignment;
    }

    @Override
    public String toString() {
        return "DataStreamAlignmentDescriptor{" +
                "dataStreamAlignment=" + dataStreamAlignment +
                '}';
    }
}
