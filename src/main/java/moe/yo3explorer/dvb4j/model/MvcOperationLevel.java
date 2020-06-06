package moe.yo3explorer.dvb4j.model;

import java.util.Arrays;

public class MvcOperationLevel {
    private final int levelIdc;
    private final int operationPointsCount;
    private final OperationPoint[] operationPoints;

    public MvcOperationLevel(int levelIdc, int operationPointsCount, OperationPoint[] operationPoints) {
        this.levelIdc = levelIdc;
        this.operationPointsCount = operationPointsCount;
        this.operationPoints = operationPoints;
    }

    public int getLevelIdc() {
        return levelIdc;
    }

    public int getOperationPointsCount() {
        return operationPointsCount;
    }

    public OperationPoint[] getOperationPoints() {
        return operationPoints;
    }

    @Override
    public String toString() {
        return "MvcOperationLevel{" +
                "levelIdc=" + levelIdc +
                ", operationPointsCount=" + operationPointsCount +
                ", operationPoints=" + Arrays.toString(operationPoints) +
                '}';
    }
}
