package it.polimi.ingsw.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;


/**
 * Manages the Nickname scene of the GUI.
 */
public class NicknameController {

    @FXML
    private TextField nicknameText;
    @FXML
    private Text errorNick;
    @FXML
    private Text waitingOther;


    public NicknameController() {
    }


    @FXML
    private void login(KeyEvent ke) {

        if (ke.getCode().equals(KeyCode.ENTER)) {

            String nickname = nicknameText.getCharacters().toString();

            try {
                //give nick to manager thread
                GuiManager.queue.put(nickname);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }


    /**
     * Displays on the scene the error: the inserted nickname was not available.
     * The player needs to try with a different one.
     */
    public void displayErrorNick() {
        errorNick.setVisible(true);
    }


    public void removeErrorNickFromScreen() {
        errorNick.setVisible(false);
    }


    public void displayWaitingOther() {
        waitingOther.setVisible(true);
    }


    /**
     * Sets invisible the text that states that someone else is choosing his nickname.
     */
    public void removeWaitingOtherFromScreen() {
        waitingOther.setVisible(false);
    }


    /**
     * The TextField to insert the nickname is enabled to enter it.
     */
    public void enableNicknameText () {
        nicknameText.setDisable(false);
    }


}
