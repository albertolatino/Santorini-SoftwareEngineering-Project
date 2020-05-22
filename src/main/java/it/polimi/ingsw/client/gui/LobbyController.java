package it.polimi.ingsw.client.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {

    @FXML
    private Label playerName1;
    @FXML
    private Label playerName2;
    @FXML
    private Label playerName3;
    @FXML
    private ImageView background1;
    @FXML
    private ImageView background2;
    @FXML
    private ImageView background3;
    @FXML
    private ImageView loader1;
    @FXML
    private ImageView loader2;
    @FXML
    private ImageView loader3;
    @FXML
    private ImageView banner3;
    @FXML
    private Button next;
    @FXML
    private Text loadingText1;
    @FXML
    private Text loadingText2;
    @FXML
    private Text loadingText3;
    @FXML
    private ImageView worker1;
    @FXML
    private ImageView worker2;
    @FXML
    private ImageView worker3;

    private int playersConnected;
    private volatile int numberOfPlayers;
    private volatile String nickname1;
    private volatile String nickname2;
    private volatile String nickname3;
    private volatile String color1;
    private volatile String color2;
    private volatile String color3;


    public LobbyController() {
        playersConnected = 0;
        nickname1 = null;
        nickname2 = null;
        nickname3 = null;
        color1 = null;
        color2 = null;
        color3 = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //disable next button until all players are connected
        next.setDisable(true);

        //hide label
        if (numberOfPlayers == 2) {
            playerName3.setVisible(false);
            banner3.setVisible(false);
            loader3.setVisible(false);
            loadingText3.setVisible(false);
        }

        //todo add this client nickname and color
        //call setPlayerInfo after color is set:
        //to do so save nickname locally in NicknameController
        //and do the same for color
        //showPlayer(nicknameController.getNickname(), colorController.getColor());

        //shows info already available when joining
        if (nickname1 != null) {
            showPlayer(nickname1, color1);
        } else if (nickname2 != null) {
            showPlayer(nickname2, color2);
        } else if (nickname3 != null) {
            showPlayer(nickname3, color3);
        }

    }

    public void showPlayer(String nickname, String color) {

        String path = "/board/workers/male_worker_front_" + color + ".png";
        Image workerImage = new Image(path);

        if (playerName1.getText().equals("")) {

            playerName1.setText(nickname);
            worker1.setImage(workerImage);
            worker1.setVisible(true);
            loader1.setVisible(false);
            loadingText1.setVisible(false);
            background1.setVisible(false);

        } else if (playerName2.getText().equals("")) {

            playerName2.setText(nickname);
            worker2.setImage(workerImage);
            worker2.setVisible(true);
            loader2.setVisible(false);
            loadingText2.setVisible(false);
            background2.setVisible(false);

        } else if (playerName3.getText().equals("")) {

            playerName3.setText(nickname);
            worker3.setImage(workerImage);
            worker3.setVisible(true);
            loader3.setVisible(false);
            loadingText3.setVisible(false);
            background3.setVisible(false);

        } else
            System.out.println("Error: cannot add more than three players");    //debugging

        playersConnected++;

        if (playersConnected == numberOfPlayers)
            next.setDisable(false);
    }

    @FXML
    private void next() {
        System.out.println("next");
    }

    public synchronized void setPlayerInfo(String nickname, String color) {

        if (nickname1 == null) {
            nickname1 = nickname;
            color1 = color;

        } else if (nickname2 == null) {
            nickname2 = nickname;
            color2 = color;
        } else if (nickname3 == null) {
            nickname3 = nickname;
            color3 = color;
        }

        //if client is in lobby there's at least 1 player connected (him)
        //RENDER
        if (playersConnected > 0)
            showPlayer(nickname, color);
        //otherwise it has already been saved and will be rendered in initialize
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
