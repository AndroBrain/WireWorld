package world.build.subbuilders;

import world.build.WireBuilder;

import static world.other.OtherConstants.*;

public class FuseForN5Builder {
    public static void build(WireBuilder wireBuilder, int x, int y, char direction){
        switch(direction){
            case SOUTH:
                AndNotGateBuilder.build(wireBuilder, x + 2, y);
                for(int i = x + 4; i < x + 7; i++)
                    wireBuilder.putWire(i, y);
                wireBuilder.putWire(x + 7, y + 1);
                for(int i = x + 7; i > x + 2; i--)
                    wireBuilder.putWire(i, y + 2);
                wireBuilder.putWire(x + 8, y + 3);
                wireBuilder.putWire(x + 2, y + 3);
                wireBuilder.putWire(x + 2, y + 4);
                AndNotGateBuilder.build(wireBuilder, x + 4, y + 5);
                wireBuilder.putWire(x + 5,y + 7);
                wireBuilder.putWire(x + 6,y + 8);
                wireBuilder.putWire(x + 5,y + 9);
                wireBuilder.putWire(x + 4,y + 9);
                AndNotGateBuilder.build(wireBuilder, x + 2, y + 8);
                for(int i = y + 1; i < y + 8; i++)
                    wireBuilder.putWire(x, i);
                wireBuilder.putWire(x + 6, y + 4);
                wireBuilder.putWire(x + 7, y + 4);
                break;
            case NORTH:
                y = y + 8;
                AndNotGateBuilder.build(wireBuilder, x - 2, y);
                for(int i = x - 4; i > x - 7; i--)
                    wireBuilder.putWire(i, y);
                wireBuilder.putWire(x - 7, y - 1);
                for(int i = x - 7; i < x - 2; i++)
                    wireBuilder.putWire(i, y - 2);
                wireBuilder.putWire(x - 8, y - 3);
                wireBuilder.putWire(x - 2, y - 3);
                wireBuilder.putWire(x - 2, y - 4);
                AndNotGateBuilder.build(wireBuilder, x - 4, y - 5);
                wireBuilder.putWire(x - 5,y - 7);
                wireBuilder.putWire(x - 6,y - 8);
                wireBuilder.putWire(x - 5,y - 9);
                wireBuilder.putWire(x - 4,y - 9);
                AndNotGateBuilder.build(wireBuilder, x - 2, y - 8);
                for(int i = y - 1; i > y - 8; i--)
                    wireBuilder.putWire(x, i);
                wireBuilder.putWire(x - 6, y - 4);
                wireBuilder.putWire(x - 7, y - 4);
                break;
            case WEST:
                AndNotGateBuilder.build(wireBuilder, y, x + 2);
                for(int i = x + 4; i < x + 7; i++)
                    wireBuilder.putWire(y, i);
                wireBuilder.putWire(y + 1, x + 7);
                for(int i = x + 7; i > x + 2; i--)
                    wireBuilder.putWire(y + 2,i);
                wireBuilder.putWire(y + 3, x + 8);
                wireBuilder.putWire(y + 3, x + 2);
                wireBuilder.putWire(y + 4, x + 2);
                AndNotGateBuilder.build(wireBuilder, y + 5, x + 4);
                wireBuilder.putWire(y + 7,x + 5);
                wireBuilder.putWire(y + 8,x + 6);
                wireBuilder.putWire(y + 9,x + 5);
                wireBuilder.putWire(y + 9,x + 4);
                AndNotGateBuilder.build(wireBuilder, y + 8, x + 2);
                for(int i = y + 1; i < y + 8; i++)
                    wireBuilder.putWire(i, x);
                wireBuilder.putWire(y + 4, x + 6);
                wireBuilder.putWire(y + 4, x + 7);
                break;
            case EAST:
                y = y + 8;
                AndNotGateBuilder.build(wireBuilder, y, x - 2);
                for(int i = x - 4; i > x - 7; i--)
                    wireBuilder.putWire(y, i);
                wireBuilder.putWire(y - 1, x - 7);
                for(int i = x - 7; i < x - 2; i++)
                    wireBuilder.putWire(y - 2,i);
                wireBuilder.putWire(y - 3, x - 8);
                wireBuilder.putWire(y - 3, x - 2);
                wireBuilder.putWire(y - 4, x - 2);
                AndNotGateBuilder.build(wireBuilder, y - 5, x - 4);
                wireBuilder.putWire(y - 7,x - 5);
                wireBuilder.putWire(y - 8,x - 6);
                wireBuilder.putWire(y - 9,x - 5);
                wireBuilder.putWire(y - 9,x - 4);
                AndNotGateBuilder.build(wireBuilder, y - 8, x - 2);
                for(int i = y - 1; i > y - 8; i--)
                    wireBuilder.putWire(i, x);
                wireBuilder.putWire(y - 4, x - 6);
                wireBuilder.putWire(y - 4, x - 7);
                break;

        }
    }
}
