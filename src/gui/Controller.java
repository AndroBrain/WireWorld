package gui;

import world.cells.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Controller {

    private int xInput;
    private int yInput;
    private int iterationsInput;
    private int delayInput;

    private String xS;
    private String yS;
    private String iterationsInfo;
    private String delayInfo;

    private Canvas canvas;


    public void addGreenCanvas(){
        canvas = new Canvas(30, 30);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(0, 0, 30, 30, 0, 0); // w kolejności - odległość x od krawędzi canvasa ; y -||- ; bok kwadratu ; -||- ; zaokrąglenie ; -||-

    }

    void makeGridPane(Cell[][] cellMap, int rows, int columns){
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
                if(cellMap[x][y] instanceof Wire){
                    addGreenCanvas();
                    gridPane.add(canvas, 1, 1);
//                    gridPane.add(new Button(), x, y);
                }else if(cellMap[x][y] instanceof Head){
                    gridPane.add(new Button(), x, y);
                }else if(cellMap[x][y] instanceof Tail){
                    gridPane.add(new Button(), x, y);
                }
            }
        }

    }


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
        System.out.println("Super");
        if(xSize != null) {
            xS = xSize.getText();
            xInput = Integer.parseInt(xS);
        } else {
            xInput = 10;
        }
        if(ySize != null) {
            yS = ySize.getText();
            yInput = Integer.parseInt(yS);
        } else {
            yInput = 10;
        }
        if(iterationsTextField != null){
            iterationsInfo = iterationsTextField.getText();
            iterationsInput = Integer.parseInt(iterationsInfo);
        } else {
            iterationsInput = 10;
        }
        if(delayTextField != null) {
            delayInfo = delayTextField.getText();
            delayInput = Integer.parseInt(delayInfo);
            System.out.println(xInput + " " + yInput + " " + iterationsInfo + " " + delayInfo);
        } else {
            delayInput = 10;
        }
        addGreenCanvas();
        gridPane.add(canvas, 1, 1);
        //makeGridPane();
//        addGreenCanvas();
    }

    public void putCanvasInGridCell(){

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
