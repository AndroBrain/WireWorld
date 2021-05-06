package world;

import world.cells.Cell;
import world.cells.CellContainer;
import world.cells.Head;
import world.cells.Tail;
import world.neighbourhood.Moor;
import world.neighbourhood.Neighbourhood;
import world.rules.Rules;

import java.util.HashMap;

public class WireMap {
    private HashMap<Position, Cell> wireMap;
    private final Neighbourhood neighbourhood;
    private final Rules rules;

    public WireMap(WorldDimensions worldDimensions, Rules rules) {
        this.wireMap = new HashMap<>();
        this.neighbourhood = new Moor(wireMap, worldDimensions);
        this.rules = rules;
    }

    public void putEntry(int x, int y, Cell cell) {
        wireMap.put(new Position(x, y), cell);
    }

    public void iterate() {
        HashMap<Position, Cell> map = new HashMap<>();
        wireMap.forEach((position, cell) -> {
            if (cell instanceof Head) {
                map.put(position, CellContainer.tail);
            } else if (cell instanceof Tail) {
                map.put(position, CellContainer.wire);
            } else {
                map.put(position, rules.update(neighbourhood.iterateNeighbourhood(position)));
            }
        });
        wireMap = map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Wire:\n");
        wireMap.forEach((position, cell) -> {
            sb.append(position.getX() + " " + position.getY() + " " + cell).append('\n');
        });
        return sb.toString();
    }

    public HashMap<Position, Cell> getWireMap() {
        return wireMap;
    }
}
