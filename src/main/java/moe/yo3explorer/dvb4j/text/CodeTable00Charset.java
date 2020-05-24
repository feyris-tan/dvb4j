package moe.yo3explorer.dvb4j.text;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CodeTable00Charset extends Charset {
    /**
     * Initializes a new charset with the given canonical name and alias
     * set.
     *
     */
    public CodeTable00Charset()
    {
        super("DVBCodeTable00", new String[] {"ISO-6937-euro"});
    }

    @Override
    public boolean contains(Charset cs) {
        return false;
    }

    @Override
    public CharsetDecoder newDecoder() {
        return new CodeTable00CharsetDecoder(this);
    }

    @Override
    public CharsetEncoder newEncoder() {
        return null;
    }
}
