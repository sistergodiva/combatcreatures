package se.snrn.combatcreatures.map.generator;


import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.pathfinding.AStar;
import se.snrn.combatcreatures.map.pathfinding.FloodFill;

import java.util.ArrayList;

import static se.snrn.combatcreatures.map.TileType.FLOOR;
import static se.snrn.combatcreatures.map.TileType.WALL;


public class MapParser {


    public Tile getRandomEmptyTile(TileMap tileMap) {
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
