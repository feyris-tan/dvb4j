package moe.yo3explorer.dvb4j.model.descriptorEntities;

public class Subcell {
    private final int cellIdExtension;
    private final long transponderFrequency;

    public Subcell(int cellIdExtension, long transponderFrequency) {

        this.cellIdExtension = cellIdExtension;
        this.transponderFrequency = transponderFrequency;
    }

    public int getCellIdExtension() {
        return cellIdExtension;
    }

    public long getTransponderFrequency() {
        return transponderFrequency;
    }

    @Override
    public String toString() {
        return "Subcell{" +
                "cellIdExtension=" + cellIdExtension +
                ", transponderFrequency=" + transponderFrequency +
                '}';
    }
}
