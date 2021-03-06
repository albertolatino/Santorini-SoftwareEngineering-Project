package it.polimi.ingsw.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;


/**
 * Manages the connection scene of the GUI.
 */
public class ConnectController {

    @FXML
    private TextField IPText;

    @FXML
    private Text error;

    @FXML
    private ImageView waitForGame;

    public ConnectController() {
    }

    @FXML
    private void connect(KeyEvent ke) {

        if (ke.getCode().equals(KeyCode.ENTER)) {

            error.setVisible(false);
            String IPAddress = IPText.getCharacters().toString();

            try {
                //give ip address to manager thread
                GuiManager.queue.put(IPAddress);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //if not the creator, display the loader
            waitForGame.setVisible(true);
        }
    }


    /**
     * Sets visible the error when trying to connect to the server of the game.
     */
    public void displayError() {
        error.setVisible(true);
    }

}
