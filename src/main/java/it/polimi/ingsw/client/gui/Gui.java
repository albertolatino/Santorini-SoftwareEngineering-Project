package it.polimi.ingsw.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("/scenes/welcome.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/scenes/start-player.fxml"));

            Scene scene = new Scene(root, 600, 400);

            stage.setTitle("Santorini");
            stage.setScene(scene);

            //stage.setResizable(true);
            stage.setMinWidth(500);
            stage.setMinHeight(330);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}