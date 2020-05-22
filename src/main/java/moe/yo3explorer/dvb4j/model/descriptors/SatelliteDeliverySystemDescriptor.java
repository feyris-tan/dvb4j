package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbException;
import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.FEC;
import moe.yo3explorer.dvb4j.model.enums.ModulationType;
import moe.yo3explorer.dvb4j.model.enums.Polarization;

import java.nio.ByteBuffer;
import java.util.Objects;

public class SatelliteDeliverySystemDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x43;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        byte[] frequencyRaw = new byte[4];
        wrap.get(frequencyRaw);
        frequency = (double)Integer.parseInt(DvbTimeConverter.bytesToHex(frequencyRaw));
        frequency /= 100.0;

        byte[] orbitalRaw = new byte[2];
        wrap.get(orbitalRaw);
        int orbitalInt = Integer.parseInt(DvbTimeConverter.bytesToHex(orbitalRaw));
        orbitalPosition = (int) orbitalInt / 10.0;

        byte flags = wrap.get();
        isEast = (flags & 0x80) != 0;

        int polarizationRaw = flags >> 5;
        polarizationRaw &= 0x03;
        polarization = Polarization.values()[polarizationRaw];

        int rollOffRaw = flags >> 3;
        rollOffRaw &= 0x03;
        rollOff = parseRollOff(rollOffRaw);

        isS2 = (flags & 0x04) != 0;
        if (!isS2)
            rollOff = 0;

        int modulationTypeRaw = flags & 0x03;
        modulationType = ModulationType.values()[modulationTypeRaw];

        byte[] symbolRateRaw = new byte[4];
        wrap.get(symbolRateRaw);
        String symbolRateStr = DvbTimeConverter.bytesToHex(symbolRateRaw);
        symbolRateStr = symbolRateStr.substring(0,symbolRateStr.length() - 1);
        symbolRate = Integer.parseInt(symbolRateStr);
        symbolRate /= 10;

        int fecRaw = symbolRateRaw[3] & 0x0f;
        fec = FEC.values()[fecRaw];
    }

    private double parseRollOff(int rolloff)
    {
        switch (rolloff)
        {
            case 0:
                return 0.35;
            case 1:
                return 0.25;
            case 2:
                return 0.20;
            case 3:
                return Double.NaN;
            default:
                throw new DvbException(String.format("Maximum roll off is 3!"));
        }
    }

    private double frequency;
    private double orbitalPosition;
    private boolean isEast;
    private Polarization polarization;
    private double rollOff;
    private boolean isS2;
    private ModulationType modulationType;
    private int symbolRate;
    private FEC fec;

    public double getFrequency() {
        return frequency;
    }

    public double getOrbitalPosition() {
        return orbitalPosition;
    }

    public boolean isEast() {
        return isEast;
    }

    public Polarization getPolarization() {
        return polarization;
    }

    public double getRollOff() {
        return rollOff;
    }

    public boolean isS2() {
        return isS2;
    }

    public ModulationType getModulationType() {
        return modulationType;
    }

    public int getSymbolRate() {
        return symbolRate;
    }

    public FEC getFec() {
        return fec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SatelliteDeliverySystemDescriptor)) return false;
        SatelliteDeliverySystemDescriptor that = (SatelliteDeliverySystemDescriptor) o;
        return getFrequency() == that.getFrequency() &&
                Double.compare(that.getOrbitalPosition(), getOrbitalPosition()) == 0 &&
                isEast() == that.isEast() &&
                getPolarization() == that.getPolarization();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrequency(), getOrbitalPosition(), isEast(), getPolarization());
    }

    @Override
    public String toString() {
        return "SatelliteDeliverySystemDescriptor{" +
                "frequency=" + frequency +
                ", orbitalPosition=" + orbitalPosition +
                ", isEast=" + isEast +
                ", polarization=" + polarization +
                ", rollOff=" + rollOff +
                ", isS2=" + isS2 +
                ", modulationType=" + modulationType +
                ", symbolRate=" + symbolRate +
                ", fec=" + fec +
                '}';
    }
}
