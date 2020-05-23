package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.enums.ContentAssignment;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ContentDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x54;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        contentAssignments = new ContentAssignment[buffer.length / 2];
        for (int i = 0; i < buffer.length; i += 2)
        {
            contentAssignments[i / 2] = ContentAssignment.decodeContentAssignment(buffer[i]);
        }
    }

    private ContentAssignment[] contentAssignments;

    public ContentAssignment[] getContentAssignments() {
        return contentAssignments;
    }

    @Override
    public String toString() {
        return "ContentDescriptor{" +
                "ContentAssignments=" + Arrays.toString(contentAssignments) +
                '}';
    }
}
