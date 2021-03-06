package it.polimi.ingsw.client.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


/**
 * Graphic User Interface chosen by the player to run the app.
 */
public class Gui extends Application {

    private static Stage stage;

    /**
     * Launches the GUI.
     */
    public static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Gui.stage = stage;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/scenes/welcome.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        //ImageCursor cursor = new ImageCursor(new Image("/labels/cursor_rotated.png"));
        //scene.setCursor(cursor);
        stage.setTitle("Santorini");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        try {
            GuiManager.queue.put("done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });


    }

    protected static Stage getStage() {
        return stage;
    }

}
