package moe.yo3explorer.dvb4j.text;

import org.jetbrains.annotations.NotNull;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;

public class DvbCharsetDecoder extends CharsetDecoder {
    /**
     * Initializes a new decoder.  The new decoder will have the given
     * chars-per-byte values and its replacement will be the
     * string <code>"&#92;uFFFD"</code>.
     *
     * @param cs                  The charset that created this decoder
     * @throws IllegalArgumentException If the preconditions on the parameters do not hold
     */
    DvbCharsetDecoder(DvbCharset cs) {
        super(cs, 1,1);
    }

    @Override
    protected CoderResult decodeLoop(@NotNull ByteBuffer in, CharBuffer out) {
        int length = in.limit() - in.position();
        int dirtyLength = length;
        byte[] dirtyBuffer = new byte[length];
        in.get(dirtyBuffer);

        Charset actualCharset = null;
        byte[] cleanBuffer = new byte[length];
        int cleanPtr = 0;
        for (int dirtyPtr = 0; dirtyPtr < dirtyBuffer.length; dirtyPtr++)
        {
            if (dirtyPtr == 0)
            {
                if ((dirtyBuffer[0] & 0xff) >= 0x20) {
                    actualCharset = UsedCharsets.CODE_TABLE_00;
                    cleanBuffer[cleanPtr++] = dirtyBuffer[dirtyPtr];
                    continue;
                }

                if (dirtyBuffer[0] == 0x10)
                {
                    byte sb = dirtyBuffer[++dirtyPtr];
                    byte tb = dirtyBuffer[++dirtyPtr];
                    if (sb != 0x00)
                        throw new RuntimeException("reserved charset");
                    switch (tb)
                    {
                        case 0x01: actualCharset = UsedCharsets.ISO_8859_1; continue;
                        case 0x02: actualCharset = UsedCharsets.ISO_8859_2; continue;
                        case 0x03: actualCharset = UsedCharsets.ISO_8859_3; continue;
                        case 0x04: actualCharset = UsedCharsets.ISO_8859_4; continue;
                        case 0x05: actualCharset = UsedCharsets.ISO_8859_5; continue;
                        case 0x06: actualCharset = UsedCharsets.ISO_8859_6; continue;
                        case 0x07: actualCharset = UsedCharsets.ISO_8859_7; continue;
                        case 0x08: actualCharset = UsedCharsets.ISO_8859_8; continue;
                        case 0x09: actualCharset = UsedCharsets.ISO_8859_9; continue;
                        case 0x0a: throw new RuntimeException("not yet implemented");
                        case 0x0b: actualCharset = UsedCharsets.ISO_8859_11; continue;
                        case 0x0d: actualCharset = UsedCharsets.ISO_8859_13; continue;
                        case 0x0e: throw new RuntimeException("not yet implemented");
                        case 0x0f: actualCharset = UsedCharsets.ISO_8859_15; continue;
                        default: throw new RuntimeException("reserved charset");
                    }
                }

                switch (dirtyBuffer[0])
                {
                    case 0x01: actualCharset = UsedCharsets.ISO_8859_5; continue;
                    case 0x02: actualCharset = UsedCharsets.ISO_8859_6; continue;
                    case 0x03: actualCharset = UsedCharsets.ISO_8859_7; continue;
                    case 0x04: actualCharset = UsedCharsets.ISO_8859_8; continue;
                    case 0x05: actualCharset = UsedCharsets.ISO_8859_9; continue;
                    case 0x06: actualCharset = UsedCharsets.ISO_8859_10; continue;
                    case 0x07: actualCharset = UsedCharsets.ISO_8859_11; continue;
                    case 0x08: actualCharset = UsedCharsets.ISO_10646; continue;
                    case 0x09: actualCharset = UsedCharsets.ISO_8859_13; continue;
                    case 0x0a: actualCharset = UsedCharsets.ISO_8859_14; continue;
                    case 0x0b: actualCharset = UsedCharsets.ISO_8859_15; continue;
                    case 0x11: actualCharset = UsedCharsets.ISO_10646; continue;
                    case 0x12: throw new RuntimeException("not implemented");
                    case 0x13: actualCharset = UsedCharsets.GB_2312_1980; continue;
                    case 0x14: actualCharset = UsedCharsets.ISO_10646; continue;
                    case 0x15: actualCharset = StandardCharsets.UTF_8; continue;
                    case 0x1f:
                        throw new RuntimeException("not implemented, see encoding_type_id ETSI TS 101 162 ");
                    default:
                        System.out.println(String.format("reserved charset 0x%02X - falling back to UTF-8!",dirtyBuffer[0]));
                        actualCharset = StandardCharsets.UTF_8;
                        continue;
                }
            }

            if ((dirtyBuffer[dirtyPtr] & 0xff) == 0xC2) {
                if (dirtyPtr != dirtyLength - 1)
                    if ((dirtyBuffer[dirtyPtr + 1] & 0xff) >= 0x80 && (dirtyBuffer[dirtyPtr + 1] & 0xff) <= 0x9F) {
                        dirtyPtr++;
                        continue;
                    }
            }

            if ((dirtyBuffer[dirtyPtr] & 0xff) >= 0x80 && (dirtyBuffer[dirtyPtr] & 0xff) <= 0x9F) {
                continue;
            }

            if ((dirtyBuffer[dirtyPtr] & 0xff) == 0xee) {
                if (dirtyPtr != dirtyLength - 1)
                    if ((dirtyBuffer[dirtyPtr + 1] & 0xff) == 0x82) {
                        if ((dirtyBuffer[dirtyPtr + 1] & 0xff) >= 0x80 && (dirtyBuffer[dirtyPtr + 1] & 0xff) <= 0x9F) {
                            dirtyPtr += 2;
                            continue;
                        }
                    }
            }

            if ((dirtyBuffer[dirtyPtr] & 0xff) == 0xe0) {
                if (dirtyPtr != dirtyLength - 1)
                    if ((dirtyBuffer[dirtyPtr + 1] & 0xff) >= 0x80 && (dirtyBuffer[dirtyPtr + 1] & 0xff) <= 0x9F) {
                        dirtyPtr++;
                        continue;
                    }
            }

            cleanBuffer[cleanPtr++] = dirtyBuffer[dirtyPtr];
        }
        int cleanLen = cleanPtr;

        CharBuffer decode = actualCharset.decode(ByteBuffer.wrap(cleanBuffer, 0, cleanLen));
        char[] decoded = decode.array();
        out.put(decoded);
        return CoderResult.UNDERFLOW;
    }
}
