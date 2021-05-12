package world;

import world.build.WorldDimensions;
import world.cells.Cell;
import world.neighbourhood.Neighbourhood;
import world.other.CellConstants;
import world.other.Position;
import world.rules.Rules;

import java.util.HashMap;

import static world.other.CellConstants.HEAD;
import static world.other.CellConstants.TAIL;

public class WireMapManager {
    private HashMap<Position, Cell> wireMap;
    private final Neighbourhood neighbourhood;
    private final Rules rules;
    private final WorldDimensions worldDimensions;

    public WireMapManager(WorldDimensions worldDimensions, Rules rules, Neighbourhood neighbourhood) {
        this.wireMap = new HashMap<>();
        this.worldDimensions = worldDimensions;
        this.neighbourhood = neighbourhood;
        this.rules = rules;
    }

    //  For Testing
    public void putEntry(int x, int y, Cell cell) {
        if (x >= 0 && x < worldDimensions.getRows() && y >= 0 && y < worldDimensions.getColumns())
            if (!wireMap.containsKey(new Position(x, y)))
                wireMap.put(new Position(x, y), cell);
            else if (cell == HEAD || cell == TAIL)
                wireMap.replace(new Position(x, y), cell);
    }

    public void iterate() {
        HashMap<Position, Cell> map = new HashMap<>();
        wireMap.forEach((position, cell) -> {
            if (cell == HEAD) {
                map.put(position, TAIL);
            } else if (cell == TAIL) {
                map.put(position, CellConstants.WIRE);
            } else {
                map.put(position, rules.update(neighbourhood.iterateNeighbourhood(wireMap, position)));
            }
        });
        wireMap = map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Wire:\n");
        wireMap.forEach((position, cell) -> sb.append(position.getX()).append(" ").append(position.getY()).append(" ").append(cell).append('\n'));
        return sb.toString();
    }

    public WorldDimensions getWorldDimensions() {
        return worldDimensions;
    }

    public HashMap<Position, Cell> getWireMap() {
        return wireMap;
    }
}
