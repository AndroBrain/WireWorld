package world.rules;

import world.cells.Cell;
import world.other.CellConstants;

public class WireRules implements Rules {
    @Override
    public Cell update(int heads) {
        if (heads == 1 || heads == 2) {
            return CellConstants.HEAD;
        }
        return CellConstants.WIRE;
    }
}
