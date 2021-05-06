package world;

import files_io.Input;

public class CreameDeLaCreame {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        WireMapManager wireMapManager = Input.load("test.txt");

        if (wireMapManager == null) {
            System.out.println("Wrong format");
            return;
        }

        System.out.println(wireMapManager);

        wireMapManager.iterate();

        System.out.println(wireMapManager);

        System.out.println(System.currentTimeMillis() - start);
    }
}
