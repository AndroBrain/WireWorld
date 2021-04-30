package world.neighbourhood;

import world.Matrix;
import world.Position;

public interface Neighbourhood {
    int iterateNeighbourhood(Matrix matrix, Position position);
}
