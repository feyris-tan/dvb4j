package moe.yo3explorer.dvb4j.text;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class Iso8859Fourteen extends Charset {
    /**
     * Initializes a new charset with the given canonical name and alias
     * set.
     *
     */
    public Iso8859Fourteen()
    {
        super("ISO-8859-14", new String[] {"ISO-8859-14"});
    }

    @Override
    public boolean contains(Charset cs) {
        return false;
    }

    @Override
    public CharsetDecoder newDecoder() {
        return new Iso8859FourteenDecoder(this);
    }

    @Override
    public CharsetEncoder newEncoder() {
        return null;
    }
}
