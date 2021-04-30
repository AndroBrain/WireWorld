package world;

import org.junit.Before;
import org.junit.Test;
import world.neighbourhood.Moor;
import world.rules.WireRules;

import static org.junit.Assert.assertEquals;

public class IterationsTest {
    Matrix cellMat;
    WireMap wireMap;

    @Before
    public void createWire() {
        cellMat = new Matrix(3, 3);
        wireMap = new WireMap(cellMat, new Moor(), new WireRules());
        WireBuilder wireBuilder = new WireBuilder(cellMat, wireMap);
        for (int i = 0; i < cellMat.getRows(); i++)
            for (int j = 0; j < cellMat.getColumns(); j++)
                wireBuilder.putEmpty(i, j);
        wireBuilder.putWire(1, 1);
        wireBuilder.putHead(1, 0);
    }


    @Test
    public void simpleIterationTest() {
        Matrix simpleMatrix = new Matrix(3, 3);
        WireMap simpleWireMap = new WireMap(simpleMatrix, new Moor(), new WireRules());
        WireBuilder simpleWireBuilder = new WireBuilder(simpleMatrix, simpleWireMap);
        for (int i = 0; i < simpleMatrix.getRows(); i++)
            for (int j = 0; j < simpleMatrix.getColumns(); j++)
                simpleWireBuilder.putEmpty(i, j);
        simpleWireBuilder.putHead(1, 1);
        simpleWireBuilder.putTail(1, 0);

        wireMap.iterate();
        assertEquals(simpleMatrix, cellMat);
    }
}