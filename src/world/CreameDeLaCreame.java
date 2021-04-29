package world;

import world.neighbourhood.Moor;
import world.rules.WireRules;

public class CreameDeLaCreame {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Matrix matrix = new Matrix(3, 3);
        WireBuilder wireBuilder = new WireBuilder(matrix);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                wireBuilder.putEmpty(i, j);
        wireBuilder.putWire(1, 1);
        wireBuilder.putHead(1, 0);

        System.out.println(matrix);

        WireMap wireMap = new WireMap(matrix, new Moor(), new WireRules());
        wireMap.iterate();

        System.out.println(matrix);
        System.out.println(System.currentTimeMillis() - start);
    }
}
