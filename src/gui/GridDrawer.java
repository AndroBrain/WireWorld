package gui;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import world.WireMapManager;
import world.build.WorldDimensions;

public class GridDrawer {
    private final GridPane gridPane;
    private double canvasWidth;
    private double canvasHeight;

    public GridDrawer(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void drawGridPane(WireMapManager wireMapManager) {
        clearGrid();

        if (wireMapManager != null) {
            WorldDimensions worldDimensions = wireMapManager.getWorldDimensions();
            int rows = worldDimensions.getRows();
            int columns = worldDimensions.getColumns();

            resizeAutomatically(rows, columns);

            canvasWidth = gridPane.getWidth() / columns;
            canvasHeight = gridPane.getHeight() / rows;

            for (int x = 0; x < rows; x++)
                for (int y = 0; y < columns; y++) {
                    Canvas canvas = createCanvasWithStroke(Color.BLACK);
                    gridPane.add(canvas, y, x, 1, 1);
                }
            drawWire(wireMapManager);
        }
    }

    public void drawWire(WireMapManager wireMapManager) {
        wireMapManager.getWireMap().forEach((position, cell) -> {
            int x = position.getX();
            int y = position.getY();
            Canvas canvas = createCanvasWithStroke(cell.getColor());
            gridPane.add(canvas, y, x, 1, 1);
        });
    }

    private void clearGrid() {
        if (gridPane.getChildren().size() > 0)
            gridPane.getChildren().retainAll(gridPane.getChildren().get(0));
    }

    private void resizeAutomatically(int rows, int columns) {
        ObservableList<ColumnConstraints> colConstraints = gridPane.getColumnConstraints();
        colConstraints.clear();
        for (int col = 0; col < rows; col++) {
            ColumnConstraints c = new ColumnConstraints();
            c.setHalignment(HPos.CENTER);
            c.setHgrow(Priority.ALWAYS);
            colConstraints.add(c);
        }

        ObservableList<RowConstraints> rowConstraints = gridPane.getRowConstraints();
        rowConstraints.clear();
        for (int row = 0; row < columns; row++) {
            RowConstraints c = new RowConstraints();
            c.setValignment(VPos.CENTER);
            c.setVgrow(Priority.ALWAYS);
            rowConstraints.add(c);
        }
    }

    private Canvas createCanvasWithStroke(Color canvasColor) {
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(canvasColor);
        gc.fillRoundRect(0, 0, canvasWidth, canvasHeight, 0, 0); // w kolejności - odległość x od krawędzi canvasa ; y -- ; bok kwadratu ; -- ; zaokrąglenie ; -||-
        gc.setStroke(Color.color(0.5, 0.1, 0.3));
        gc.strokeRoundRect(0, 0, canvasWidth, canvasHeight, 1, 1);
        return canvas;
    }
}