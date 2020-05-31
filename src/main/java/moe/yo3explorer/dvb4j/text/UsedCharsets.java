package moe.yo3explorer.dvb4j.text;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class UsedCharsets
{
    public static final Charset CODE_TABLE_00 = new CodeTable00Charset();
    public static final Charset DVB = new DvbCharset();
    public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
    public static final Charset ISO_8859_2 = Charset.forName("ISO-8859-2");
    public static final Charset ISO_8859_3 = Charset.forName("ISO-8859-3");
    public static final Charset ISO_8859_4 = Charset.forName("ISO-8859-4");
    public static final Charset ISO_8859_5 = Charset.forName("ISO-8859-5");
    public static final Charset ISO_8859_6 = Charset.forName("ISO-8859-6");
    public static final Charset ISO_8859_7 = Charset.forName("ISO-8859-7");
    public static final Charset ISO_8859_8 = Charset.forName("ISO-8859-8");
    public static final Charset ISO_8859_9 = Charset.forName("ISO-8859-9");
    public static final Charset ISO_8859_11 = Charset.forName("x-iso-8859-11");
    public static final Charset ISO_8859_13 = Charset.forName("ISO-8859-13");
    public static final Charset ISO_8859_14 = new Iso8859Fourteen();
    public static final Charset ISO_8859_15 = Charset.forName("ISO-8859-15");
    public static final Charset ISO_10646 = StandardCharsets.UTF_16BE;
    public static final Charset GB_2312_1980 = Charset.forName("GB2312");

    public static Charset[] INCLUSIONS = new Charset[] {ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_4, ISO_8859_5,
            ISO_8859_6, ISO_8859_7, ISO_8859_8, ISO_8859_9, ISO_8859_11, ISO_8859_13, ISO_8859_14, ISO_8859_15,
            ISO_10646, GB_2312_1980, CODE_TABLE_00};
}
