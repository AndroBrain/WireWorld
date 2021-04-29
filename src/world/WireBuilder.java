package world;

import world.cells.CellContainer;

public class WireBuilder {
    private final Matrix cellMat;

    public WireBuilder(Matrix cellMat) {
        this.cellMat = cellMat;
    }

    public void putWire(int x, int y) {
        cellMat.setEntry(x, y, CellContainer.wire);
    }

    public void putHead(int x, int y) {
        cellMat.setEntry(x, y, CellContainer.head);
    }

    public void putTail(int x, int y) {
        cellMat.setEntry(x, y, CellContainer.tail);
    }

    public void putEmpty(int x, int y) {
        cellMat.setEntry(x, y, CellContainer.empty);
    }

}
