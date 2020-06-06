package moe.yo3explorer.dvb4j.model.descriptorEntities;

import java.util.Arrays;

public class Cell {
    private final int cellId;
    private final long frequency;
    private final Subcell[] subcells;

    public Cell(int cellId, long frequency, Subcell[] subcells) {
        this.cellId = cellId;
        this.frequency = frequency;
        this.subcells = subcells;
    }

    public int getCellId() {
        return cellId;
    }

    public long getFrequency() {
        return frequency;
    }

    public Subcell[] getSubcells() {
        return subcells;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "cellId=" + cellId +
                ", frequency=" + frequency +
                ", subcells=" + Arrays.toString(subcells) +
                '}';
    }
}
