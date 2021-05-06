package world;

import world.rules.WireRules;

public class CreameDeLaCreame {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        WorldDimensions worldDimensions = new WorldDimensions(10, 10);
        WireMap wireMap = new WireMap(worldDimensions, new WireRules());
        WireBuilder wireBuilder = new WireBuilder(wireMap);

        wireBuilder.putWire(1, 1);
        wireBuilder.putHead(1, 0);

        System.out.println(wireMap);

        wireMap.iterate();

        System.out.println(wireMap);

        System.out.println(System.currentTimeMillis() - start);
    }
}
