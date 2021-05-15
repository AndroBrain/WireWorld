package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ShowScreen extends Application {

    int counter = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Wireworld");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
                    counter++;
                    if (counter >= 60) {
                        ((Controller) loader.getController()).drawGridPane();
                        counter = 0;
                    }
                }
        );
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> {
                    counter++;
                    if (counter >= 60) {
                        ((Controller) loader.getController()).drawGridPane();
                        counter = 0;
                    }
                }
        );

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
