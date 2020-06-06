package moe.yo3explorer.dvb4j;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DvbTimeConverter
{
    private DvbTimeConverter() {}

    @Nullable
    public static Date parseTime(@NotNull ByteBuffer payload)
    {
        if ((payload.limit() - payload.position()) < 5)
            return null;

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
        String hourString = singleByteToHex(ho);
        if (hourString.contains("A") | hourString.contains("B") | hourString.contains("C") | hourString.contains("D") | hourString.contains("E") | hourString.contains("F"))
            return null;
        int hour = Integer.parseInt(hourString);
        byte mi = payload.get();
        String minuteString = singleByteToHex(mi);
        if (minuteString.contains("A") | minuteString.contains("B") | minuteString.contains("C") | minuteString.contains("D") | minuteString.contains("E") | minuteString.contains("F"))
            return null;
        int minute = Integer.parseInt(minuteString);
        byte se = payload.get();
        String secondString = singleByteToHex(se);
        if (secondString.contains("A") | secondString.contains("B") | secondString.contains("C") | secondString.contains("D") | secondString.contains("E") | secondString.contains("F"))
            return null;
        int second = Integer.parseInt(secondString);

        Date time = new GregorianCalendar(y, m - 1, d, hour, minute, second).getTime();
        return time;
    }

    //loosely based on https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    @NotNull
    @Contract("_ -> new")
    private static String singleByteToHex(byte theByte) {
        char[] hexChars = new char[2];
        int v = theByte & 0xFF;
        hexChars[0] = HEX_ARRAY[v >>> 4];
        hexChars[1] = HEX_ARRAY[v & 0x0F];
        return new String(hexChars);
    }

    @Nullable
    public static Integer timeOffsetInJavaTime(@NotNull ByteBuffer payload)
    {
        String hourString = singleByteToHex(payload.get());
        if (hourString.contains("A") || hourString.contains("B") || hourString.contains("C") || hourString.contains("D") || hourString.contains("E") || hourString.contains("F"))
            return null;
        int hours = Integer.parseInt(hourString);

        String minuteString = singleByteToHex(payload.get());
        if (minuteString.contains("A") || minuteString.contains("B") || minuteString.contains("C") || minuteString.contains("D") || minuteString.contains("E") || minuteString.contains("F"))
            return null;
        int minutes = Integer.parseInt(minuteString);

        int result = hours * 3600;
        result += minutes * 60;
        result *= 1000;
        return result;
    }

    @NotNull
    public static String bytesToHex(byte[] bytes) {
        if (bytes == null)
            return "";
        // stolen from https://stackoverflow.com/a/9855338
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static long parseDuration(@NotNull ByteBuffer payload)
    {
        byte[] level1 = new byte[3];
        payload.get(level1);

        String hexByte = singleByteToHex(level1[0]);
        if (hexByte.contains("A") || hexByte.contains("B") || hexByte.contains("C") || hexByte.contains("D") || hexByte.contains("E") || hexByte.contains("F"))
            return -1;
        int hours = Integer.parseInt(hexByte);

        hexByte = singleByteToHex(level1[1]);
        if (hexByte.contains("A") || hexByte.contains("B") || hexByte.contains("C") || hexByte.contains("D") || hexByte.contains("E") || hexByte.contains("F"))
            return -1;
        int minutes = Integer.parseInt(hexByte);

        hexByte = singleByteToHex(level1[2]);
        if (hexByte.contains("A") || hexByte.contains("B") || hexByte.contains("C") || hexByte.contains("D") || hexByte.contains("E") || hexByte.contains("F"))
            return -1;
        int seconds = Integer.parseInt(hexByte);

        long result = 0;
        result += (seconds * 1000);
        result += ((minutes * 60) * 1000);
        result += ((hours * 3600) * 1000);
        return result;
    }
}
