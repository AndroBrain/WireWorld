package files_io;

import world.build.WorldDimensions;
import world.cells.Cell;
import world.other.Position;

import java.io.*;
import java.util.HashMap;

import static world.other.CellConstants.HEAD;
import static world.other.CellConstants.TAIL;

public class Output {

    private final HashMap<Position, Cell> wireMap;
    private final WorldDimensions worldDimensions;

    public Output(HashMap<Position, Cell> wireMap, WorldDimensions worldDimensions) {
        this.wireMap = wireMap;
        this.worldDimensions = worldDimensions;
    }

    private static File lastIteration;

    private static void createFile() {
        try {
            lastIteration = new File("lastIteration.txt");
            if (lastIteration.createNewFile()) {
                System.out.println("File created: " + lastIteration.getName());
            } else {
                lastIteration.delete();
                lastIteration.createNewFile();
                System.out.println("Old file deleted, new has been created: " + lastIteration.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        createFile();

        StringBuilder sb = new StringBuilder();

        sb.append(worldDimensions.getRows()).append(' ').append(worldDimensions.getColumns()).append('\n');

        wireMap.forEach((position, cell) -> {
            if (cell == HEAD) {
                int xi = position.getX();
                int yi = position.getY();
                String x = Integer.toString(xi);
                String y = Integer.toString(yi);

                sb.append("ElectronHead ").append(x).append(" ").append(y).append("\n");
            } else if (cell == TAIL) {
                int xi = position.getX();
                int yi = position.getY();
                String x = Integer.toString(xi);
                String y = Integer.toString(yi);

                sb.append("ElectronTail ").append(x).append(" ").append(y).append("\n");
            }
        });

        while (!Input.wireCells.isEmpty())
            sb.append(Input.wireCells.pop()).append('\n');

        try (Writer output = new BufferedWriter(new FileWriter(lastIteration, true))) {
            output.append(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
