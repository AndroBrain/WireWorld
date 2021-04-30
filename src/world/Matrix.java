package world;

import world.cells.*;

import java.util.Arrays;

public class Matrix {
    private final Cell[][] cellMat;
    private final int rows;
    private final int columns;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cellMat = new Cell[rows][columns];
        fillMatrixWithEmptyCells();
    }

    public void setEntry(int row, int column, Cell cell) {
        cellMat[row][column] = cell;
    }

    public Cell getEntry(int row, int column) {
        return cellMat[row][column];
    }

    private void fillMatrixWithEmptyCells() {
        for (int column = 0; column < rows; column++)
            Arrays.fill(cellMat[column], CellContainer.empty);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix{\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
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

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Matrix))
            return false;
        Matrix differentMatrix = (Matrix) obj;
        if (getColumns() != differentMatrix.getColumns() || getRows() != differentMatrix.getRows())
            return false;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                if (getEntry(i, j) != differentMatrix.getEntry(i, j))
                    return false;
            }
        return true;
    }
}
