package moe.yo3explorer.dvb4j.model;

public class PMTTag {

    public PMTTag(int tag, byte[] tagContent) {
        this.tag = tag;
        this.tagContent = tagContent;
    }

    private int tag;
    private final byte[] tagContent;

    public int getTag() {
        return tag;
    }

    public byte[] getTagContent() {
        return tagContent;
    }
}
