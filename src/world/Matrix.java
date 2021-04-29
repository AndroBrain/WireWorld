package world;

import world.cells.*;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix{\n");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = cellMat[i][j];
                if (cell instanceof Empty) {
                    sb.append("Empty ");
                } else if (cell instanceof Wire) {
                    sb.append("Wire  ");
                } else if (cell instanceof Head) {
                    sb.append("Head  ");
                } else if (cell instanceof Tail) {
                    sb.append("Tail  ");
                } else {
                    sb.append("ERROR ");
                }
            }
            sb.append('\n');
        }
        sb.append('}');

        return sb.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Matrix))
            return false;
        Matrix differentMatrix = (Matrix) obj;
        if (getHeight() != differentMatrix.getHeight() || getWidth() != differentMatrix.getWidth())
            return false;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                if (getEntry(i, j) != differentMatrix.getEntry(i, j))
                    return false;
            }
        return true;
    }
}
