package world.rules;

import world.Matrix;
import world.cell.Cell;
import world.cell.Head;
import world.cell.Tail;
import world.cell.Wire;

import java.util.HashMap;

public class Rules {
    private final HashMap<Pos, Cell> wireMap;
    private final Matrix cellMat;

    public Rules(Matrix cellMat) {
        this.cellMat = cellMat;
        this.wireMap = new HashMap<>();
        createWireMap();
    }

    private void createWireMap() {
        for (int x = 0; x < cellMat.getWidth(); x++)
            for (int y = 0; y < cellMat.getHeight(); y++) {
                Cell cell = cellMat.getEntry(x, y);
                if (cell instanceof Wire || cell instanceof Head || cell instanceof Tail) {
                    wireMap.put(new Pos(x, y), cell);
                }
            }
    }

    public HashMap<Pos, Cell> getWireMap() {
        return wireMap;
    }
}
