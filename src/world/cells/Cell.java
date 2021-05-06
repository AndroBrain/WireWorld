package world.cells;

import javafx.scene.paint.Color;

public abstract class Cell {
    private final Color color;

    public Cell(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
