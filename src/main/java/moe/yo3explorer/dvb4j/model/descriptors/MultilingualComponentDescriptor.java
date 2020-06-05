package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.MultilingualComponent;
import moe.yo3explorer.dvb4j.text.UsedCharsets;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MultilingualComponentDescriptor implements Descriptor {
    private int componentType;

    @Override
    public int getTag() {
        return 0x5e;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        componentType = wrap.get() & 0xff;

        componentList = new LinkedList<>();
        while (wrap.limit() > wrap.position())
        {
            byte[] langCodeWrap = new byte[3];
            wrap.get(langCodeWrap);
            String langCode = new String(langCodeWrap, StandardCharsets.US_ASCII);

            int textDescriptionLength = wrap.get() & 0xff;
            int remain = wrap.limit() - wrap.position();
            if (remain < textDescriptionLength)
                return;
            byte[] textDescription = new byte[textDescriptionLength];
            String text = new String(textDescription, UsedCharsets.DVB);
            MultilingualComponent mc = new MultilingualComponent(langCode,text);
            componentList.add(mc);
        }
    }

    private List<MultilingualComponent> componentList;

    public int getComponentType() {
        return componentType;
    }

    public List<MultilingualComponent> getComponentList() {
        return Collections.unmodifiableList(componentList);
    }


}
