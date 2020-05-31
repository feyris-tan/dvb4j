package moe.yo3explorer.dvb4j.text;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class Iso8859FourteenDecoder extends CharsetDecoder {
    Iso8859FourteenDecoder(Iso8859Fourteen charset) {
        super(charset, 1f, 1);
    }

    @Override
    protected CoderResult decodeLoop(@NotNull ByteBuffer in, CharBuffer out) {
        while (in.position() < in.limit())
        {
            int read = in.get() & 0xff;
            if (read < 128) {
                out.append((char) read);
                continue;
            }

            switch (read)
            {
                case 0x80: out.put('\u0080'); break;
                case 0x81: out.put('\u0081'); break;
                case 0x82: out.put('\u0082'); break;
                case 0x83: out.put('\u0083'); break;
                case 0x84: out.put('\u0084'); break;
                case 0x85: out.put('\u0085'); break;
                case 0x86: out.put('\u0086'); break;
                case 0x87: out.put('\u0087'); break;
                case 0x88: out.put('\u0088'); break;
                case 0x89: out.put('\u0089'); break;
                case 0x8A: out.put('\u008A'); break;
                case 0x8B: out.put('\u008B'); break;
                case 0x8C: out.put('\u008C'); break;
                case 0x8D: out.put('\u008D'); break;
                case 0x8E: out.put('\u008E'); break;
                case 0x8F: out.put('\u008F'); break;
                case 0x90: out.put('\u0090'); break;
                case 0x91: out.put('\u0091'); break;
                case 0x92: out.put('\u0092'); break;
                case 0x93: out.put('\u0093'); break;
                case 0x94: out.put('\u0094'); break;
                case 0x95: out.put('\u0095'); break;
                case 0x96: out.put('\u0096'); break;
                case 0x97: out.put('\u0097'); break;
                case 0x98: out.put('\u0098'); break;
                case 0x99: out.put('\u0099'); break;
                case 0x9A: out.put('\u009A'); break;
                case 0x9B: out.put('\u009B'); break;
                case 0x9C: out.put('\u009C'); break;
                case 0x9D: out.put('\u009D'); break;
                case 0x9E: out.put('\u009E'); break;
                case 0x9F: out.put('\u009F'); break;
                case 0xA0: out.put('\u00A0'); break;
                case 0xA1: out.put('Ḃ'); break;
                case 0xA2: out.put('ḃ'); break;
                case 0xA3: out.put('£'); break;
                case 0xA4: out.put('Ċ'); break;
                case 0xA5: out.put('ċ'); break;
                case 0xA6: out.put('Ḋ'); break;
                case 0xA7: out.put('§'); break;
                case 0xA8: out.put('Ẁ'); break;
                case 0xA9: out.put('©'); break;
                case 0xAa: out.put('Ẃ'); break;
                case 0xAb: out.put('ḋ'); break;
                case 0xAc: out.put('Ỳ'); break;
                case 0xAD: out.put('\u00AD'); break;
                case 0xAe: out.put('®'); break;
                case 0xAf: out.put('Ÿ'); break;
                case 0xB0: out.put('Ḟ'); break;
                case 0xB1: out.put('ḟ'); break;
                case 0xb2: out.put('Ġ'); break;
                case 0xb3: out.put('ġ'); break;
                case 0xb4: out.put('Ṁ'); break;
                case 0xb5: out.put('ṁ'); break;
                case 0xb6: out.put('¶'); break;
                case 0xb7: out.put('Ṗ'); break;
                case 0xb8: out.put('ẁ'); break;
                case 0xb9: out.put('ṗ'); break;
                case 0xba: out.put('ẃ'); break;
                case 0xbb: out.put('Ṡ'); break;
                case 0xbc: out.put('ỳ'); break;
                case 0xbd: out.put('Ẅ'); break;
                case 0xbe: out.put('ẅ'); break;
                case 0xbf: out.put('ṡ'); break;
                case 0xc0: out.put('À'); break;
                case 0xc1: out.put('Á'); break;
                case 0xc2: out.put('Â'); break;
                case 0xc3: out.put('Ã'); break;
                case 0xc4: out.put('Ä'); break;
                case 0xc5: out.put('Å'); break;
                case 0xc6: out.put('Æ'); break;
                case 0xc7: out.put('Ç'); break;
                case 0xc8: out.put('È'); break;
                case 0xc9: out.put('É'); break;
                case 0xca: out.put('Ê'); break;
                case 0xcb: out.put('Ë'); break;
                case 0xcc: out.put('Ì'); break;
                case 0xcd: out.put('Í'); break;
                case 0xce: out.put('Î'); break;
                case 0xcf: out.put('Ï'); break;
                case 0xd0: out.put('Ŵ'); break;
                case 0xd1: out.put('Ñ'); break;
                case 0xd2: out.put('Ò'); break;
                case 0xd3: out.put('Ó'); break;
                case 0xd4: out.put('Ô'); break;
                case 0xd5: out.put('Õ'); break;
                case 0xd6: out.put('Ö'); break;
                case 0xd7: out.put('Ṫ'); break;
                case 0xd8: out.put('Ø'); break;
                case 0xd9: out.put('Ù'); break;
                case 0xda: out.put('Ú'); break;
                case 0xdb: out.put('Û'); break;
                case 0xdc: out.put('Ü'); break;
                case 0xdd: out.put('Ý'); break;
                case 0xde: out.put('Ŷ'); break;
                case 0xdf: out.put('ß'); break;
                case 0xe0: out.put('à'); break;
                case 0xe1: out.put('á'); break;
                case 0xe2: out.put('â'); break;
                case 0xe3: out.put('ã'); break;
                case 0xe4: out.put('ä'); break;
                case 0xe5: out.put('å'); break;
                case 0xe6: out.put('æ'); break;
                case 0xe7: out.put('ç'); break;
                case 0xe8: out.put('è'); break;
                case 0xe9: out.put('é'); break;
                case 0xea: out.put('ê'); break;
                case 0xeb: out.put('ë'); break;
                case 0xec: out.put('ì'); break;
                case 0xed: out.put('í'); break;
                case 0xee: out.put('î'); break;
                case 0xef: out.put('ï'); break;
                case 0xf0: out.put('ŵ'); break;
                case 0xf1: out.put('ñ'); break;
                case 0xf2: out.put('ò'); break;
                case 0xf3: out.put('ó'); break;
                case 0xf4: out.put('ô'); break;
                case 0xf5: out.put('õ'); break;
                case 0xf6: out.put('ö'); break;
                case 0xf7: out.put('ṫ'); break;
                case 0xf8: out.put('ø'); break;
                case 0xf9: out.put('ù'); break;
                case 0xfa: out.put('ú'); break;
                case 0xfb: out.put('û'); break;
                case 0xfc: out.put('ü'); break;
                case 0xfd: out.put('ý'); break;
                case 0xfe: out.put('ŷ'); break;
                case 0xff: out.put('ÿ'); break;
            }
        }
        return CoderResult.UNDERFLOW;
    }
}
