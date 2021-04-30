package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import world.Matrix;
import world.cells.CellContainer;
import world.cells.Head;
import world.cells.Tail;
import world.cells.Wire;

public class Controller {

    private int xInput;
    private int yInput;
    private int iterationsInput;
    private int delayInput;

    private String xS;
    private String yS;
    private String iterationsInfo;
    private String delayInfo;

    private double pixelWidth;
    private double pixelHeight;

    private Canvas canvas;

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
    void startWireworld(ActionEvent event) {
        xInput = getInput(xSize.getText());
        yInput = getInput(ySize.getText());
        iterationsInput = getInput(iterationsTextField.getText());
        delayInput = getInput(delayTextField.getText());
        Matrix matrix = new Matrix(5, 5);

        pixelWidth = gridPane.getWidth() / matrix.getColumns();
        pixelHeight = gridPane.getHeight() / matrix.getRows();

        matrix.setEntry(2, 2, CellContainer.wire);

        makeGridPane(matrix);
    }

    public void addGreenCanvas(double pW, double pH) {
        canvas = new Canvas(pW, pH);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(0, 0, pW, pH, 0, 0); // w kolejności - odległość x od krawędzi canvasa ; y -- ; bok kwadratu ; -- ; zaokrąglenie ; -||-
    }

    void makeGridPane(Matrix matrix) {
        for (int x = 0; x < matrix.getRows(); x++) {
            for (int y = 0; y < matrix.getColumns(); y++) {
                if (matrix.getEntry(x, y) instanceof Wire) {
                    addGreenCanvas(pixelWidth, pixelHeight);
                    gridPane.add(canvas, x, y);
                } else if (matrix.getEntry(x, y) instanceof Head) {
                    gridPane.add(new Button(), x, y);
                } else if (matrix.getEntry(x, y) instanceof Tail) {
                    gridPane.add(new Button(), x, y);
                }
            }
        }
    }

    private int getInput(String text) {
        return text.length() > 0 ? Integer.parseInt(text) : 10;
    }

    public void putCanvasInGridCell() {

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
}
