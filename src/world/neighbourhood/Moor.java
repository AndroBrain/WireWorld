package world.neighbourhood;

import world.cells.Cell;
import world.other.Position;

import java.util.HashMap;

import static world.other.CellConstants.HEAD;

public class Moor implements Neighbourhood {
    @Override
    public int iterateNeighbourhood(HashMap<Position, Cell> map, Position position) {
        int x = position.getX();
        int y = position.getY();
        int neighbours = 0;
        for (int i = x - 1; i < x + 2; i++)
            for (int j = y - 1; j < y + 2; j++)
                if (map.get(new Position(i, j)) == HEAD)
                    neighbours++;
        return neighbours;
    }
}
