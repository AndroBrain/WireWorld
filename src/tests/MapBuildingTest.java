package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import world.Matrix;
import world.WireBuilder;
import world.cell.Cell;
import world.cell.Head;
import world.cell.Tail;
import world.cell.Wire;
import world.rules.Pos;
import world.rules.Rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapBuildingTest {
    Matrix cellMat;

    @Before
    public void createWire() {
        cellMat = new Matrix(2, 2);
        WireBuilder wireBuilder = new WireBuilder(cellMat);
        wireBuilder.putWire(0, 0);
        wireBuilder.putEmpty(0, 1);
        wireBuilder.putHead(1, 0);
        wireBuilder.putTail(1, 1);
    }

    @Test
    public void createMap() {
        Rules rules = new Rules(cellMat);

        HashMap<Pos, Cell> actualMap = rules.getWireMap();

        assertTrue(actualMap.get(new Pos(0, 0)) instanceof Wire);
        assertTrue(actualMap.get(new Pos(1, 0)) instanceof Head);
        assertTrue(actualMap.get(new Pos(1, 1)) instanceof Tail);
    }

}
