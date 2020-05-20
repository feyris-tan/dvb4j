package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class SystemClockDescriptor implements Descriptor {
    private boolean externalClockReference;
    private int integerPart;
    private int exponentPart;

    @Override
    public int getTag() {
        return 11;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        externalClockReference = (buffer[0] & 0x80) != 0;
        integerPart = buffer[0] & 0x3F;
        exponentPart = (buffer[1] & 0xE0) >> 5;
    }

    public boolean isExternalClockReference() {
        return externalClockReference;
    }

    public int getIntegerPart() {
        return integerPart;
    }

    public int getExponentPart() {
        return exponentPart;
    }

    @Override
    public String toString() {
        return "SystemClockDescriptor{" +
                "externalClockReference=" + externalClockReference +
                ", integerPart=" + integerPart +
                ", exponentPart=" + exponentPart +
                '}';
    }
}
