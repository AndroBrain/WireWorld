package gui;

import files_io.Input;
import files_io.Output;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import world.WireMapManager;
import world.build.WorldDimensions;

import java.io.File;
import java.io.IOException;

public class Controller {

    private double pixelWidth;
    private double pixelHeight;

    private WireMapManager wireMapManager;
    private Thread solverThread;

    private int max;
    private File file;

    @FXML
    private Button refreshButton;

    @FXML
    private Button startButton;

    @FXML
    private Button generateButton;

    @FXML
    private Label loadFileLabel;

    @FXML
    private TextField plainGridWidth;

    @FXML
    private TextField plainGridHeight;

    @FXML
    private TextField iterationsTextField;

    @FXML
    private TextField delayTextField;

    @FXML
    private GridPane gridPane;

    @FXML
    private AnchorPane rootPane;

    public void initialize() {
        iterationsTextField.textProperty().addListener(new NumericListener(iterationsTextField));
        delayTextField.textProperty().addListener(new NumericListener(iterationsTextField));
        refreshButton.setText("Refresh\nCan only make bigger");
        refreshButton.setOnAction((e) -> drawGridPane());
    }

    @FXML
    void openFile(ActionEvent event) {
        startButton.setDisable(false);
        loadFileLabel.setText("Loading WireWorld\n please wait");
        if (solverThread != null)
            if (!solverThread.isInterrupted())
                solverThread.interrupt();
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) rootPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            wireMapManager = Input.load(file.getAbsolutePath());
            if (wireMapManager == null)
                loadFileLabel.setText("ERROR\ncouldn't load file");
            else {
                loadFileLabel.setText("WireWorld file\nloaded successfully!");
                drawGridPane();
            }

        } else {
            loadFileLabel.setText("ERROR\ncouldn't load file");
        }
    }

    @FXML
    void plainGridGenerate() {

    }

    @FXML
    void startWireworld(ActionEvent event) {
        if (wireMapManager != null) {
            startButton.setDisable(true);
            int delay = getDelayInput(delayTextField.getText());
            int iterations = getIterationsInput(iterationsTextField.getText());
            solverThread = new Thread(() -> {
                running:
                while (true) {
                    for (int i = 0; i < iterations; i++) {
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            break running;
                        }
                        wireMapManager.iterate();
                        Platform.runLater(() -> drawWire(wireMapManager));
                    }
                    startButton.setDisable(false);
                    break;
                }
                Output output = new Output(file, wireMapManager.getWireMap(), max);
                try {
                    output.save(file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            solverThread.setDaemon(true);
            solverThread.start();
        } else {
            loadFileLabel.setText("Please load\nWireWorld file first");
        }
    }

    public void drawGridPane() {
//        Clear old gridpane
        if (gridPane.getChildren().size() > 0)
            gridPane.getChildren().retainAll(gridPane.getChildren().get(0));
        if (wireMapManager != null) {
            WorldDimensions worldDimensions = wireMapManager.getWorldDimensions();
            int rows = worldDimensions.getRows();
            int columns = worldDimensions.getColumns();

            ObservableList<ColumnConstraints> colConstraints = gridPane.getColumnConstraints();
            colConstraints.clear();
            max = Math.max(rows, columns);
            for (int col = 0; col < max; col++) {
                ColumnConstraints c = new ColumnConstraints();
                c.setHalignment(HPos.CENTER);
                c.setHgrow(Priority.ALWAYS);
                colConstraints.add(c);
            }

            ObservableList<RowConstraints> rowConstraints = gridPane.getRowConstraints();
            rowConstraints.clear();
            for (int row = 0; row < max; row++) {
                RowConstraints c = new RowConstraints();
                c.setValignment(VPos.CENTER);
                c.setVgrow(Priority.ALWAYS);
                rowConstraints.add(c);
            }

            pixelWidth = gridPane.getWidth() / max;
            pixelHeight = gridPane.getHeight() / max;

            for (int x = 0; x < max; x++)
                for (int y = 0; y < max; y++) {
                    Canvas canvas = createCanvasWithStroke(pixelWidth, pixelHeight, Color.BLACK);
                    gridPane.add(canvas, y, x, 1, 1);
                }
            drawWire(wireMapManager);
        }
    }

    private void drawWire(WireMapManager wireMapManager) {
        wireMapManager.getWireMap().forEach((position, cell) -> {
            int x = position.getX();
            int y = position.getY();
            Canvas canvas = createCanvasWithStroke(pixelWidth, pixelHeight, cell.getColor());
            gridPane.add(canvas, y, x, 1, 1);
        });
    }

    private Canvas createCanvasWithStroke(double pW, double pH, Color canvasColor) {
        Canvas canvas = new Canvas(pixelWidth, pixelHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(canvasColor);
        gc.fillRoundRect(0, 0, pW, pH, 0, 0); // w kolejności - odległość x od krawędzi canvasa ; y -- ; bok kwadratu ; -- ; zaokrąglenie ; -||-
        gc.setStroke(Color.RED);
        gc.strokeRoundRect(0, 0, pW, pH, 1, 1);
        return canvas;
    }

    public int getIterationsInput(String input) {
        return getInput(input, 40);
    }

    public int getDelayInput(String input) {
        return getInput(input, 100);
    }

    private int getInput(String input, int standard) {
        try {
            return Math.max(Integer.parseInt(input), standard);
        } catch (NumberFormatException e) {
            return standard;
        }
    }
}