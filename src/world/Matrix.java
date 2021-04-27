package world;

import world.cell.Cell;

public class Matrix {
    private final Cell[][] cellMat;
    private final int width;
    private final int height;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.cellMat = new Cell[width][height];
    }

    public void setEntry(int x, int y, Cell cell) {
        cellMat[x][y] = cell;
    }

    public Cell getEntry(int x, int y) {
        return cellMat[x][y];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
