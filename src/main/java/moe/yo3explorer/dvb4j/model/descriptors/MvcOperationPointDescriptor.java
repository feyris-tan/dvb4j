package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptorEntities.MvcOperationLevel;
import moe.yo3explorer.dvb4j.model.descriptorEntities.OperationPoint;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MvcOperationPointDescriptor implements Descriptor {
    private int profileIdc;
    private boolean constraintSet0;
    private boolean constraintSet1;
    private boolean constraintSet2;
    private boolean constraintSet3;
    private boolean constraintSet4;
    private boolean constraintSet5;
    private int avcCompatibleFlags;
    private MvcOperationLevel[] levels;

    @Override
    public int getTag() {
        return 51;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        profileIdc = wrap.get() & 0xff;

        byte flags = wrap.get();
        constraintSet0 = (flags & 0x80) != 0;
        constraintSet1 = (flags & 0x40) != 0;
        constraintSet2 = (flags & 0x20) != 0;
        constraintSet3 = (flags & 0x10) != 0;
        constraintSet4 = (flags & 0x08) != 0;
        constraintSet5 = (flags & 0x04) != 0;
        avcCompatibleFlags = (flags & 0x03);

        int levelCount = wrap.get() & 0xff;
        levels = new MvcOperationLevel[levelCount];
        for (int i = 0; i < levelCount; i++)
        {
            int levelIdc = wrap.get() & 0xff;
            int operationPointsCount = wrap.get() & 0xff;
            OperationPoint[] operationPoints = new OperationPoint[operationPointsCount];
            for (int j = 0; j < operationPointsCount; j++)
            {
                flags = wrap.get();
                int applicableTemporalId = wrap.get() & 0x07;
                int numTargetOutputViews = wrap.get() & 0xff;
                int esCount = wrap.get() & 0xff;
                if ((wrap.limit() - wrap.position()) < esCount)
                    return;
                int[] esReferences = new int[esCount];
                for (int k = 0; k < esCount; k++)
                {
                    esReferences[k] = wrap.get() & 0x3f;
                }
                operationPoints[j] = new OperationPoint(applicableTemporalId,numTargetOutputViews,esReferences);
            }
            levels[i] = new MvcOperationLevel(levelIdc,operationPointsCount,operationPoints);
        }
    }

    public int getProfileIdc() {
        return profileIdc;
    }

    public boolean isConstraintSet0() {
        return constraintSet0;
    }

    public boolean isConstraintSet1() {
        return constraintSet1;
    }

    public boolean isConstraintSet2() {
        return constraintSet2;
    }

    public boolean isConstraintSet3() {
        return constraintSet3;
    }

    public boolean isConstraintSet4() {
        return constraintSet4;
    }

    public boolean isConstraintSet5() {
        return constraintSet5;
    }

    public int getAvcCompatibleFlags() {
        return avcCompatibleFlags;
    }

    public MvcOperationLevel[] getLevels() {
        return levels;
    }

    @Override
    public String toString() {
        return "MvcOperationPointDescriptor{" +
                "profileIdc=" + profileIdc +
                ", constraintSet0=" + constraintSet0 +
                ", constraintSet1=" + constraintSet1 +
                ", constraintSet2=" + constraintSet2 +
                ", constraintSet3=" + constraintSet3 +
                ", constraintSet4=" + constraintSet4 +
                ", constraintSet5=" + constraintSet5 +
                ", avcCompatibleFlags=" + avcCompatibleFlags +
                ", levels=" + Arrays.toString(levels) +
                '}';
    }
}
