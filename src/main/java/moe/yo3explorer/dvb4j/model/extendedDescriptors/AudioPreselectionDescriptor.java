package moe.yo3explorer.dvb4j.model.extendedDescriptors;

import moe.yo3explorer.dvb4j.model.descriptorEntities.AudioPreselection;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.AudioRenderingIndication;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class AudioPreselectionDescriptor implements Descriptor {
    private List<AudioPreselection> preselectionList;

    @Override
    public int getTag() {
        return 0x19;
    }

    @Override
    public void readFrom(byte[] buffer) {
        preselectionList = new LinkedList<>();
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        int numPreselections = (wrap.get() & 0xF8) >> 3;

        for (int i = 0; i < numPreselections; i++)
        {
            byte flags = wrap.get();
            int preselectionId = (flags & 0xF8) >> 3;

            int renderingIndicationRa = (flags & 0x07);
            AudioRenderingIndication audioRenderingIndication = AudioRenderingIndication.values()[renderingIndicationRa];

            flags = wrap.get();
            boolean audioDescription = (flags & 0x80) != 0;
            boolean spokenSubtitles = (flags & 0x40) != 0;
            boolean dialogueEnhancement = (flags & 0x20) != 0;
            boolean interactivityEnabled = (flags & 0x10) != 0;
            boolean languageCodePresent = (flags & 0x08) != 0;
            boolean textLabelPresent = (flags & 0x04) != 0;
            boolean multiStreamInfoPresent = (flags & 0x02) != 0;
            boolean futureExtension = (flags & 0x01) != 0;

            String iso639languageCode = null;
            if (languageCodePresent)
            {
                byte[] langCodeBuffer = new byte[3];
                wrap.get(langCodeBuffer);
                iso639languageCode = new String(langCodeBuffer,StandardCharsets.US_ASCII);
            }

            Integer messageId = null;
            if (textLabelPresent)
            {
                messageId = wrap.get() & 0xff;
            }

            byte[] auxComponents = null;
            if (multiStreamInfoPresent)
            {
                int numAuxComponents = (wrap.get() & 0xff) >> 5;
                auxComponents = new byte[numAuxComponents];
                wrap.get(auxComponents);
            }

            byte[] futureExtensionData = null;
            if (futureExtension)
            {
                int futureExtensionLength = (wrap.get() & 0x1f);
                futureExtensionData = new byte[futureExtensionLength];
                wrap.get(futureExtensionData);
            }

            AudioPreselection audioPreselection = new AudioPreselection(audioDescription,spokenSubtitles,dialogueEnhancement,interactivityEnabled,
                    languageCodePresent,textLabelPresent,multiStreamInfoPresent,futureExtension,iso639languageCode,messageId,auxComponents,
                    futureExtensionData);
            preselectionList.add(audioPreselection);
        }
    }

    public List<AudioPreselection> getPreselectionList() {
        return preselectionList;
    }

    @Override
    public String toString() {
        return "AudioPreselectionDescriptor{" +
                "numPreselectionList=" + preselectionList.size() +
                '}';
    }
}
