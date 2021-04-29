package world.rules;

import world.cells.Cell;
import world.cells.CellContainer;

public class WireRules implements Rules {
    @Override
    public Cell update(int heads) {
        if (heads == 1 || heads == 2) {
            return CellContainer.head;
        }
        return CellContainer.wire;
    }
}
