package world;

import world.cell.Empty;
import world.cell.Head;
import world.cell.Tail;
import world.cell.Wire;

public class WireBuilder {
    private final Matrix cellMat;

    public WireBuilder(Matrix cellMat) {
        this.cellMat = cellMat;
    }

    public void putWire(int x, int y) {
        cellMat.setEntry(x, y, new Wire());
    }

    public void putHead(int x, int y) {
        cellMat.setEntry(x, y, new Head());
    }

    public void putTail(int x, int y) {
        cellMat.setEntry(x, y, new Tail());
    }

    public void putEmpty(int x, int y) {
        cellMat.setEntry(x, y, new Empty());
    }

}
