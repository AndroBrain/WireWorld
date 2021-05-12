package world.build.subbuilders;

import world.build.WireBuilder;

public class AndNotGateBuilder {
    public static void build(WireBuilder wireBuilder, int x, int y) {
        for (int column = y - 1; column < y + 2; column++)
            wireBuilder.putWire(x, column);
        for (int row = x - 1; row < x + 2; row += 2)
            wireBuilder.putWire(row, y);
    }
}
