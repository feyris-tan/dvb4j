package moe.yo3explorer.dvb4j.text;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class Iso8859TenDecoder extends CharsetDecoder {
    Iso8859TenDecoder(Iso8859Ten charset) {
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
                case 0xa1: out.put('Ą'); break;
                case 0xa2: out.put('Ē'); break;
                case 0xa3: out.put('Ģ'); break;
                case 0xa4: out.put('Ī'); break;
                case 0xa5: out.put('Ĩ'); break;
                case 0xa6: out.put('Ķ'); break;
                case 0xa7: out.put('§'); break;
                case 0xa8: out.put('Ļ'); break;
                case 0xa9: out.put('Đ'); break;
                case 0xaa: out.put('Š'); break;
                case 0xab: out.put('Ŧ'); break;
                case 0xac: out.put('Ž'); break;
                case 0xad: out.put('\u00AD'); break;
                case 0xae: out.put('Ū'); break;
                case 0xaf: out.put('Ŋ'); break;
                case 0xb0: out.put('°'); break;
                case 0xb1: out.put('ą'); break;
                case 0xb2: out.put('ē'); break;
                case 0xb3: out.put('ģ'); break;
                case 0xb4: out.put('ī'); break;
                case 0xb5: out.put('ĩ'); break;
                case 0xb6: out.put('ķ'); break;
                case 0xb7: out.put('·'); break;
                case 0xb8: out.put('ļ'); break;
                case 0xb9: out.put('đ'); break;
                case 0xba: out.put('š'); break;
                case 0xbb: out.put('ŧ'); break;
                case 0xbc: out.put('ž'); break;
                case 0xbd: out.put('―'); break;
                case 0xbe: out.put('ū'); break;
                case 0xbf: out.put('ŋ'); break;
                case 0xc0: out.put('Ā'); break;
                case 0xc1: out.put('Á'); break;
                case 0xc2: out.put('Â'); break;
                case 0xc3: out.put('Ã'); break;
                case 0xc4: out.put('Ä'); break;
                case 0xc5: out.put('Å'); break;
                case 0xc6: out.put('Æ'); break;
                case 0xc7: out.put('Į'); break;
                case 0xc8: out.put('Č'); break;
                case 0xc9: out.put('É'); break;
                case 0xca: out.put('Ę'); break;
                case 0xcb: out.put('Ë'); break;
                case 0xcc: out.put('Ė'); break;
                case 0xcd: out.put('Í'); break;
                case 0xce: out.put('Î'); break;
                case 0xcf: out.put('Ï'); break;
                case 0xd0: out.put('Ð'); break;
                case 0xd1: out.put('Ņ'); break;
                case 0xd2: out.put('Ō'); break;
                case 0xd3: out.put('Ó'); break;
                case 0xd4: out.put('Ô'); break;
                case 0xd5: out.put('Õ'); break;
                case 0xd6: out.put('Ö'); break;
                case 0xd7: out.put('Ũ'); break;
                case 0xd8: out.put('Ø'); break;
                case 0xd9: out.put('Ų'); break;
                case 0xda: out.put('Ú'); break;
                case 0xdb: out.put('Û'); break;
                case 0xdc: out.put('Ü'); break;
                case 0xdd: out.put('Ý'); break;
                case 0xde: out.put('Þ'); break;
                case 0xdf: out.put('ß'); break;
                case 0xe0: out.put('ā'); break;
                case 0xe1: out.put('á'); break;
                case 0xe2: out.put('â'); break;
                case 0xe3: out.put('ã'); break;
                case 0xe4: out.put('ä'); break;
                case 0xe5: out.put('å'); break;
                case 0xe6: out.put('æ'); break;
                case 0xe7: out.put('į'); break;
                case 0xe8: out.put('č'); break;
                case 0xe9: out.put('é'); break;
                case 0xea: out.put('ę'); break;
                case 0xeb: out.put('ë'); break;
                case 0xec: out.put('ė'); break;
                case 0xed: out.put('í'); break;
                case 0xee: out.put('î'); break;
                case 0xef: out.put('ï'); break;
                case 0xf0: out.put('ð'); break;
                case 0xf1: out.put('ņ'); break;
                case 0xf2: out.put('ō'); break;
                case 0xf3: out.put('ó'); break;
                case 0xf4: out.put('ô'); break;
                case 0xf5: out.put('õ'); break;
                case 0xf6: out.put('ö'); break;
                case 0xf7: out.put('ũ'); break;
                case 0xf8: out.put('ø'); break;
                case 0xf9: out.put('ų'); break;
                case 0xfa: out.put('ú'); break;
                case 0xfb: out.put('û'); break;
                case 0xfc: out.put('ü'); break;
                case 0xfd: out.put('ý'); break;
                case 0xfe: out.put('þ'); break;
                case 0xff: out.put('ĸ'); break;
            }
        }
        return CoderResult.UNDERFLOW;
    }
}
