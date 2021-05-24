package files_io;

import world.WireMapManager;
import world.build.WireBuilder;
import world.build.WorldDimensions;
import world.neighbourhood.Moor;
import world.rules.WireRules;

import java.io.BufferedReader;
import java.io.FileReader;

public class Input {

    public static String[] outForDiode = new String[100000];
    public static String[] outForElectronGenerator = new String[100000];
    public static String[] outForAndNot = new String[100000];
    public static String[] outForOr = new String[100000];
    public static String[] outForWire = new String[100000];
    public static String[] outForWire1 = new String[100000];
    public static String[] outForWire2 = new String[100000];
    public static String[] outForFuseForN5 = new String[100000];

    public static int l = 0;
    public static int k = 0;
    public static int i = 0;
    public static int j = 0;
    public static int a = 0;
    public static int b = 0;
    public static int c = 0;
    public static int d = 0;


    public static String[] wireData;

    public static WireMapManager load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line != null) {
                String[] dimensions = line.split(" ");
                if (dimensions.length < 2)
                    return null;
                WorldDimensions worldDimensions = new WorldDimensions(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));

                WireMapManager wireMapManager = new WireMapManager(worldDimensions, new WireRules(), new Moor(worldDimensions));
                WireBuilder wireBuilder = new WireBuilder(wireMapManager);

                while ((line = br.readLine()) != null) {
                    if (line.isEmpty())
                        continue;
                    String[] wireData = line.split(" ");
                    String cellType = wireData[0];
                    int x = Integer.parseInt(wireData[1]);
                    int y = Integer.parseInt(wireData[2]);
                    switch (cellType) {
                        case "ElectronHead":
                            wireBuilder.putHead(x, y);
                            outForWire2[c] = "Wire " + x + " " + y;
                            c++;
                            break;
                        case "ElectronTail":
                            wireBuilder.putTail(x, y);
                            outForWire1[b] = "Wire " + x + " " + y;
                            b++;
                            break;
                        case "Wire":
                            wireBuilder.putWire(x, y);
                            outForWire[a] = line;
                            a++;
                            break;
                        case "Diode":
                            wireBuilder.putDiode(x, y, wireData[3].charAt(0));
                            outForDiode[i] = line;
                            i++;
                            break;
                        case "OrGate":
                            wireBuilder.putOrGate(x, y, wireData[3].charAt(0));
                            outForOr[j] = line;
                            j++;
                            break;
                        case "ElectronGenerator":
                            wireBuilder.putElectronGenerator(x, y, wireData[3], Integer.parseInt(wireData[4]), Integer.parseInt(wireData[5]));
                            outForElectronGenerator[k] = line;
                            k++;
                            break;
                        case "AndNotGate":
                            wireBuilder.putAndNot(x, y);
                            outForAndNot[l] = line;
                            l++;
                            break;
                        case "FuseForN5":
                            wireBuilder.putFuseForN5(x, y, wireData[3].charAt(0));
                            outForFuseForN5[d] = line;
                            d++;

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
