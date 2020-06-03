package moe.yo3explorer.dvb4j.text;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Iterator;
import java.util.LinkedList;

public class DvbCharsetProvider extends CharsetProvider {

    public DvbCharsetProvider()
    {
        knownCharsets = new LinkedList<>();
        knownCharsets.add(new CodeTable00Charset());
        knownCharsets.add(new DvbCharset());
        knownCharsets.add(new Iso8859Ten());
        knownCharsets.add(new Iso8859Fourteen());

    }

    private LinkedList<Charset> knownCharsets;

    @Override
    public Iterator<Charset> charsets() {
        return knownCharsets.iterator();
    }

    @Override
    public Charset charsetForName(@NotNull String charsetName) {
        for (Charset knownCharset : knownCharsets) {
            if (knownCharset.name().equals(charsetName))
                return knownCharset;
            if (knownCharset.displayName().equals(charsetName))
                return knownCharset;
            for (String alias : knownCharset.aliases()) {
                if (alias.equals(charsetName))
                    return knownCharset;
            }
        }
        return null;
    }
}
