package moe.yo3explorer.dvb4j.text;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class CodeTable00CharsetDecoder extends CharsetDecoder {
    CodeTable00CharsetDecoder(CodeTable00Charset charset) {
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

            if (read == 0xc1)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('À'); continue;
                    case 'E': out.put('È'); continue;
                    case 'I': out.put('Ì'); continue;
                    case 'O': out.put('Ò'); continue;
                    case 'U': out.put('Ù'); continue;
                    case 'a': out.put('à'); continue;
                    case 'e': out.put('è'); continue;
                    case 'i': out.put('ì'); continue;
                    case 'o': out.put('ò'); continue;
                    case 'u': out.put('ù'); continue;
                    default: continue;
                }
            }
            else if (read == 0xc2)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('Á'); continue;
                    case 'C': out.put('Ć'); continue;
                    case 'E': out.put('É'); continue;
                    case 'I': out.put('Í'); continue;
                    case 'L': out.put('Ĺ'); continue;
                    case 'N': out.put('Ń'); continue;
                    case 'O': out.put('Ó'); continue;
                    case 'R': out.put('Ŕ'); continue;
                    case 'S': out.put('Ś'); continue;
                    case 'U': out.put('Ú'); continue;
                    case 'Y': out.put('Ý'); continue;
                    case 'Z': out.put('Ź'); continue;
                    case 'a': out.put('á'); continue;
                    case 'c': out.put('ć'); continue;
                    case 'e': out.put('é'); continue;
                    case 'g': out.put('ģ'); continue;
                    case 'i': out.put('í'); continue;
                    case 'l': out.put('ĺ'); continue;
                    case 'n': out.put('ń'); continue;
                    case 'o': out.put('ó'); continue;
                    case 'r': out.put('ŕ'); continue;
                    case 's': out.put('ś'); continue;
                    case 'u': out.put('ú'); continue;
                    case 'y': out.put('ý'); continue;
                    case 'z': out.put('ź'); continue;
                    default: continue;
                }
            }
            else if (read == 0xc3)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('Â'); continue;
                    case 'C': out.put('Ĉ'); continue;
                    case 'E': out.put('Ê'); continue;
                    case 'G': out.put('Ĝ'); continue;
                    case 'H': out.put('Ĥ'); continue;
                    case 'I': out.put('Î'); continue;
                    case 'J': out.put('Ĵ'); continue;
                    case 'O': out.put('Ô'); continue;
                    case 'S': out.put('Ŝ'); continue;
                    case 'U': out.put('Û'); continue;
                    case 'W': out.put('Ŵ'); continue;
                    case 'Y': out.put('Ŷ'); continue;
                    case 'a': out.put('â'); continue;
                    case 'c': out.put('ĉ'); continue;
                    case 'e': out.put('ê'); continue;
                    case 'g': out.put('ĝ'); continue;
                    case 'h': out.put('ĥ'); continue;
                    case 'i': out.put('î'); continue;
                    case 'j': out.put('ĵ'); continue;
                    case 'o': out.put('ô'); continue;
                    case 's': out.put('ŝ'); continue;
                    case 'u': out.put('û'); continue;
                    case 'w': out.put('ŵ'); continue;
                    case 'y': out.put('ŷ'); continue;
                    default: continue;
                }
            }
            else if (read == 0xc4)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('Ã'); continue;
                    case 'I': out.put('Ĩ'); continue;
                    case 'N': out.put('Ñ'); continue;
                    case 'O': out.put('Õ'); continue;
                    case 'U': out.put('Ũ'); continue;
                    case 'a': out.put('ã'); continue;
                    case 'i': out.put('ĩ'); continue;
                    case 'n': out.put('ñ'); continue;
                    case 'o': out.put('õ'); continue;
                    case 'u': out.put('ũ'); continue;
                    default: continue;
                }
            }
            else if (read == 0xc5)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('Ā'); continue;
                    case 'E': out.put('Ē'); continue;
                    case 'I': out.put('Ī'); continue;
                    case 'O': out.put('Ō'); continue;
                    case 'U': out.put('Ū'); continue;
                    case 'a': out.put('ā'); continue;
                    case 'e': out.put('ē'); continue;
                    case 'i': out.put('ī'); continue;
                    case 'o': out.put('ō'); continue;
                    case 'u': out.put('ū'); continue;
                    default: continue;
                }
            }
            else if (read == 0xc6)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('Ă'); continue;
                    case 'G': out.put('Ğ'); continue;
                    case 'U': out.put('Ŭ'); continue;
                    case 'a': out.put('ă'); continue;
                    case 'g': out.put('ğ'); continue;
                    case 'u': out.put('ŭ'); continue;
                    default: continue;
                }
            }
            else if (read == 0xc7)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'C': out.put('Ċ'); continue;
                    case 'E': out.put('Ė'); continue;
                    case 'G': out.put('Ġ'); continue;
                    case 'I': out.put('İ'); continue;
                    case 'Z': out.put('Ż'); continue;
                    case 'c': out.put('ċ'); continue;
                    case 'e': out.put('ė'); continue;
                    case 'g': out.put('ġ'); continue;
                    case 'z': out.put('ż'); continue;
                    default: continue;
                }
            }
            else if (read == 0xc8)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('Ä'); continue;
                    case 'E': out.put('Ë'); continue;
                    case 'I': out.put('Ï'); continue;
                    case 'O': out.put('Ö'); continue;
                    case 'U': out.put('Ü'); continue;
                    case 'Y': out.put('Ÿ'); continue;
                    case 'a': out.put('ä'); continue;
                    case 'e': out.put('ë'); continue;
                    case 'i': out.put('ï'); continue;
                    case 'o': out.put('ö'); continue;
                    case 'u': out.put('ü'); continue;
                    case 'y': out.put('ÿ'); continue;
                    default: continue;
                }
            }
            else if (read == 0xca)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('Å'); continue;
                    case 'U': out.put('Ů'); continue;
                    case 'a': out.put('å'); continue;
                    case 'u': out.put('ů'); continue;
                    default: continue;
                }
            }
            else if (read == 0xcb)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'C': out.put('Ç'); continue;
                    case 'G': out.put('Ģ'); continue;
                    case 'K': out.put('Ķ'); continue;
                    case 'L': out.put('Ļ'); continue;
                    case 'N': out.put('Ņ'); continue;
                    case 'R': out.put('Ŗ'); continue;
                    case 'S': out.put('Ş'); continue;
                    case 'T': out.put('Ţ'); continue;
                    case 'c': out.put('ç'); continue;
                    case 'k': out.put('ķ'); continue;
                    case 'l': out.put('ļ'); continue;
                    case 'n': out.put('ņ'); continue;
                    case 'r': out.put('ŗ'); continue;
                    case 's': out.put('ş'); continue;
                    case 't': out.put('ţ'); continue;
                    default: continue;
                }
            }
            else if (read == 0xcd)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'O': out.put('Ő'); continue;
                    case 'U': out.put('Ű'); continue;
                    case 'o': out.put('ő'); continue;
                    case 'u': out.put('ű'); continue;
                    default: continue;
                }
            }
            else if (read == 0xce)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'A': out.put('Ą'); continue;
                    case 'E': out.put('Ę'); continue;
                    case 'I': out.put('Į'); continue;
                    case 'U': out.put('Ų'); continue;
                    case 'a': out.put('ą'); continue;
                    case 'e': out.put('ę'); continue;
                    case 'i': out.put('į'); continue;
                    case 'u': out.put('ų'); continue;
                    default: continue;
                }
            }
            else if (read == 0xcf)
            {
                read = in.get() & 0xff;
                switch (read)
                {
                    case 'C': out.put('Č'); continue;
                    case 'D': out.put('Ď'); continue;
                    case 'E': out.put('Ě'); continue;
                    case 'L': out.put('Ľ'); continue;
                    case 'N': out.put('Ň'); continue;
                    case 'R': out.put('Ř'); continue;
                    case 'S': out.put('Š'); continue;
                    case 'T': out.put('Ť'); continue;
                    case 'Z': out.put('Ž'); continue;
                    case 'c': out.put('č'); continue;
                    case 'd': out.put('ď'); continue;
                    case 'e': out.put('ě'); continue;
                    case 'l': out.put('ľ'); continue;
                    case 'n': out.put('ň'); continue;
                    case 'r': out.put('ř'); continue;
                    case 's': out.put('š'); continue;
                    case 't': out.put('ť'); continue;
                    case 'z': out.put('ž'); continue;
                    default: continue;
                }
            }

            switch (read)
            {
                case 0xA0: out.put('\u00A0'); continue;
                case 0xA1: out.put('¡'); continue;
                case 0xA2: out.put('¢'); continue;
                case 0xA3: out.put('£'); continue;
                case 0xA4: out.put('€'); continue;
                case 0xA5: out.put('¥'); continue;
                case 0xA7: out.put('§'); continue;
                case 0xA8: out.put('¤'); continue;
                case 0xA9: out.put('‘'); continue;
                case 0xAA: out.put('“'); continue;
                case 0xAB: out.put('«'); continue;
                case 0xAC: out.put('←'); continue;
                case 0xAD: out.put('↑'); continue;
                case 0xAE: out.put('→'); continue;
                case 0xAF: out.put('↓'); continue;
                case 0xB0: out.put('°'); continue;
                case 0xB1: out.put('±'); continue;
                case 0xB2: out.put('²'); continue;
                case 0xB3: out.put('³'); continue;
                case 0xB4: out.put('×'); continue;
                case 0xB5: out.put('µ'); continue;
                case 0xB6: out.put('¶'); continue;
                case 0xB7: out.put('·'); continue;
                case 0xB8: out.put('÷'); continue;
                case 0xB9: out.put('’'); continue;
                case 0xBA: out.put('”'); continue;
                case 0xBB: out.put('»'); continue;
                case 0xBC: out.put('¼'); continue;
                case 0xBD: out.put('½'); continue;
                case 0xBE: out.put('¾'); continue;
                case 0xBF: out.put('¿'); continue;
                case 0xD0: out.put('―'); continue;
                case 0xD1: out.put('¹'); continue;
                case 0xD2: out.put('®'); continue;
                case 0xD3: out.put('©'); continue;
                case 0xD4: out.put('™'); continue;
                case 0xD5: out.put('♪'); continue;
                case 0xD6: out.put('¬'); continue;
                case 0xD7: out.put('¦'); continue;
                case 0xDC: out.put('⅛'); continue;
                case 0xDD: out.put('⅜'); continue;
                case 0xDE: out.put('⅝'); continue;
                case 0xDF: out.put('⅞'); continue;
                case 0xE0: out.put('Ω'); continue;
                case 0xE1: out.put('Æ'); continue;
                case 0xE2: out.put('Đ'); continue;
                case 0xE3: out.put('ª'); continue;
                case 0xE4: out.put('Ħ'); continue;
                case 0xE6: out.put('Ĳ'); continue;
                case 0xE7: out.put('Ŀ'); continue;
                case 0xE8: out.put('Ł'); continue;
                case 0xE9: out.put('Ø'); continue;
                case 0xEA: out.put('Œ'); continue;
                case 0xEB: out.put('º'); continue;
                case 0xEC: out.put('Þ'); continue;
                case 0xED: out.put('Ŧ'); continue;
                case 0xEE: out.put('Ŋ'); continue;
                case 0xEF: out.put('ŉ'); continue;
                case 0xF0: out.put('ĸ'); continue;
                case 0xF1: out.put('æ'); continue;
                case 0xF2: out.put('đ'); continue;
                case 0xF3: out.put('ð'); continue;
                case 0xF4: out.put('ħ'); continue;
                case 0xF5: out.put('ı'); continue;
                case 0xF6: out.put('ĳ'); continue;
                case 0xF7: out.put('ŀ'); continue;
                case 0xF8: out.put('ł'); continue;
                case 0xF9: out.put('ø'); continue;
                case 0xFA: out.put('œ'); continue;
                case 0xFB: out.put('ß'); continue;
                case 0xFC: out.put('þ'); continue;
                case 0xFD: out.put('ŧ'); continue;
                case 0xFE: out.put('ŋ'); continue;
                case 0xFF: out.put('\u00AD'); continue;
                default:
                    continue;
            }
        }
        return CoderResult.UNDERFLOW;
    }
}
