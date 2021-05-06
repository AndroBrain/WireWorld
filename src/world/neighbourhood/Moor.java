package world.neighbourhood;

import world.Position;
import world.build.WorldDimensions;
import world.cells.Cell;
import world.cells.Head;

import java.util.HashMap;

public class Moor extends Neighbourhood {

    public Moor(WorldDimensions worldDimensions) {
        super(worldDimensions);
    }

    @Override
    public int iterateNeighbourhood(HashMap<Position, Cell> map, Position position) {
        int x = position.getX();
        int y = position.getY();
        int neighbours = 0;
        for (int i = x - 1; i < x + 2; i++)
            for (int j = y - 1; j < y + 2; j++)
                if ((i != x || j != y) && i > -1 && j > -1 && i < worldDimensions.getRows() && j < worldDimensions.getColumns())
                    if (map.get(new Position(i, j)) instanceof Head)
                        neighbours++;
        return neighbours;
    }
}
