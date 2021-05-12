package world.build.subbuilders;

import world.build.WireBuilder;

import static world.other.OtherConstants.*;

public class DiodeBuilder {

    public static void build(WireBuilder wireBuilder, int x, int y, char direction) {
        for (int row = x - 1; row < x + 2; row++)
            for (int column = y - 1; column < y + 2; column++)
                if (!(row == x && column == y)) {
                    switch (direction) {
                        case NORTH:
                            if (row != x + 1)
                                wireBuilder.putWire(row, column);
                            break;
                        case SOUTH:
                            if (row != x - 1)
                                wireBuilder.putWire(row, column);
                            break;
                        case WEST:
                            if (column != y - 1)
                                wireBuilder.putWire(row, column);
                            break;
                        case EAST:
                            if (column != y + 1)
                                wireBuilder.putWire(row, column);
                            break;
                    }
                }
    }
}
