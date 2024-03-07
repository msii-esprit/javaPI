package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainJavaFX extends Application {

    public static final String CURRENCY = "$";
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/CreeCommande.fxml"));
        primaryStage.setTitle("Koujina");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
