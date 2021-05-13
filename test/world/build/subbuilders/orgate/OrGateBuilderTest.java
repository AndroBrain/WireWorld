package world.build.subbuilders.orgate;

import files_io.Input;
import org.junit.Before;
import org.junit.Test;
import world.WireMapManager;
import world.build.WorldDimensions;
import world.neighbourhood.Moor;
import world.rules.WireRules;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static world.other.CellConstants.WIRE;

public class OrGateBuilderTest {
    private static final String path = "test/world/build/subbuilders/orgate/";
    private WireMapManager wireMapManagerExpected;

    @Before
    public void before() {
        WorldDimensions worldDimensions = new WorldDimensions(5, 5);
        wireMapManagerExpected = new WireMapManager(worldDimensions, new WireRules(), new Moor(worldDimensions));
    }

    @Test
    public void buildNorth() {
        WireMapManager wireMapManager = Input.load(path + "northOrGateTest.txt");

        assertNotNull(wireMapManager);
        wireMapManagerExpected.putEntry(0, 1, WIRE);
        wireMapManagerExpected.putEntry(0, 2, WIRE);
        wireMapManagerExpected.putEntry(0, 3, WIRE);
        wireMapManagerExpected.putEntry(1, 0, WIRE);
        wireMapManagerExpected.putEntry(1, 2, WIRE);
        wireMapManagerExpected.putEntry(1, 4, WIRE);
        wireMapManagerExpected.putEntry(2, 0, WIRE);
        wireMapManagerExpected.putEntry(2, 4, WIRE);

        AtomicInteger counter = new AtomicInteger();

        wireMapManager.getWireMap().forEach(((position, cell) -> {
            if (wireMapManagerExpected.getWireMap().containsKey(position))
                counter.getAndIncrement();
        }));
        assertEquals(8, counter.get());
        assertEquals(8, wireMapManager.getWireMap().size());
    }

    @Test
    public void buildSouth() {
        WireMapManager wireMapManager = Input.load(path + "southOrGateTest.txt");

        assertNotNull(wireMapManager);
        wireMapManagerExpected.putEntry(4, 1, WIRE);
        wireMapManagerExpected.putEntry(4, 2, WIRE);
        wireMapManagerExpected.putEntry(4, 3, WIRE);
        wireMapManagerExpected.putEntry(3, 0, WIRE);
        wireMapManagerExpected.putEntry(3, 2, WIRE);
        wireMapManagerExpected.putEntry(3, 4, WIRE);
        wireMapManagerExpected.putEntry(2, 0, WIRE);
        wireMapManagerExpected.putEntry(2, 4, WIRE);

        AtomicInteger counter = new AtomicInteger();

        wireMapManager.getWireMap().forEach(((position, cell) -> {
            if (wireMapManagerExpected.getWireMap().containsKey(position))
                counter.getAndIncrement();
        }));
        assertEquals(8, counter.get());
        assertEquals(8, wireMapManager.getWireMap().size());
    }

    @Test
    public void buildEast() {
        WireMapManager wireMapManager = Input.load(path + "eastOrGateTest.txt");

        assertNotNull(wireMapManager);
        wireMapManagerExpected.putEntry(1, 4, WIRE);
        wireMapManagerExpected.putEntry(2, 4, WIRE);
        wireMapManagerExpected.putEntry(3, 4, WIRE);
        wireMapManagerExpected.putEntry(0, 3, WIRE);
        wireMapManagerExpected.putEntry(2, 3, WIRE);
        wireMapManagerExpected.putEntry(4, 3, WIRE);
        wireMapManagerExpected.putEntry(0, 2, WIRE);
        wireMapManagerExpected.putEntry(4, 2, WIRE);

        AtomicInteger counter = new AtomicInteger();

        wireMapManager.getWireMap().forEach(((position, cell) -> {
            if (wireMapManagerExpected.getWireMap().containsKey(position))
                counter.getAndIncrement();
        }));
        assertEquals(8, counter.get());
        assertEquals(8, wireMapManager.getWireMap().size());
    }

    @Test
    public void buildWest() {
        WireMapManager wireMapManager = Input.load(path + "westOrGateTest.txt");

        assertNotNull(wireMapManager);
        wireMapManagerExpected.putEntry(1, 0, WIRE);
        wireMapManagerExpected.putEntry(2, 0, WIRE);
        wireMapManagerExpected.putEntry(3, 0, WIRE);
        wireMapManagerExpected.putEntry(0, 1, WIRE);
        wireMapManagerExpected.putEntry(2, 1, WIRE);
        wireMapManagerExpected.putEntry(4, 1, WIRE);
        wireMapManagerExpected.putEntry(0, 2, WIRE);
        wireMapManagerExpected.putEntry(4, 2, WIRE);

        AtomicInteger counter = new AtomicInteger();

        wireMapManager.getWireMap().forEach(((position, cell) -> {
            if (wireMapManagerExpected.getWireMap().containsKey(position))
                counter.getAndIncrement();
        }));
        assertEquals(8, counter.get());
        assertEquals(8, wireMapManager.getWireMap().size());
    }
}