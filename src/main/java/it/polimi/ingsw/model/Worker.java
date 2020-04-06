package it.polimi.ingsw.model;


import it.polimi.ingsw.view.ViewObserver;

import java.util.ArrayList;

public class Worker {


    private ArrayList<ViewObserver> workerObservers;

    private final Player player;
    private final Sex sex;
    private Cell position;
    private final WorkerMoveMap moveMap;
    private final WorkerBuildMap buildMap;
    private int level;
    private int levelVariation; //level before moving - level after moving
    /** Creates a worker.
     * @param player The worker's owner.
     * @param sex A worker can be Male or Female.
     */
    public Worker(Player player, Sex sex) {

        this.player = player;
        this.sex = sex;
        level = 0;
        levelVariation = 0;
        moveMap = new WorkerMoveMap(this);
        buildMap = new WorkerBuildMap(this);
    }

    /**
     * Changes position of the worker and updates level and movedUp.
     * @param x Coordinate of the new position of the worker.
     * @param y Coordinate of the new position of the worker.
     */
    //prima di chiamare fare check se posso andare (isOccupied)
    public void setPosition(int x, int y) {
        Cell newPosition = player.getGame().getBoard().findCell(x,y);
        int newLevel = newPosition.getLevel();

        //vado via da cella precedente e Position nella nuova
        if(position != null) {
            position.moveOut();
        }
        newPosition.moveIn(this);
        //newPosition.getLevel() > level
        levelVariation = newLevel - level;
        level = newLevel;
        position = newPosition;
    }

    /**
     * Changes position of the worker and updates level and movedUp.
     * @param newPosition Cell the worker is moving into.
     */
    public void setPosition(Cell newPosition) {
        int newLevel = newPosition.getLevel();

        //vado via da cella precedente e Position nella nuova
        if(position != null) {
            position.moveOut();
        }
        newPosition.moveIn(this);
        //newPosition.getLevel() > level
        levelVariation = newLevel - level;
        level = newLevel;
        position = newPosition;
    }

    /**
     * Builds a new block in the specified cell.
     * @param x Coordinate of the position to build in.
     * @param y Coordinate of the position to build in.
     */
    //prima di chiamare faccio check se posso costruire block (canBuildBlock)
    public void buildBlock(int x, int y) {
        Cell buildPosition = player.getGame().getBoard().findCell(x,y);
        buildPosition.buildBlock();
    }

    /**
     * Builds a new dome in the specified cell.
     * @param x Coordinate of the position to build in.
     * @param y Coordinate of the position to build in.
     */
    //prima di chiamare faccio check se posso costruire dome (canBuildDome)
    public void buildDome(int x, int y) {
        Cell buildPosition = player.getGame().getBoard().findCell(x,y);
        buildPosition.buildDome();
    }


    public Player getPlayer() {
        return player;
    }

    public Cell getPosition() {
        return position;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelVariation() {
        return levelVariation;
    }

    public Sex getSex() {
        return sex;
    }

    public WorkerMoveMap getMoveMap() {
        return moveMap;
    }

    public WorkerBuildMap getBuildMap() {
        return buildMap;
    }

    //OBSERVER METHODS

    /**
     * This method adds a new Observer.
     * @param newObserver Reference of the observer.
     */
    public void register(ViewObserver newObserver){

        this.workerObservers.add(newObserver);

    }


    /**
     * This method remove an observer.
     * @param myObserver The observer to be unregistered.
     */
    public void unregister(ViewObserver myObserver){

        this.workerObservers.remove(myObserver);
    }

    /**
     * This method updates all the Observer of the Worker Class.
     */
    public void notifyObservers(){

        for(ViewObserver observer : this.workerObservers )
        {
            observer.update(this);
        }

    }


}
