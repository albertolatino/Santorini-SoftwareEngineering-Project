package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.BoardClient;
import it.polimi.ingsw.serializableObjects.CellClient;
import it.polimi.ingsw.serializableObjects.WorkerClient;
import it.polimi.ingsw.server.model.Board;
import it.polimi.ingsw.server.model.Worker;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicReference;

import static it.polimi.ingsw.client.gui.GuiManager.*;

public class BoardController {

    @FXML
    public ImageView c0r0;
    @FXML
    public ImageView c1r0;
    @FXML
    public ImageView c2r0;
    @FXML
    public ImageView c3r0;
    @FXML
    public ImageView c4r0;
    @FXML
    public ImageView c0r1;
    @FXML
    public ImageView c1r1;
    @FXML
    public ImageView c2r1;
    @FXML
    public ImageView c3r1;
    @FXML
    public ImageView c4r1;
    @FXML
    public ImageView c0r2;
    @FXML
    public ImageView c1r2;
    @FXML
    public ImageView c2r2;
    @FXML
    public ImageView c3r2;
    @FXML
    public ImageView c4r2;
    @FXML
    public ImageView c0r3;
    @FXML
    public ImageView c1r3;
    @FXML
    public ImageView c2r3;
    @FXML
    public ImageView c3r3;
    @FXML
    public ImageView c4r3;
    @FXML
    public ImageView c0r4;
    @FXML
    public ImageView c1r4;
    @FXML
    public ImageView c2r4;
    @FXML
    public ImageView c3r4;
    @FXML
    public ImageView c4r4;


    @FXML
    private Text myNickname;
    @FXML
    private Text otherNicknameLeft;
    @FXML
    private Text otherNicknameRight;
    @FXML
    private ImageView myGod;
    @FXML
    private ImageView otherGodLeft;
    @FXML
    private ImageView otherGodRight;
    @FXML
    private ImageView godLeftBar;
    @FXML
    private ImageView godLeftFrame;
    @FXML
    private Text mainText;
    @FXML
    private GridPane boardGrid;

    private boolean cellRequested;

    private final Image bluemale;
    private final Image bluefemale;
    private final Image whitemale;
    private final Image whitefemale;
    private final Image beigemale;
    private final Image beigefemale;
    private final Image level1;
    private final Image level2;
    private final Image level3;
    private final Image dome;


    public BoardController() {
        cellRequested = false;
        bluemale = new Image("/board/workers/male_worker_blue.png");
        bluefemale = new Image("/board/workers/female_worker_blue.png");
        whitemale = new Image("/board/workers/male_worker_white.png");
        whitefemale = new Image("/board/workers/female_worker_white.png");
        beigemale = new Image("/board/workers/male_worker_beige.png");
        beigefemale = new Image("/board/workers/female_worker_beige.png");
        level1 = new Image("/board/level1/alto/level1_light.png");
        level2 = new Image("/board/level2/alto/level2_light.png");
        level3 = new Image("/board/level3/alto/level3_light.png");
        dome = new Image("/board/buildings/dome/alto/dome_light.png");
    }

    protected void init() {

        myNickname.setText(nickname1.get());
        otherNicknameRight.setText(nickname2.get());
        Image myGodImage = new Image("/gods/full_" + god1.get().toLowerCase() + ".png");
        Image godRightImage = new Image("/gods/full_" + god2.get().toLowerCase() + ".png");
        myGod.setImage(myGodImage);
        otherGodRight.setImage(godRightImage);

        if (numberOfPlayers.get() == 2) {
            godLeftFrame.setVisible(false);
            godLeftBar.setVisible(false);
            otherNicknameLeft.setVisible(false);
            otherGodLeft.setVisible(false);
        } else {
            otherNicknameRight.setText(nickname3.get());
            Image godLeftImage = new Image("/gods/full_" + god3.get().toLowerCase() + ".png");
            otherGodLeft.setImage(godLeftImage);
        }

        mainText.setText("WELCOME");
    }

    protected void update(CellClient cell) {
        System.out.println("update");
        boardClient.get().update(cell);
    }

    @FXML
    private void chooseCell(MouseEvent event) {
        Node source = (Node) event.getSource();

        //for imageview of worker
        if (source instanceof ImageView)
            source = source.getParent();

        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.printf("Mouse clicked cell in [%d, %d]%n", rowIndex, colIndex);

        //if user clicks on a cell, and a method has requested a cell,
        //coordinates are sent to gui manager
        if (cellRequested) {
            try {
                GuiManager.queue.put(rowIndex);
                GuiManager.queue.put(colIndex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setCellRequested(false);
    }

    @FXML
    private void showGodDescription() {
        System.out.println("God description");
    }

    @FXML
    private void menu() {
        System.out.println("MENU");
    }

    protected void printMap() {

        System.out.println("in printmap");

        //iterate on all cells of cellclient and render them correctly
        //based on the corresponding content of boardclient
        //for (int row = 0; row < Board.SIDE; row++) {
        //for (int col = 0; col < Board.SIDE; col++) {

        for (Node node : boardGrid.getChildren()) {

            Pane pane = (Pane) node;

            int row = GridPane.getRowIndex(node);
            int col = GridPane.getColumnIndex(node);

            CellClient cell = boardClient.get().findCell(row, col);

            //cell contains building
            switch (cell.getCellLevel()) {
                case 0:
                    //nothing, ground level
                    break;
                case 1:
                    ImageView building1 = new ImageView(level1);
                    building1.setFitWidth(80);
                    building1.setFitHeight(80);
                    //boardGrid.add(building1, col, row);
                    pane.getChildren().add(building1);
                    System.out.println("lev1: " + row + "," + col);
                    break;
                case 2:
                    ImageView building2 = new ImageView(level2);
                    building2.setFitWidth(80);
                    building2.setFitHeight(80);
                    //boardGrid.add(building2, col, row);
                    pane.getChildren().add(building2);

                    break;
                case 3:
                    ImageView building3 = new ImageView(level3);
                    building3.setFitWidth(80);
                    building3.setFitHeight(80);
                    //boardGrid.add(building3, col, row);
                    pane.getChildren().add(building3);

                    break;

                default:
                    System.out.println("building level error");
            }

                /*
                    1) aggiungere tutti i building dinamicamente uno sopra l'altro
                     e mettere in ogni cella una imageview per workers

                    2) mettere in ogni cella due imageview: una per worker una per building
                    e se ne può aggiungere una dinamicamente per dome
                */


            //cell contains dome
            if (cell.hasDome()) {
                //boardGrid.add(new ImageView(dome), col, row);
                ImageView domeBuilding = new ImageView(dome);
                domeBuilding.setFitWidth(80);
                domeBuilding.setFitHeight(80);
                pane.getChildren().add(domeBuilding);
            }

            //get ImageView attribute of cell
            Class<?> c = getClass();
            Field field;
            ImageView workerImageView = null;

            try {

                field = c.getDeclaredField("c" + col + "r" + row);
                workerImageView = (ImageView) field.get(this);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }


            //cell contains worker
            if (cell.hasWorker()) {

                String sex = cell.getWorkerClient().getWorkerSex();
                String color = cell.getWorkerClient().getWorkerColor();


                //get image of worker in cell
                Field field2;
                Image workerImage = null;

                try {
                    String workerId = color + sex;
                    field2 = c.getDeclaredField(workerId.toLowerCase());
                    workerImage = (Image) field2.get(this);

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                //workerImageView.toFront();
                workerImageView.setViewOrder(-1.0);
                workerImageView.setImage(workerImage);

            } else {
                workerImageView.setImage(null);
            }
        }
        //}
    }

    protected void printToMainText(String text) {
        mainText.setText(text);
    }

    protected void setCellRequested(boolean cellRequested) {
        this.cellRequested = cellRequested;
    }

    protected void waitChallengerStartPlayer() {

    }

}