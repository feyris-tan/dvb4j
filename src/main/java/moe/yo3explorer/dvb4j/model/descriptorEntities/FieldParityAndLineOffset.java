package moe.yo3explorer.dvb4j.model.descriptorEntities;

public class FieldParityAndLineOffset
{

    private final boolean fieldParity;
    private final int lineOffset;

    public FieldParityAndLineOffset(boolean fieldParity, int lineOffset) {

        this.fieldParity = fieldParity;
        this.lineOffset = lineOffset;
    }

    public boolean isFieldParity() {
        return fieldParity;
    }

    public int getLineOffset() {
        return lineOffset;
    }

    @Override
    public String toString() {
        return "FieldParityAndLineOffset{" +
                "fieldParity=" + fieldParity +
                ", lineOffset=" + lineOffset +
                '}';
    }
}
