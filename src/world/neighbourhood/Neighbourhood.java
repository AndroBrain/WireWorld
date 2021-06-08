package world.neighbourhood;

import world.cells.Cell;
import world.other.Position;

import java.util.HashMap;

public interface Neighbourhood {
     int iterateNeighbourhood(HashMap<Position, Cell> map, Position position);
}