package world.other;

import javafx.scene.paint.Color;
import world.cells.*;

public class CellConstants {
    public static Cell EMPTY = new Empty(Color.BLACK);
    public static final Cell WIRE = new Wire(Color.YELLOW);
    public static final Cell TAIL = new Tail(Color.BLUE);
    public static final Cell HEAD = new Head(Color.RED);
}