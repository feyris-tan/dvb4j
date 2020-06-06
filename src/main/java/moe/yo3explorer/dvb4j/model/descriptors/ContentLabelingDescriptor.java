package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;

import java.nio.ByteBuffer;

public class ContentLabelingDescriptor implements Descriptor {
    private int metadataApplicationFormat;
    private long metadataApplicationFormatIdentifier;
    private boolean contentReferenceIdRecordPresent;
    private int contentTimeBaseIndicator;
    private byte[] contentReferenceId;
    private long contentTimeBaseValue;
    private long metadataTimeBaseValue;
    private int contentId;
    private int timeBaseAssociationDataLength;

    @Override
    public int getTag() {
        return 36;
    }

    @Override
    public String toString() {
        return "ContentLabelingDescriptor{" +
                "metadataApplicationFormat=" + metadataApplicationFormat +
                ", contentReferenceIdRecordPresent=" + contentReferenceIdRecordPresent +
                ", contentTimeBaseIndicator=" + contentTimeBaseIndicator +
                '}';
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        metadataApplicationFormat = wrap.getShort() & 0xffff;
        if (metadataApplicationFormat == 0xffff)
        {
            metadataApplicationFormatIdentifier = wrap.getInt() & 0xffffffffL;
        }

        byte flags = wrap.get();
        contentReferenceIdRecordPresent = (flags & 0x80) != 0;
        contentTimeBaseIndicator = (flags & 0x78);
        if (contentReferenceIdRecordPresent)
        {
            int contentReferenceIdRecordLength = wrap.get() & 0xff;
            contentReferenceId = new byte[contentReferenceIdRecordLength];
            wrap.get(contentReferenceId);
        }

        if (contentTimeBaseIndicator == 1 || contentTimeBaseIndicator == 2)
        {
            flags = wrap.get();
            contentTimeBaseValue  = (long)(flags & 0x01) << 33;
            contentTimeBaseValue += (wrap.getInt() & 0xFFFFFFFFL);

            flags = wrap.get();
            metadataTimeBaseValue = (long)(flags & 0x01) << 33;
            metadataTimeBaseValue += (wrap.getInt() & 0xFFFFFFFFL);
        }
        if (contentTimeBaseIndicator == 2)
        {
            contentId = wrap.get() & 0x7f;
        }
        if (contentTimeBaseIndicator >= 3 && contentTimeBaseIndicator <= 7)
        {
            timeBaseAssociationDataLength = wrap.get() & 0xff;
        }
    }

    public int getMetadataApplicationFormat() {
        return metadataApplicationFormat;
    }

    public long getMetadataApplicationFormatIdentifier() {
        return metadataApplicationFormatIdentifier;
    }

    public boolean isContentReferenceIdRecordPresent() {
        return contentReferenceIdRecordPresent;
    }

    public int getContentTimeBaseIndicator() {
        return contentTimeBaseIndicator;
    }

    public byte[] getContentReferenceId() {
        return contentReferenceId;
    }

    public long getContentTimeBaseValue() {
        return contentTimeBaseValue;
    }

    public long getMetadataTimeBaseValue() {
        return metadataTimeBaseValue;
    }

    public int getContentId() {
        return contentId;
    }

    public int getTimeBaseAssociationDataLength() {
        return timeBaseAssociationDataLength;
    }
}
