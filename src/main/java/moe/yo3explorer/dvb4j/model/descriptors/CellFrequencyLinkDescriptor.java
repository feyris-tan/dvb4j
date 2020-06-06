package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.descriptorEntities.Cell;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptorEntities.Subcell;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class CellFrequencyLinkDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x6d;
    }

    private List<Cell> cells;

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);
        while (wrap.limit() > wrap.position())
        {
            int cellId = wrap.getShort() & 0xffff;
            long frequency = wrap.getInt() & 0xffffffffL;

            int subcellInfoLength = wrap.get() & 0xff;

            if ((subcellInfoLength * 5) > (wrap.limit() - wrap.position()))
                return;

            Subcell[] subcells = new Subcell[subcellInfoLength];
            for (int i = 0; i < subcellInfoLength; i++)
            {
                int cellIdExtension = wrap.get() & 0xff;
                long transponderFrequency = wrap.getInt() & 0xffffffffL;
                subcells[i] = new Subcell(cellIdExtension,transponderFrequency);
            }
            Cell cell = new Cell(cellId,frequency,subcells);
            if (cells == null)
                cells = new LinkedList<>();
            cells.add(cell);
        }
    }

}
