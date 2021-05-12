package world.build.subbuilders;

import world.build.WireBuilder;

import static world.other.OtherConstants.*;

public class ElectronGeneratorBuilder {
    public static void build(WireBuilder wireBuilder, int x, int y, String direction, int width, int height) {
        switch (direction) {
            case NORTH_EAST:
                for (int column = 1; column <= width; column++) {
                    wireBuilder.putWire(x, y - column);
                    wireBuilder.putWire(x - height - 1, y - column);
                }
                for (int row = 1; row <= height; row++) {
                    wireBuilder.putWire(x - row, y);
                    wireBuilder.putWire(x - row, y - width - 1);
                }
                break;
            case NORTH_WEST:
                for (int column = 1; column <= width; column++) {
                    wireBuilder.putWire(x, y + column);
                    wireBuilder.putWire(x - height - 1, y + column);
                }
                for (int row = 1; row <= height; row++) {
                    wireBuilder.putWire(x - row, y);
                    wireBuilder.putWire(x - row, y + width + 1);
                }
                break;
            case SOUTH_EAST:
                for (int column = 1; column <= width; column++) {
                    wireBuilder.putWire(x, y - column);
                    wireBuilder.putWire(x + height + 1, y - column);
                }
                for (int row = 1; row <= height; row++) {
                    wireBuilder.putWire(x + row, y);
                    wireBuilder.putWire(x + row, y - width - 1);
                }
                break;
            case SOUTH_WEST:
                for (int column = 1; column <= width; column++) {
                    wireBuilder.putWire(x, y + column);
                    wireBuilder.putWire(x + height + 1, y + column);
                }
                for (int row = 1; row <= height; row++) {
                    wireBuilder.putWire(x + row, y);
                    wireBuilder.putWire(x + row, y + width + 1);
                }
                break;
        }
    }


}
