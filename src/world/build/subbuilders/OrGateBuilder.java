package world.build.subbuilders;

import world.build.WireBuilder;

import static world.other.OtherConstants.*;

public class OrGateBuilder {
    public static void build(WireBuilder wireBuilder, int x, int y, char direction) {
        switch (direction) {
            case NORTH:
                buildTop(wireBuilder, y, x - 2, true);
                buildMiddle(wireBuilder, y, x - 1, true);
                buildBottom(wireBuilder, y, x, true);
                break;
            case SOUTH:
                buildTop(wireBuilder, y, x + 2, true);
                buildMiddle(wireBuilder, y, x + 1, true);
                buildBottom(wireBuilder, y, x, true);
                break;
            case WEST:
                buildTop(wireBuilder, x, y + 2, false);
                buildMiddle(wireBuilder, x, y + 1, false);
                buildBottom(wireBuilder, x, y, false);
                break;
            case EAST:
                buildTop(wireBuilder, x, y - 2, false);
                buildMiddle(wireBuilder, x, y - 1, false);
                buildBottom(wireBuilder, x, y, false);
        }
    }

    private static void buildTop(WireBuilder wireBuilder, int lineOne, int lineTwo, boolean isVertical) {
        for (int line = lineOne - 1; line < lineOne + 2; line++)
            buildWireVerticallyOrHorizontally(wireBuilder, line, lineTwo, isVertical);
    }

    private static void buildMiddle(WireBuilder wireBuilder, int lineOne, int lineTwo, boolean isVertical) {
        for (int line = lineOne - 2; line < lineOne + 3; line += 2)
            buildWireVerticallyOrHorizontally(wireBuilder, line, lineTwo, isVertical);
    }

    private static void buildBottom(WireBuilder wireBuilder, int lineOne, int lineTwo, boolean isVertical) {
        for (int line = lineOne - 2; line < lineOne + 3; line += 4)
            buildWireVerticallyOrHorizontally(wireBuilder, line, lineTwo, isVertical);
    }

    private static void buildWireVerticallyOrHorizontally(WireBuilder wireBuilder, int line, int lineTwo, boolean isVertical) {
        if (isVertical)
            wireBuilder.putWire(lineTwo, line);
        else
            wireBuilder.putWire(line, lineTwo);
    }
}
