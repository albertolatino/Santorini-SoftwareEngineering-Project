package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.ClientViewObserver;
import it.polimi.ingsw.server.VirtualView;

import java.util.ArrayList;


/**
 * This class represents a board's cell structure.
 * It contains the info about the height of the building in this specific position of the board,
 * which is defined thanks to precise Cartesian attributes,
 * and then if there is a worker standing in this position at a certain point of the game.
 */
public class Cell {

    private final ArrayList<ClientViewObserver> cellObservers;
    private boolean dome;
    private int level;
    private Worker worker;
    private final int x;
    private final int y;
    private final boolean inPerimeter;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.level = 0;
        this.worker = null;
        this.dome = false;
        inPerimeter = x == 0 || x == (Board.SIDE - 1) || y == 0 || y == (Board.SIDE - 1);
        cellObservers = new ArrayList<>();
    }


    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }


    public void setDome(boolean dome) {
        this.dome = dome;
    }


    public void setWorker(Worker worker) {
        this.worker = worker;
    }


    public boolean hasDome() {
        return dome;
    }


    /**
     * Builds 1 block more in this cell
     */
    public void buildBlock() {
        level++;
        notifyObservers();
    }


    /**
     * Build the dome in the cell
     */
    public void buildDome() {
        dome = true;
        notifyObservers();
    }


    /**
     * Finds out if in this cell there's a worker or not.
     *
     * @return true if the cell contains a worker
     */
    public boolean hasWorker() {
        return worker != null;
    }


    /**
     * States that a worker is moving in the cell.
     *
     * @param worker Is the worker arriving in the cell
     */
    public void moveIn(Worker worker) {
        this.worker = worker;
        notifyObservers(); //when a worker has moved in this Cell, it will be changed in the View
    }


    /**
     * States that a worker isn't in this cell anymore.
     */
    public void moveOut() {
        worker = null;
        notifyObservers(); //when a worker has moved out from this Cell, it will be changed in the View
    }


    /**
     * Finds out if the cell stays on the border of the board.
     *
     * @return true if the cell is on the border of the board
     */
    public boolean isInPerimeter() {
        return inPerimeter;
    }


    /**
     * Checks if cell has a worker or a dome on it.
     * @return true if is occupied, otherwise false.
     */
    public boolean isOccupied() {
        return hasWorker() || hasDome();
    }


    /**
     * Getter method of the worker attribute of this class.
     * @return The specific worker that stands inside this cell, null if there's no one.
     */
    public Worker getWorker() {
        return worker;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    //OBSERVER METHODS

    /**
     * This method adds a new Observer.
     *
     * @param newObserver Reference of the observer.
     */
    public void register(ClientViewObserver newObserver) {

        this.cellObservers.add(newObserver);

    }


    /**
     * This method removes an observer.
     *
     * @param myObserver The observer to be unregistered.
     */
    public void unregister(ClientViewObserver myObserver) {

        this.cellObservers.remove(myObserver);
    }


    public ArrayList<ClientViewObserver> getCellObservers(){
        return this.cellObservers;
    }


    /**
     * This method updates all the Observer of the Worker Class.
     */
    public void notifyObservers() {

        for (ClientViewObserver observer : this.cellObservers)
            observer.update(this);
    }

    /**
     * Removes from the cellObservers list the client that is observing this specific cell.
     * @param client client to be removed from observers.
     */
    public void remove(VirtualView client) {

        cellObservers.remove(client);
    }
}


