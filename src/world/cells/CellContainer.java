package world.cells;

import javafx.scene.paint.Color;

public class CellContainer {
    public static Cell empty = new Empty(Color.BLACK);
    public static Cell wire = new Wire(Color.YELLOW);
    public static Cell tail = new Tail(Color.BLUE);
    public static Cell head = new Head(Color.RED);
}
