package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptorEntities.TeletextPageMetadata;
import moe.yo3explorer.dvb4j.model.enums.TeletextType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TeletextDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x56;
    }

    @Override
    public void readFrom(byte[] buffer) {
        textPages = new ArrayList<>();
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        while (wrap.limit() > wrap.position())
        {
            byte[] languageCodeRaw = new byte[3];
            wrap.get(languageCodeRaw);
            String languageCode = new String(languageCodeRaw, StandardCharsets.US_ASCII);

            byte teletextFlag = wrap.get();
            int teletextTypeRaw = teletextFlag >> 3;
            TeletextType teletextType = TeletextType.parse(teletextTypeRaw);

            int teletextMagazineNumber = teletextFlag & 0x07;

            int teletextPageNumber = wrap.get() & 0xff;

            textPages.add(new TeletextPageMetadata(languageCode,teletextType,teletextMagazineNumber,teletextPageNumber));
        }
    }

    private ArrayList<TeletextPageMetadata> textPages;

    public ArrayList<TeletextPageMetadata> getTextPages() {
        return textPages;
    }

    @Override
    public String toString() {
        return "TeletextDescriptor{" +
                "numTextPages=" + textPages.size() +
                '}';
    }
}
