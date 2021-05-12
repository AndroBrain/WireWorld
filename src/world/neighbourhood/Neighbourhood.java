package world.neighbourhood;

import world.build.WorldDimensions;
import world.cells.Cell;
import world.other.Position;

import java.util.HashMap;

public abstract class Neighbourhood {
    final WorldDimensions worldDimensions;

    public Neighbourhood(WorldDimensions worldDimensions) {
        this.worldDimensions = worldDimensions;
    }

    public int iterateNeighbourhood(HashMap<Position, Cell> map, Position position) {
        return 0;
    }
}
