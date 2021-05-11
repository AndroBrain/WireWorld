package world.build;

import world.WireMapManager;
import world.other.CellConstants;

public class WireBuilder {
    private final WireMapManager wireMapManager;

    public WireBuilder(WireMapManager wireMapManager) {
        this.wireMapManager = wireMapManager;
    }

    public void putWire(int x, int y) {
        wireMapManager.putEntry(x, y, CellConstants.WIRE);
    }

    public void putHead(int x, int y) {
        wireMapManager.putEntry(x, y, CellConstants.HEAD);
    }

    public void putTail(int x, int y) {
        wireMapManager.putEntry(x, y, CellConstants.TAIL);
    }

    public void putDiode(int x, int y, char direction) {
        DiodeBuilder.build(wireMapManager, x, y, direction);
    }

}
