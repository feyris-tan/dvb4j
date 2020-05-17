package moe.yo3explorer.dvb4j;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Category(AllTests.class)
public class JavaSanityTest {
    @Test
    public void byteArrayOutputStreamStaysAlive() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(new byte[] {1,2});

        byte[] bytes = baos.toByteArray();
        Assert.assertEquals(bytes.length,2);
        Assert.assertEquals(bytes[0],1);
        Assert.assertEquals(bytes[1],2);

        baos.write(new byte[] {3,4});

        bytes = baos.toByteArray();
        Assert.assertEquals(bytes.length,4);
        Assert.assertEquals(bytes[0],1);
        Assert.assertEquals(bytes[1],2);
        Assert.assertEquals(bytes[2],3);
        Assert.assertEquals(bytes[3],4);
    }
}
