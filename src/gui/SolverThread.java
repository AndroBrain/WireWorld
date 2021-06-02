package gui;

import files_io.Output;
import javafx.application.Platform;
import javafx.scene.control.Button;
import world.WireMapManager;

public class SolverThread extends Thread {
    private final Button startButton;
    private final int iterations;
    private final int delay;
    private final WireMapManager wireMapManager;
    private final GridDrawer gridDrawer;

    public SolverThread(Button startButton, int iterations, int delay, WireMapManager wireMapManager, GridDrawer gridDrawer) {
        this.startButton = startButton;
        this.iterations = iterations;
        this.delay = delay;
        this.wireMapManager = wireMapManager;
        this.gridDrawer = gridDrawer;
    }

    @Override
    public void run() {
        startButton.setDisable(true);
        for (int i = 0; i < iterations; i++) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Output output = new Output(wireMapManager.getWireMap(), wireMapManager.getWorldDimensions());
                output.save();
                startButton.setDisable(false);
                break;
            }
            wireMapManager.iterate();
            Platform.runLater(() -> gridDrawer.drawWire(wireMapManager));
        }
        Output output = new Output(wireMapManager.getWireMap(), wireMapManager.getWorldDimensions());
        output.save();
        startButton.setDisable(false);
    }
}
