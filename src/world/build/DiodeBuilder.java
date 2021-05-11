package world.build;

import world.WireMapManager;

import static world.other.CellConstants.WIRE;
import static world.other.OtherConstants.*;

public class DiodeBuilder {

    public static void build(WireMapManager wireMapManager, int x, int y, char direction) {
        for (int row = x - 1; row < x + 2; row++)
            for (int column = y - 1; column < y + 2; column++)
                if (!(row == x && column == y)) {
                    switch (direction) {
                        case NORTH:
                            if (row != x + 1)
                                wireMapManager.putEntry(row, column, WIRE);
                            break;
                        case SOUTH:
                            if (row != x - 1)
                                wireMapManager.putEntry(row, column, WIRE);
                            break;
                        case WEST:
                            if (column != y - 1)
                                wireMapManager.putEntry(row, column, WIRE);
                            break;
                        case EAST:
                            if (column != y + 1)
                                wireMapManager.putEntry(row, column, WIRE);
                            break;
                    }
                }
    }
}
