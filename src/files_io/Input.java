package files_io;

import world.WireMapManager;
import world.build.WireBuilder;
import world.build.WorldDimensions;
import world.neighbourhood.Moor;
import world.rules.WireRules;

import java.io.BufferedReader;
import java.io.FileReader;

public class Input {
    public static WireMapManager load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line != null) {
                String[] dimensions = line.split(" ");
                WorldDimensions worldDimensions = new WorldDimensions(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));

                WireMapManager wireMapManager = new WireMapManager(worldDimensions, new WireRules(), new Moor(worldDimensions));
                WireBuilder wireBuilder = new WireBuilder(wireMapManager);

                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    String[] wireData = line.split(" ");
                    String cellType = wireData[0];
                    int x = Integer.parseInt(wireData[1]);
                    int y = Integer.parseInt(wireData[2]);
                    switch (cellType) {
                        case "ElectronHead":
                            wireBuilder.putHead(x, y);
                            break;
                        case "ElectronTail":
                            wireBuilder.putTail(x, y);
                            break;
                        case "Wire":
                            wireBuilder.putWire(x, y);
                            break;
                        case "Diode":
                            wireBuilder.putDiode(x, y, wireData[3].charAt(0));
                            break;
                        case "OrGate":
                            wireBuilder.putOrGate(x, y, wireData[3].charAt(0));
                            break;
                        case "ElectronGenerator":
                            wireBuilder.putElectronGenerator(x, y, wireData[3], Integer.parseInt(wireData[4]), Integer.parseInt(wireData[5]));
                            break;
                    }
                }
                return wireMapManager;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
