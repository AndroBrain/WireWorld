package gui;

import files_io.Input;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import world.WireMapManager;
import world.WorldDimensions;

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

    @FXML
    private Button startButton;

    @FXML
    private Button generateButton;

    @FXML
    private TextField plainGridWidth;

    @FXML
    private TextField plainGridHeight;

    @FXML
    private TextField xSize;

    @FXML
    private TextField ySize;

    @FXML
    private TextField iterationsTextField;

    @FXML
    private TextField delayTextField;

    @FXML
    private GridPane gridPane;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void openFile(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) rootPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("Path: " + file.getAbsolutePath());
        }
    }

    @FXML
    void plainGridGenerate(ActionEvent event) {
        plainGridW = getInput(plainGridWidth.getText());
        plainGridH = getInput(plainGridHeight.getText());
//        Matrix matrix = new Matrix(plainGridW, plainGridH);
        //    pixelWidth = gridPane.getWidth() / matrix.getRows();
        //   pixelHeight = gridPane.getHeight() / matrix.getColumns();
        //      makeGridPane(matrix);
//        startButton.setDisable(false);
//        generateButtonPressed = 1;
    }

    @FXML
    void startWireworld(ActionEvent event) {

        xInput = getInput(xSize.getText());
        yInput = getInput(ySize.getText());
        iterationsInput = getInput(iterationsTextField.getText());
        delayInput = getInput(delayTextField.getText());

        WireMapManager wireMapManager = Input.load(file.getAbsolutePath());

        if (wireMapManager != null) {
            WorldDimensions worldDimensions = wireMapManager.getWorldDimensions();

            pixelWidth = gridPane.getWidth() / worldDimensions.getRows();
            pixelHeight = gridPane.getHeight() / worldDimensions.getColumns();

            int rows = worldDimensions.getRows();
            int columns = worldDimensions.getColumns();

            for (int x = 0; x < rows; x++)
                for (int y = 0; y < columns; y++) {
                    addCanvasWithStroke(pixelWidth, pixelHeight, Color.BLACK, Color.RED);
                    gridPane.add(canvas, x, y, 1, 1);
                }

            wireMapManager.getWireMap().forEach((position, cell) -> {
                int x = position.getX();
                int y = position.getY();
                addCanvasWithStroke(pixelWidth, pixelHeight, cell.getColor(), Color.RED);
                gridPane.add(canvas, x, y, 1, 1);
            });
        }

        startButtonPressed = 1;
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
        return iterationsInput;
    }

    public int getDelayInput() {
        return delayInput;
    }

    public File getFile() {
        return file;
    }
}
