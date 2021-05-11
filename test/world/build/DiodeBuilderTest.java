package world.build;

import files_io.Input;
import org.junit.Before;
import org.junit.Test;
import world.WireMapManager;
import world.cells.Cell;
import world.neighbourhood.Moor;
import world.other.CellConstants;
import world.rules.WireRules;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DiodeBuilderTest {
    private static final String path = "test/world/build/";
    private static final Cell WIRE = CellConstants.WIRE;

    private WireMapManager wireMapManagerExpected;

    @Before
    public void before() {
        WorldDimensions worldDimensions = new WorldDimensions(3, 3);
        wireMapManagerExpected = new WireMapManager(worldDimensions, new WireRules(), new Moor(worldDimensions));
    }

    @Test
    public void buildNorth() {
        WireMapManager wireMapManager = Input.load(path + "northDiodeTest.txt");

        assertNotNull(wireMapManager);

        wireMapManagerExpected.putEntry(1, 0, WIRE);
        wireMapManagerExpected.putEntry(0, 0, WIRE);
        wireMapManagerExpected.putEntry(0, 1, WIRE);
        wireMapManagerExpected.putEntry(0, 2, WIRE);
        wireMapManagerExpected.putEntry(1, 2, WIRE);
        AtomicInteger counter = new AtomicInteger();

        wireMapManager.getWireMap().forEach(((position, cell) -> {
            if (wireMapManagerExpected.getWireMap().containsKey(position))
                counter.getAndIncrement();
        }));
        assertEquals(counter.get(), 5);
        assertEquals(wireMapManager.getWireMap().size(), 5);
    }

    @Test
    public void buildSouth() {
        WireMapManager wireMapManager = Input.load(path + "southDiodeTest.txt");

        assertNotNull(wireMapManager);

        wireMapManagerExpected.putEntry(1, 0, WIRE);
        wireMapManagerExpected.putEntry(2, 0, WIRE);
        wireMapManagerExpected.putEntry(2, 1, WIRE);
        wireMapManagerExpected.putEntry(2, 2, WIRE);
        wireMapManagerExpected.putEntry(1, 2, WIRE);
        AtomicInteger counter = new AtomicInteger();

        wireMapManager.getWireMap().forEach(((position, cell) -> {
            if (wireMapManagerExpected.getWireMap().containsKey(position))
                counter.getAndIncrement();
        }));
        assertEquals(counter.get(), 5);
        assertEquals(wireMapManager.getWireMap().size(), 5);
    }

    @Test
    public void buildWest() {
        WireMapManager wireMapManager = Input.load(path + "westDiodeTest.txt");

        assertNotNull(wireMapManager);

        wireMapManagerExpected.putEntry(0, 1, WIRE);
        wireMapManagerExpected.putEntry(0, 2, WIRE);
        wireMapManagerExpected.putEntry(1, 2, WIRE);
        wireMapManagerExpected.putEntry(2, 2, WIRE);
        wireMapManagerExpected.putEntry(2, 1, WIRE);
        AtomicInteger counter = new AtomicInteger();

        wireMapManager.getWireMap().forEach(((position, cell) -> {
            if (wireMapManagerExpected.getWireMap().containsKey(position))
                counter.getAndIncrement();
        }));
        assertEquals(counter.get(), 5);
        assertEquals(wireMapManager.getWireMap().size(), 5);
    }

    @Test
    public void buildEast() {
        WireMapManager wireMapManager = Input.load(path + "eastDiodeTest.txt");

        assertNotNull(wireMapManager);

        wireMapManagerExpected.putEntry(2, 1, WIRE);
        wireMapManagerExpected.putEntry(2, 0, WIRE);
        wireMapManagerExpected.putEntry(1, 0, WIRE);
        wireMapManagerExpected.putEntry(0, 0, WIRE);
        wireMapManagerExpected.putEntry(0, 1, WIRE);
        AtomicInteger counter = new AtomicInteger();

        wireMapManager.getWireMap().forEach(((position, cell) -> {
            if (wireMapManagerExpected.getWireMap().containsKey(position))
                counter.getAndIncrement();
        }));
        assertEquals(counter.get(), 5);
        assertEquals(wireMapManager.getWireMap().size(), 5);
    }
}