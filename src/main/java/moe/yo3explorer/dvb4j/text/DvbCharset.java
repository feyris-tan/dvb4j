package moe.yo3explorer.dvb4j.text;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class DvbCharset extends Charset {

    /**
     * Initializes a new charset with the given canonical name and alias
     * set.
     */
    public DvbCharset() {
        super("DVB", null);
    }

    @Override
    public boolean contains(Charset cs) {
        for (Charset charset : UsedCharsets.INCLUSIONS)
        {
            if (charset.equals(cs))
                return true;
        }
        return false;
    }

    @Override
    public CharsetDecoder newDecoder() {
        return new DvbCharsetDecoder(this);
    }

    @Override
    public CharsetEncoder newEncoder() {
        return null;
    }
}
