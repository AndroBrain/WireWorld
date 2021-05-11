package world.build;

import world.WireMapManager;
import world.cells.CellContainer;

public class WireBuilder {
    private final WireMapManager wireMapManager;

    public WireBuilder(WireMapManager wireMapManager) {
        this.wireMapManager = wireMapManager;
    }

    public void putWire(int x, int y) {
        wireMapManager.putEntry(x, y, CellContainer.wire);
    }

    public void putHead(int x, int y) {
        wireMapManager.putEntry(x, y, CellContainer.head);
    }

    public void putTail(int x, int y) {
        wireMapManager.putEntry(x, y, CellContainer.tail);
    }

    public void putDiode(int x, int y, char direction) {
        DiodeBuilder.build(wireMapManager, x, y, direction);
    }

}
