package world.neighbourhood;

import world.Position;
import world.WorldDimensions;
import world.cells.Cell;

import java.util.HashMap;

public abstract class Neighbourhood {
    HashMap<Position, Cell> map;
    WorldDimensions worldDimensions;

    public Neighbourhood(HashMap<Position, Cell> map, WorldDimensions worldDimensions) {
        this.map = map;
        this.worldDimensions = worldDimensions;
    }

    public int iterateNeighbourhood(Position position) {
        return 0;
    }
}
