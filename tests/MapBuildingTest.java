import org.junit.Before;
import org.junit.Test;
import world.Matrix;
import world.Pos;
import world.WireBuilder;
import world.WireMap;
import world.cells.Cell;
import world.cells.Head;
import world.cells.Tail;
import world.cells.Wire;
import world.neighbourhood.Moor;
import world.rules.WireRules;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class MapBuildingTest {
    Matrix cellMat;
    WireMap wireMap;

    @Before
    public void createWire() {
        cellMat = new Matrix(2, 2);

        wireMap = new WireMap(cellMat, new Moor(), new WireRules());
        WireBuilder wireBuilder = new WireBuilder(cellMat, wireMap);
        wireBuilder.putWire(0, 0);
        wireBuilder.putEmpty(0, 1);
        wireBuilder.putHead(1, 0);
        wireBuilder.putTail(1, 1);
    }

    @Test
    public void createMap() {
        HashMap<Pos, Cell> actualMap = wireMap.getWireMap();

        assertTrue(actualMap.get(new Pos(0, 0)) instanceof Wire);
        assertTrue(actualMap.get(new Pos(1, 0)) instanceof Head);
        assertTrue(actualMap.get(new Pos(1, 1)) instanceof Tail);
    }

}
