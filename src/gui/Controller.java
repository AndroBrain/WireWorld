package gui;

import files_io.Input;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import world.WireMapManager;

import java.io.File;

public class Controller {

    private WireMapManager wireMapManager;
    private SolverThread solverThread;

    private GridDrawer gridDrawer;

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
        gridDrawer = new GridDrawer(gridPane);

        iterationsTextField.setText("10");
        delayTextField.setText("100");
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
        File file = fileChooser.showOpenDialog(stage);
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

            solverThread = new SolverThread(startButton, iterations, delay, wireMapManager, gridDrawer);
            solverThread.setDaemon(true);
            solverThread.start();
        } else {
            loadFileLabel.setText("Please load\nWireWorld file first");
        }
    }

    public void drawGridPane() {
        gridDrawer.drawGridPane(wireMapManager);
    }

    public int getIterationsInput(String input) {
        return getInput(input, 1);
    }

    public int getDelayInput(String input) {
        return getInput(input, 1);
    }

    private int getInput(String input, int standard) {
        try {
            int intInput = Integer.parseInt(input);
            return intInput > 0 ? intInput : standard;
        } catch (NumberFormatException e) {
//            in case input is ""
            return standard;
        }
    }
}