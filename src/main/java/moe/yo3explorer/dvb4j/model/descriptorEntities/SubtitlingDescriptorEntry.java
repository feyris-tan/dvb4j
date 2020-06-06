package moe.yo3explorer.dvb4j.model.descriptorEntities;

import moe.yo3explorer.dvb4j.model.enums.ComponentType;

public class SubtitlingDescriptorEntry
{
    public SubtitlingDescriptorEntry(String langCode, ComponentType componentType, int compositionPageId, int ancillaryPageId) {
        this.langCode = langCode;
        this.componentType = componentType;
        this.compositionPageId = compositionPageId;
        this.ancillaryPageId = ancillaryPageId;
    }

    private String langCode;
    private ComponentType componentType;
    private int compositionPageId;
    private int ancillaryPageId;

    public String getLangCode() {
        return langCode;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public int getCompositionPageId() {
        return compositionPageId;
    }

    public int getAncillaryPageId() {
        return ancillaryPageId;
    }

    @Override
    public String toString() {
        return "SubtitlingDescriptorEntry{" +
                "langCode='" + langCode + '\'' +
                ", componentType=" + componentType +
                ", compositionPageId=" + compositionPageId +
                ", ancillaryPageId=" + ancillaryPageId +
                '}';
    }
}
