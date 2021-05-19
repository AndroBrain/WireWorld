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

    private File in;
    private final HashMap<Position, Cell> wireMap;
    private final int max;

    public Output(File in, HashMap<Position, Cell> wireMap, int max) {
        this.in = in;
        this.wireMap = wireMap;
        this.max = max;
    }

    private static File lastIteration;

    private static void createFile(String path){

        try {
            lastIteration = new File(path + "lastIteration.txt");
            if (lastIteration.createNewFile()) {
                System.out.println("File created: " + lastIteration.getName());
            } else {
                lastIteration.delete();
                lastIteration.createNewFile();
                System.out.println("Old file deleted, new has been created: " + lastIteration.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void copyFile(File input) throws IOException {
        FileInputStream in = new FileInputStream(input.getPath());
        FileOutputStream out = new FileOutputStream(input);

        try {
            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File Copied");
    }

    public void save(String path) throws IOException {
        createFile(path);

//        copyFile(in);

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
            else if(cell == TAIL) {
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
        });

        if(Input.outForWire != null)
            for(int h = 0; h < Input.a; h++) {
//                if(!(Input.wireData[0].equals("ElectronHead") && Input.wireData[1].equals(Input.outForWire[h].substring(5,5)) && Input.wireData[2].equals(Input.outForWire[h].substring(7,7))) && !(Input.wireData[0].equals("ElectronTail") && Input.wireData[1].equals(Input.outForWire[h].substring(5,5)) && Input.wireData[2].equals(Input.outForWire[h].substring(7,7))))
//                    System.out.println("sori ne ma");
//                else
                    output.append(Input.outForWire[h]).append("\n");
            }
        if(Input.outForWire1 != null)
            for(int h = 0; h < Input.b; h++) {
//                if (!(Input.wireData[0].equals("ElectronHead") && Input.wireData[1].equals(Input.outForWire1[h].substring(5, 5)) && Input.wireData[2].equals(Input.outForWire1[h].substring(7, 7))) && !(Input.wireData[0].equals("ElectronTail") && Input.wireData[1].equals(Input.outForWire1[h].substring(5, 5)) && Input.wireData[2].equals(Input.outForWire1[h].substring(7, 7))))
//                    System.out.println("sori ne ma");
//                else
                    output.append(Input.outForWire1[h]).append("\n");
            }
        if(Input.outForWire2 != null) {
            for (int h = 0; h < Input.c; h++)
//                if (!(Input.wireData[0].equals("ElectronHead") && Input.wireData[1].equals(Input.outForWire2[h].substring(5, 5)) && Input.wireData[2].equals(Input.outForWire2[h].substring(7, 7))) && !(Input.wireData[0].equals("ElectronTail") && Input.wireData[1].equals(Input.outForWire2[h].substring(5, 5)) && Input.wireData[2].equals(Input.outForWire2[h].substring(7, 7))))
//                    System.out.println("sori ne ma");
//                else
                    output.append(Input.outForWire2[h]).append("\n");
        }
        if(Input.outForDiode != null)
            for(int h = 0; h < Input.i; h++)
                output.append(Input.outForDiode[h]).append("\n");
        if(Input.outForOr != null)
            for(int h = 0; h < Input.j; h++)
                output.append(Input.outForOr[h]).append("\n");
        if(Input.outForElectronGenerator != null)
            for(int h = 0; h < Input.k; h++)
                output.append(Input.outForElectronGenerator[h]).append("\n");
        if(Input.outForAndNot != null)
            for(int h = 0; h < Input.l; h++)
                output.append(Input.outForAndNot[h]).append("\n");
        if(Input.outForFuseForN5 != null)
            for(int h = 0; h < Input.d; h++)
                output.append(Input.outForFuseForN5[h]).append("\n");



        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
