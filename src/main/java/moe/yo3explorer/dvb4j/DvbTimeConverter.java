package moe.yo3explorer.dvb4j;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DvbTimeConverter
{
    private DvbTimeConverter() {}

    @NotNull
    public static Date parseTime(@NotNull ByteBuffer payload)
    {
        int mjd = payload.getShort() & 0xffff;
        int y1 = (int)(((double)mjd - 15078.2) / 365.25);
        int m1 = (int)((mjd - 14956.1 - (int)((double)y1 * 365.25)) / 30.6001);
        int d = mjd - 14956 - (int)((double)y1 * 365.25) - (int)((double)m1 * 30.6001);
        int k = 0;
        if (m1 == 14 || m1 == 15)
            k = 1;
        int y = y1 + k + 1900;
        int m = m1 - 1 - k * 12;

        byte ho = payload.get();
        int hour = Integer.parseInt(bytesToHex(ho));
        byte mi = payload.get();
        int minute = Integer.parseInt(bytesToHex(mi));
        byte se = payload.get();
        int second = Integer.parseInt(bytesToHex(se));

        Date time = new GregorianCalendar(y, m - 1, d, hour, minute, second).getTime();
        return time;
    }

    //loosely based on https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
    private static final char[] HEX_ARRAY = "0123456789".toCharArray();
    @NotNull
    @Contract("_ -> new")
    private static String bytesToHex(byte theByte) {
        char[] hexChars = new char[2];
        int v = theByte & 0xFF;
        hexChars[0] = HEX_ARRAY[v >>> 4];
        hexChars[1] = HEX_ARRAY[v & 0x0F];
        return new String(hexChars);
    }
}
