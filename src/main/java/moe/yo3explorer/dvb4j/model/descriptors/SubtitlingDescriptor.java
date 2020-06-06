package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptorEntities.SubtitlingDescriptorEntry;
import moe.yo3explorer.dvb4j.model.enums.ComponentType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SubtitlingDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x59;
    }

    @Override
    public void readFrom(byte[] buffer) {
        entries = new ArrayList<>();
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        while (wrap.limit() > wrap.position())
        {
            byte[] langCodeRaw = new byte[3];
            wrap.get(langCodeRaw);
            String langCode = new String(langCodeRaw, StandardCharsets.US_ASCII);

            ComponentType componentType = ComponentType.decode((byte) 0x03,wrap.get());

            int compositionPageId = wrap.getShort() & 0xffff;

            int ancillaryPageId = wrap.getShort() & 0xffff;

            entries.add(new SubtitlingDescriptorEntry(langCode,componentType,compositionPageId,ancillaryPageId));
        }
    }

    private ArrayList<SubtitlingDescriptorEntry> entries;

    public ArrayList<SubtitlingDescriptorEntry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "SubtitlingDescriptor{" +
                "numEntries=" + entries.size() +
                '}';
    }
}
