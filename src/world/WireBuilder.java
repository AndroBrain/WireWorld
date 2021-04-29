package world;

import world.cells.CellContainer;

public class WireBuilder {
    private final Matrix cellMat;
    private final WireMap wireMap;

    public WireBuilder(Matrix cellMat, WireMap wireMap) {
        this.cellMat = cellMat;
        this.wireMap = wireMap;
    }

    public void putWire(int x, int y) {
        wireMap.putEntry(x, y, CellContainer.wire);
        cellMat.setEntry(x, y, CellContainer.wire);
    }

    public void putHead(int x, int y) {
        wireMap.putEntry(x, y, CellContainer.head);
        cellMat.setEntry(x, y, CellContainer.head);
    }

    public void putTail(int x, int y) {
        wireMap.putEntry(x, y, CellContainer.tail);
        cellMat.setEntry(x, y, CellContainer.tail);
    }

    public void putEmpty(int x, int y) {
        cellMat.setEntry(x, y, CellContainer.empty);
    }

}
