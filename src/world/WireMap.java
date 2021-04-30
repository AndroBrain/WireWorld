package world;

import world.cells.*;
import world.neighbourhood.Neighbourhood;
import world.rules.Rules;

import java.util.HashMap;

public class WireMap {
    private HashMap<Position, Cell> wireMap;
    private final Matrix cellMat;
    private final Neighbourhood neighbourhood;
    private final Rules rules;

    public WireMap(Matrix cellMat, Neighbourhood neighbourhood, Rules rules) {
        this.cellMat = cellMat;
        this.wireMap = new HashMap<>();
        this.neighbourhood = neighbourhood;
        this.rules = rules;
    }

    public void putEntry(int x, int y, Cell cell) {
        wireMap.put(new Position(x, y), cell);
    }

    public void iterate() {
        HashMap<Position, Cell> map = new HashMap<>();
        wireMap.forEach((k, v) -> {
            if (v instanceof Head) {
                map.put(k, CellContainer.tail);
            } else if (v instanceof Tail) {
                map.put(k, CellContainer.wire);
            } else {
                map.put(k, rules.update(neighbourhood.iterateNeighbourhood(cellMat, k)));
            }
        });
        map.forEach((k, v) -> {
            int x = k.getX();
            int y = k.getY();
            cellMat.setEntry(x, y, v);
        });
        wireMap = map;
    }

    public HashMap<Position, Cell> getWireMap() {
        return wireMap;
    }
}
