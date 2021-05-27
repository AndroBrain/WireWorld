package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;


public class ShowScreen extends Application {
    public static Timer timer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Wireworld");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);

        timer = new Timer();
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> update(loader));
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> update(loader));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void update(FXMLLoader loader) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> ((Controller) loader.getController()).drawGridPane());
                timer.cancel();
            }
        };
        timer.cancel();
        timer = new Timer();
        timer.schedule(timerTask, 1000);
    }
}
