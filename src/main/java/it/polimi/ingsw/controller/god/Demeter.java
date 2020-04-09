package it.polimi.ingsw.controller.god;

import it.polimi.ingsw.controller.GodController;
import it.polimi.ingsw.controller.UnableToBuildException;
import it.polimi.ingsw.controller.UnableToMoveException;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;
import it.polimi.ingsw.model.WorkerBuildMap;


public class Demeter extends God {

    public String description = "Your Worker may build one additional time, but not on the same space.";

    Cell firstBuildCell;

    public Demeter (GodController godController) {
        super(godController);
        firstBuildCell = null;
    }

    @Override
    public void evolveTurn(Worker w) throws UnableToBuildException, UnableToMoveException {
        move(w);
        win(w);
        firstBuildCell = build(w);
        buildAgain(w);
    }

    private void buildAgain(Worker worker) {
        WorkerBuildMap buildMap;
        try {
            buildMap = updateBuildMap(worker);
        } catch (UnableToBuildException ex) {
            //todo print you cant build again
            return;
        }

        Board board = worker.getPlayer().getGame().getBoard();


        int[] buildInput = getInputSecondBuildPosition();  //returns build position + type: block/dome
        int xBuild = buildInput[0];
        int yBuild = buildInput[1];
        int buildType = buildInput[2]; //0 is block, 1 is dome

        Cell buildPosition = board.findCell(xBuild, yBuild);


        if (buildPosition != firstBuildCell) {

            //build Dome
            if (buildType == 1) {

                if (buildMap.isAllowedToBuildBoard(xBuild, yBuild) && buildPosition.getLevel() == 3) {
                    worker.buildDome(xBuild, yBuild);

                } else {
                    //todo View + Controller error
                }

            } else if (buildType == 2) {    //build Block
                if (buildMap.isAllowedToBuildBoard(xBuild, yBuild) && buildPosition.getLevel() < 3) {
                    worker.buildBlock(xBuild, yBuild);

                } else {
                    //todo View + Controller error
                }
            }
        } else {
            //todo View + Controller error cant build in initial position
        }

    }


    public GodController getGodController(){
        return godController;
    }

}