package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

public class StdDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 17;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        assert buffer.length == 1;
        leakValid = (buffer[0] & 0x01) != 0;
    }

    private boolean leakValid;

    public boolean isLeakValid() {
        return leakValid;
    }

    @Override
    public String toString() {
        return "StdDescriptor{" +
                "leakValid=" + leakValid +
                '}';
    }
}
