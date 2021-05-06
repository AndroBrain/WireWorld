package world;

import world.build.WorldDimensions;
import world.cells.Cell;
import world.cells.CellContainer;
import world.neighbourhood.Neighbourhood;
import world.rules.Rules;

import java.util.HashMap;

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

    public void putEntry(int x, int y, Cell cell) {
        wireMap.put(new Position(x, y), cell);
    }

    public void iterate() {
        HashMap<Position, Cell> map = new HashMap<>();
        wireMap.forEach((position, cell) -> {
            if (cell == CellContainer.head) {
                map.put(position, CellContainer.tail);
            } else if (cell == CellContainer.tail) {
                map.put(position, CellContainer.wire);
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
