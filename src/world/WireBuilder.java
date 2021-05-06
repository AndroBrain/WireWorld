package world;

import world.cells.CellContainer;

public class WireBuilder {
    private final WireMap wireMap;

    public WireBuilder(WireMap wireMap) {
        this.wireMap = wireMap;
    }

    public void putWire(int x, int y) {
        wireMap.putEntry(x, y, CellContainer.wire);
    }

    public void putHead(int x, int y) {
        wireMap.putEntry(x, y, CellContainer.head);
    }

    public void putTail(int x, int y) {
        wireMap.putEntry(x, y, CellContainer.tail);
    }

}
