package world.build;

import world.WireMapManager;
import world.build.subbuilders.AndNotGateBuilder;
import world.build.subbuilders.DiodeBuilder;
import world.build.subbuilders.ElectronGeneratorBuilder;
import world.build.subbuilders.OrGateBuilder;
import world.cells.Cell;
import world.other.CellConstants;
import world.other.Position;

import java.util.HashMap;

import static world.other.CellConstants.HEAD;
import static world.other.CellConstants.TAIL;

public class WireBuilder {
    private final HashMap<Position, Cell> wireMap;
    private final WorldDimensions worldDimensions;

    public WireBuilder(WireMapManager wireMapManager) {
        this.wireMap = wireMapManager.getWireMap();
        worldDimensions = wireMapManager.getWorldDimensions();
    }

    public void putWire(int x, int y) {
        putEntry(x, y, CellConstants.WIRE);
    }

    public void putHead(int x, int y) {
        putEntry(x, y, CellConstants.HEAD);
    }

    public void putTail(int x, int y) {
        putEntry(x, y, CellConstants.TAIL);
    }

    public void putDiode(int x, int y, char direction) {
        DiodeBuilder.build(this, x, y, direction);
    }

    public void putOrGate(int x, int y, char direction) {
        OrGateBuilder.build(this, x, y, direction);
    }

    public void putElectronGenerator(int x, int y, String direction, int width, int height) {
        ElectronGeneratorBuilder.build(this, x, y, direction, width, height);
    }

    public void putAndNot(int x, int y) {
        AndNotGateBuilder.build(this, x, y);
    }

    private void putEntry(int x, int y, Cell cell) {
        if (x >= 0 && x < worldDimensions.getRows() && y >= 0 && y < worldDimensions.getColumns())
            if (!wireMap.containsKey(new Position(x, y)))
                wireMap.put(new Position(x, y), cell);
            else if (cell == HEAD || cell == TAIL)
                wireMap.replace(new Position(x, y), cell);
    }
}
