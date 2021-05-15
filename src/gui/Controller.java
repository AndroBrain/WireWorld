package gui;

import files_io.Input;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import world.WireMapManager;
import world.build.WorldDimensions;

import java.io.File;

public class Controller {

    private int xInput;
    private int yInput;
    private int iterationsInput;
    private int delayInput;
    private int plainGridW;
    private int plainGridH;

    private int generateButtonPressed = 0;
    private int startButtonPressed = 0;

    private double pixelWidth;
    private double pixelHeight;

    private Canvas canvas;

    private File file;

    private WireMapManager wireMapManager;

    @FXML
    private Button startButton;

    @FXML
    private Button generateButton;

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
        iterationsTextField.textProperty().addListener(new MakeNumericListener(iterationsTextField));
        delayTextField.textProperty().addListener(new MakeNumericListener(iterationsTextField));
    }

    @FXML
    void openFile(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) rootPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("Path: " + file.getAbsolutePath());
            wireMapManager = Input.load(file.getAbsolutePath());
            drawGridPane();
        }
    }

    @FXML
    void plainGridGenerate(ActionEvent event) {
        plainGridW = getInput(plainGridWidth.getText());
        plainGridH = getInput(plainGridHeight.getText());
    }

    @FXML
    void startWireworld(ActionEvent event) {
        if (wireMapManager != null) {
            iterationsInput = getInput(iterationsTextField.getText());
            delayInput = getInput(delayTextField.getText());
            Thread solverThread = new Thread(() -> {
                for (int i = 0; i < getIterationsInput(); i++) {
                    try {
                        Thread.sleep(getDelayInput());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    wireMapManager.iterate();
                    Platform.runLater(() -> drawWire(wireMapManager));
                }
            });
            solverThread.setDaemon(true);
            solverThread.start();

            startButtonPressed = 1;
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
            int max = Math.max(rows, columns);
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
                    addCanvasWithStroke(pixelWidth, pixelHeight, Color.BLACK, Color.RED);
                    gridPane.add(canvas, y, x, 1, 1);
                }
            drawWire(wireMapManager);
        }
    }

    private void drawWire(WireMapManager wireMapManager) {
        wireMapManager.getWireMap().forEach((position, cell) -> {
            int x = position.getX();
            int y = position.getY();
            addCanvasWithStroke(pixelWidth, pixelHeight, cell.getColor(), Color.RED);
            gridPane.add(canvas, y, x, 1, 1);
        });
    }

    private void addCanvasWithStroke(double pW, double pH, Color canvasColor, Color strokeColor) {
        canvas = new Canvas(pixelWidth, pixelHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(canvasColor);
        gc.fillRoundRect(0, 0, pW, pH, 0, 0); // w kolejności - odległość x od krawędzi canvasa ; y -- ; bok kwadratu ; -- ; zaokrąglenie ; -||-
        gc.setStroke(strokeColor);
        gc.strokeRoundRect(0, 0, pW, pH, 0, 0);
    }

    private int getInput(String text) {
        return text.length() > 0 ? Integer.parseInt(text) : 10;
    }


    public int getxInput() {
        return xInput;
    }

    public int getyInput() {
        return yInput;
    }

    public int getIterationsInput() {
        return iterationsInput > 0 ? iterationsInput : 10;
    }

    public int getDelayInput() {
        return Math.max(delayInput, 100);
    }

    public File getFile() {
        return file;
    }
}
