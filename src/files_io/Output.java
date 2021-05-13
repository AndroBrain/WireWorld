package files_io;

import gui.Controller;

import world.WireMapManager;
import world.build.WireBuilder;
import world.build.WorldDimensions;
import world.cells.Cell;
import world.other.Position;



import java.io.*;
import java.util.HashMap;

import static world.other.CellConstants.*;

public class Output {

    private final HashMap<Position, Cell> wireMap;
    private final int max;
    private String fileAbsolutePath;

    public Output(HashMap<Position, Cell> wireMap, int max, String fileAbsolutePath) {
        this.wireMap = wireMap;
        this.max = max;
        this.fileAbsolutePath = fileAbsolutePath;
    }

    private static File lastIteration;

    private static void createFile(){
        try {
            lastIteration = new File("lastIteration.txt");
            if (lastIteration.createNewFile()) {
                System.out.println("File created: " + lastIteration.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        createFile();
        Writer output = new BufferedWriter(new FileWriter(lastIteration, true));

        output.append(String.valueOf(max)).append(" ").append(String.valueOf(max)).append("\n");

        wireMap.forEach((position, cell) -> {
            if(cell == HEAD){
                int xi = position.getX();
                int yi = position.getY();
                String x = Integer.toString(xi);
                String y = Integer.toString(yi);

                try {
                    output.append("ElectronHead ").append(x).append(" ").append(y).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(cell == TAIL){
                int xi = position.getX();
                int yi = position.getY();
                String x = Integer.toString(xi);
                String y = Integer.toString(yi);

                try {
                    output.append("ElectronTail ").append(x).append(" ").append(y).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(cell == WIRE){
                int xi = position.getX();
                int yi = position.getY();
                String x = Integer.toString(xi);
                String y = Integer.toString(yi);

                try {
                    output.append("Wire ").append(x).append(" ").append(y).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        if(Input.helpForDiode != null)
            for(int h = 0; h < Input.i; h++)
                output.append(Input.helpForDiode[h]);
        if(Input.helpForOr != null)
            for(int h = 0; h < Input.j; h++)
                output.append(Input.helpForOr[h]);
        if(Input.helpForElectronGenerator != null)
            for(int h = 0; h < Input.k; h++)
                output.append(Input.helpForElectronGenerator[h]);
        if(Input.helpForAndNot != null)
            for(int h = 0; h < Input.l; h++)
                output.append(Input.helpForAndNot[h]);


        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
