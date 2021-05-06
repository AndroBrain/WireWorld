package gui;

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
import world.Matrix;
import world.cells.*;

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
        Matrix matrix = new Matrix(plainGridW, plainGridH);
        pixelWidth = gridPane.getWidth() / matrix.getRows();
        pixelHeight = gridPane.getHeight() / matrix.getColumns();
        makeGridPane(matrix);
//        startButton.setDisable(false);
//        generateButtonPressed = 1;
    }

    @FXML
    void startWireworld(ActionEvent event) {

        xInput = getInput(xSize.getText());
        yInput = getInput(ySize.getText());
        iterationsInput = getInput(iterationsTextField.getText());
        delayInput = getInput(delayTextField.getText());
        Matrix matrix = new Matrix(25, 25);

        pixelWidth = gridPane.getWidth() / matrix.getRows();
        pixelHeight = gridPane.getHeight() / matrix.getColumns();

        matrix.setEntry(1, 2, CellContainer.wire);
        matrix.setEntry(2, 2, CellContainer.wire);
        matrix.setEntry(3, 2, CellContainer.tail);
        matrix.setEntry(4, 2, CellContainer.head);
        matrix.setEntry(4, 3, CellContainer.wire);
        matrix.setEntry(4, 4, CellContainer.wire);
        matrix.setEntry(4, 5, CellContainer.wire);
        matrix.setEntry(4, 6, CellContainer.wire);
        matrix.setEntry(4, 7, CellContainer.wire);
        matrix.setEntry(4, 8, CellContainer.wire);
        matrix.setEntry(4, 9, CellContainer.wire);
        matrix.setEntry(4, 10, CellContainer.wire);
        matrix.setEntry(4, 11, CellContainer.wire);
        matrix.setEntry(4, 12, CellContainer.wire);
        matrix.setEntry(4, 13, CellContainer.wire);
        matrix.setEntry(5, 13, CellContainer.wire);
        matrix.setEntry(6, 13, CellContainer.wire);
        matrix.setEntry(7, 13, CellContainer.wire);
        matrix.setEntry(8, 13, CellContainer.wire);
        matrix.setEntry(9, 13, CellContainer.wire);
        matrix.setEntry(10, 13, CellContainer.wire);
        matrix.setEntry(11, 13, CellContainer.wire);
        matrix.setEntry(12, 13, CellContainer.wire);
        matrix.setEntry(13, 13, CellContainer.wire);
        matrix.setEntry(14, 13, CellContainer.wire);

        System.out.println(matrix);

        makeGridPane(matrix);
//        generateButton.setDisable(false);
        startButtonPressed = 1;
    }

    public void addGreenCanvas(double pW, double pH) {
        canvas = new Canvas(pW, pH);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(0, 0, pW, pH, 0, 0); // w kolejności - odległość x od krawędzi canvasa ; y -- ; bok kwadratu ; -- ; zaokrąglenie ; -||-
        gc.strokeRoundRect(0, 0, pW, pH, 10, 10);
    }

    public void addYellowCanvas(double pW, double pH) {
        canvas = new Canvas(pW, pH);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        gc.fillRoundRect(0, 0, pW, pH, 0, 0);
        gc.strokeRoundRect(0, 0, pW, pH, 10, 10);
    }

    public void addGrayCanvas(double pW, double pH) {
        canvas = new Canvas(pW, pH);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRoundRect(0, 0, pW, pH, 0, 0);
        gc.strokeRoundRect(0, 0, pW, pH, 10, 10);
    }

    public void addBlackCanvas(double pW, double pH) {
        canvas = new Canvas(pW, pH);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRoundRect(0, 0, pW, pH, 0, 0);
        gc.setStroke(Color.RED);
        gc.strokeRoundRect(0, 0, pW, pH, 10, 10);
    }

    void makeGridPane(Matrix matrix) {
        for (int x = 0; x < matrix.getRows(); x++) { // nie ma znaczenia kolejnosc iteracji x i y
            for (int y = 0; y < matrix.getColumns(); y++) {
                if (matrix.getEntry(x, y) instanceof Empty) {
                    addBlackCanvas(pixelWidth, pixelHeight);
                    gridPane.add(canvas, x, y, 1, 1);
                } else if (matrix.getEntry(x, y) instanceof Wire) {
                    addGreenCanvas(pixelWidth, pixelHeight);
                    gridPane.add(canvas, x, y, 1, 1);
                } else if (matrix.getEntry(x, y) instanceof Head) {
                    addYellowCanvas(pixelWidth, pixelHeight);
                    gridPane.add(canvas, x, y, 1, 1);
                } else if (matrix.getEntry(x, y) instanceof Tail) {
                    addGrayCanvas(pixelWidth, pixelHeight);
                    gridPane.add(canvas, x, y, 1, 1);
                }
            }
        }
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
