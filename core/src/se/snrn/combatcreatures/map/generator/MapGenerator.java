package se.snrn.combatcreatures.map.generator;


import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.pathfinding.FloodFill;

import java.util.ArrayList;

import static se.snrn.combatcreatures.map.TileType.FLOOR;
import static se.snrn.combatcreatures.map.TileType.WALL;

public class MapGenerator {

    private int width;
    private int height;

    public MapGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * generates a TileMap from specific cellular automata settings
     * good standard setting: 0.4, 3, 4, 6
     *
     * @param chanceToStartAlive the chance for the initial state of any tile being alive (0.0 - 1.0)
     * @param deathLimit         if a living cell has less than this amount of neighbours, it dies
     * @param birthLimit         if a dead cell has more than this amount of neighbours, it is born
     * @param numberOfSteps      the amount of times this simulation is run in the initial random map
     */
    public TileMap getCellAutoMap(double chanceToStartAlive, int deathLimit, int birthLimit, int numberOfSteps) {
        //create 2d array of booleans set to false
        boolean[][] cellmap = new boolean[width][height];
        //inititalize the map with 0-100% chance for each tile to start alive
        cellmap = initialiseMap(cellmap, chanceToStartAlive);
        //run through the rules a number of times
        for (int i = 0; i < numberOfSteps; i++) {
            cellmap = doSimulationStep(cellmap, deathLimit, birthLimit);
        }


        //make true tiles FULL and false tiles OPEN
        TileMap map = getMapFromBool(cellmap, width, height);
        if(!acceptableMap(map)) {
            return getCellAutoMap(chanceToStartAlive,deathLimit,birthLimit,numberOfSteps );
        } else {
            MapParser mapParser = new MapParser();
            mapParser.parseMap(map);
            return map;
        }
    }

    private boolean acceptableMap(TileMap tileMap){
        Tile tile =  MapParser.getRandomEmptyTile(tileMap);
        ArrayList<Tile> emptyTiles;
        emptyTiles = FloodFill.getFloodFromTile(tileMap, tile);
        tileMap.setStartTile(tile);
        System.out.println(emptyTiles.size());
        return emptyTiles.size() > tileMap.getHeight() * tileMap.getWidth() * 0.40;
    }


    public boolean[][] getBooleanMap(double chanceToStartAlive, int deathLimit, int birthLimit, int numberOfSteps) {
        //create 2d array of booleans set to false
        boolean[][] cellmap = new boolean[width][height];
        //inititalize the map with 0-100% chance for each tile to start alive
        cellmap = initialiseMap(cellmap, chanceToStartAlive);
        //run through the rules a number of times
        for (int i = 0; i < numberOfSteps; i++) {
            cellmap = doSimulationStep(cellmap, deathLimit, birthLimit);
        }
        //make true tiles FULL and false tiles OPEN
        return cellmap;
    }


    private TileMap getMapFromBool(boolean[][] cellGrid, int width, int height) {
        TileMap map = new TileMap(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (cellGrid[i][j]) {
                    map.getTileArray()[i][j] = new Tile(i, j, WALL, map);
                }
                if (!cellGrid[i][j]) {
                    map.getTileArray()[i][j] = new Tile(i, j, FLOOR, map);
                }

            }
        }

        return map;
    }

    private boolean[][] initialiseMap(boolean[][] map, double chanceToStartAlive) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (Math.random() < chanceToStartAlive) {
                    map[x][y] = true;
                }
            }
        }
        return map;
    }


    public boolean[][] doSimulationStep(boolean[][] oldMap, int deathLimit, int birthLimit) {

        boolean[][] newMap = new boolean[width][height];
        for (int x = 0; x < oldMap.length; x++) {
            for (int y = 0; y < oldMap[0].length; y++) {
                int nbs = countAliveNeighbours(oldMap, x, y);
                //if neighbours < deathLimit a cell dies, else it stays alive
                if (oldMap[x][y]) {
                    newMap[x][y] = nbs >= deathLimit;
                } //if neighbours > birthLimit a cell is born, else it stays dead
                else {
                    newMap[x][y] = nbs > birthLimit;

                }

            }
        }
        return newMap;
    }


    private int countAliveNeighbours(boolean[][] map, int x, int y) {
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int neighbour_x = x + i;
                int neighbour_y = y + j;
                //If we're looking at the middle point
                if (i == 0 && j == 0) {
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if (neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length) {
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if (map[neighbour_x][neighbour_y]) {
                    count = count + 1;
                }
            }
        }
        return count;
    }
}
