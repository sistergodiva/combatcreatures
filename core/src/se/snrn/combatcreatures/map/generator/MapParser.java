package se.snrn.combatcreatures.map.generator;


import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.pathfinding.AStar;
import se.snrn.combatcreatures.map.pathfinding.FloodFill;

import java.util.ArrayList;

import static se.snrn.combatcreatures.map.TileType.*;


public class MapParser {


    public static Tile getRandomEmptyTile(TileMap tileMap) {
        Tile emptyTile = tileMap.getTile(RandomNumber.range(0, tileMap.getWidth()), RandomNumber.range(0, tileMap.getHeight()));

        if (emptyTile != null && emptyTile.getType() == FLOOR) {
            return emptyTile;
        }
        return getRandomEmptyTile(tileMap);
    }


    public void parseMap(TileMap tileMap) {
        ArrayList<Tile> filled;


        Tile startTile = getRandomEmptyTile(tileMap);

        tileMap.setFilled(filled = FloodFill.getFloodFromTile(tileMap, startTile));
        tileMap.setWalls(FloodFill.getWallsFromTile(tileMap, startTile));
        tileMap.setSpawns(getSpawnLocations(tileMap, filled));
        fillExtraRooms(tileMap);
        generateStairs(tileMap);
    }

    private void generateStairs(TileMap tileMap) {
        Tile stairsUp = getRandomEmptyTile(tileMap);
        Tile stairsDown = getRandomEmptyTile(tileMap);
        if (AStar.getDistance(stairsUp, stairsDown, tileMap) < 80) {
            System.out.println((AStar.getDistance(stairsUp, stairsDown, tileMap)));
            generateStairs(tileMap);
        } else {

            stairsUp.setType(UP);
            stairsDown.setType(DOWN);
        }
    }

    public void fillExtraRooms(TileMap tileMap) {
        for (int i = 0; i < tileMap.getWidth(); i++) {
            for (int j = 0; j < tileMap.getHeight(); j++) {
                if (!tileMap.getFilled().contains(tileMap.tiles[i][j])) {
                    tileMap.tiles[i][j].setType(WALL);
                }
            }
        }
    }

    private ArrayList<Tile> findPath(Tile start, Tile goal, TileMap tileMap) {
        return AStar.calculateAStarNoTerrain(start, goal, tileMap);
    }

    private ArrayList<Tile> getSpawnLocations(TileMap map, ArrayList<Tile> filled) {
        int fullNeighbours = 0;
        ArrayList<Tile> spawnLocations = new ArrayList<>();

        for (Tile tile : filled
                ) {
            if (!tile.isVisible()) {
                ArrayList<Tile> neighbours = map.getOrthoNeighbours(tile);
                for (Tile neighbour : neighbours
                        ) {
                    if (neighbour.getType() == WALL) {
                        fullNeighbours++;
                    }
                }
                if (fullNeighbours >= 2) {
                    spawnLocations.add(tile);
                    fullNeighbours = 0;
                }
            }
        }
        return spawnLocations;
    }
}
