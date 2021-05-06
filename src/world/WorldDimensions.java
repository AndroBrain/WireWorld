package world;

public class WorldDimensions {
    private final int rows;
    private final int columns;

    public WorldDimensions(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
