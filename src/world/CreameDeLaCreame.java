package world;

import world.neighbourhood.Moor;
import world.rules.WireRules;

public class CreameDeLaCreame {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Matrix matrix = new Matrix(3, 4);
        WireMap wireMap = new WireMap(matrix, new Moor(), new WireRules());
        WireBuilder wireBuilder = new WireBuilder(matrix, wireMap);

        wireBuilder.putWire(1, 1);
        wireBuilder.putHead(1, 0);

        System.out.println(matrix);

        wireMap.iterate();

        System.out.println(matrix);
        System.out.println(System.currentTimeMillis() - start);
    }
}
