package world.neighbourhood;

import world.cells.Cell;
import world.other.Position;

import java.util.HashMap;

public interface Neighbourhood {
    default int iterateNeighbourhood(HashMap<Position, Cell> map, Position position) {
        return 0;
    }
}
