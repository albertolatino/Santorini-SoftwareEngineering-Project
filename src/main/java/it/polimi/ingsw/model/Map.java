package it.polimi.ingsw.model;

/**
 * This class represents the structure where the players can play their game
 */

public class Map {
    private final Game game;
    private final Cell[][] map;
    public static final int SIDE = 5;

    public Map(Game game) {
        this.game = game;
        this.map = new Cell[SIDE][SIDE];
        for(int i = 0; i < SIDE; i++){
            for(int j = 0; j < SIDE; j++){
                map[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getMap(){
        return map;
    }

    /**
     * Used to find one specific cell on the map
     * @param x Row of the map
     * @param y Column of the map
     * @return  Returns cell if contained in map, null otherwise.
     */
    public Cell findCell(int x, int y) {
        if(isInMap(x,y))
            return map[x][y];
        return null;
    }

    public boolean isInMap(int x, int y) {
        return x >= 0 && x < SIDE && y >= 0 && y < SIDE;
    }

}
