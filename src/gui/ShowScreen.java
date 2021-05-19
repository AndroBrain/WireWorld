package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ShowScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Wireworld");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
