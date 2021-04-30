package world.neighbourhood;

import world.Matrix;
import world.Position;
import world.cells.Head;

public class Moor implements Neighbourhood {

    @Override
    public int iterateNeighbourhood(Matrix matrix, Position position) {
        int x = position.getX();
        int y = position.getY();
        int neighbours = 0;
        for (int i = x - 1; i < x + 2; i++)
            for (int j = y - 1; j < y + 2; j++)
                if ((i != x || j != y) && i > -1 && j > -1 && i < matrix.getRows() && j < matrix.getColumns())
                    if (matrix.getEntry(i, j) instanceof Head)
                        neighbours++;
        return neighbours;
    }
}
