package moe.yo3explorer.dvb4j.text;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Iterator;
import java.util.LinkedList;

public class DvbCharsetProvider extends CharsetProvider {
    @Override
    public Iterator<Charset> charsets() {
        LinkedList<Charset> provided = new LinkedList<>();
        provided.add(new CodeTable00Charset());
        provided.add(new DvbCharset());
        return provided.iterator();
    }

    @Override
    public Charset charsetForName(@NotNull String charsetName) {
        switch (charsetName)
        {
            case "DVB":
                return new DvbCharset();
            case "DVB Character code table 00":
                return new CodeTable00Charset();
            default:
                return null;
        }
    }
}
