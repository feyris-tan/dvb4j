package moe.yo3explorer.dvb4j;

import moe.yo3explorer.dvb4j.text.UsedCharsets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(AllTests.class)
public class EncodingTest
{
    @Test
    public void decodeSimpleAscii()
    {
        String s = "Sands of time";
        byte[] bytes = s.getBytes();
        String t = new String(bytes,UsedCharsets.DVB);
        Assert.assertEquals(s,t);
    }
}
