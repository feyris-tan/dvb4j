package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

public class ShortSmoothingBufferDescriptor implements Descriptor {
    private int sbSize;
    private double leakRate;

    @Override
    public int getTag() {
        return 0x61;
    }

    @Override
    public void readFrom(byte[] buffer) {
        byte flags = buffer[0];
        sbSize = flags & 0xC0 >> 6;
        if (sbSize == 1)
            sbSize = 1536;
        else
            sbSize = 0;

        int leakRateRaw = flags & 0x3f;
        leakRate = parseLeakRate(leakRateRaw);
    }

    private double parseLeakRate(int leakRateRaw)
    {
        switch (leakRateRaw)
        {
            case 1: return 0.0009;
            case 2: return 0.0018;
            case 3: return 0.0036;
            case 4: return 0.0072;
            case 5: return 0.0108;
            case 6: return 0.0144;
            case 7: return 0.0216;
            case 8: return 0.0288;
            case 9: return 0.075;
            case 10: return 0.5;
            case 11: return 0.5625;
            case 12: return 0.8437;
            case 13: return 1.0;
            case 14: return 1.1250;
            case 15: return 1.5;
            case 16: return 1.6875;
            case 17: return 2.0;
            case 18: return        2.2500;
            case 19: return       2.5;
            case 20: return        3.0;
            case 21: return        3.3750;
            case 22: return        3.5;
            case 23: return        4.0;
            case 24: return        4.5;
            case 25: return        5.0;
            case 26: return        5.5;
            case 27: return        6.0;
            case 28: return        6.5;
            case 29: return        6.7500;
            case 30:
            case 31:
            case 32: return ((leakRateRaw) - 16) * 0.5;
            case 33:
            case 34:
            case 35:
            case 36:
            case 37: return ((leakRateRaw) - 24);
            case 38: return 13.5;
            case 39:
            case 40:
            case 41:
            case 42:
            case 43: return ((leakRateRaw) - 25);
            case 44:
            case 45:
            case 46:
            case 47: return ((leakRateRaw) - 34) * 2;
            case 48: return 27;
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55: return ((leakRateRaw) - 35) * 2;
            case 56: return       44;
            case 57: return       48;
            case 58: return       54;
            case 59: return       72;
            case 60: return 108;
            default: return Double.NaN;
        }
    }

    public int getSbSize() {
        return sbSize;
    }

    public double getLeakRate() {
        return leakRate;
    }

    @Override
    public String toString() {
        return "ShortSmoothingBufferDescriptor{" +
                "sbSize=" + sbSize +
                ", leakRate=" + leakRate +
                '}';
    }
}
