package se.snrn.combatcreatures.map.generator;


import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
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


        Tile startTile = tileMap.getStartTile();

        tileMap.setFilled(filled = FloodFill.getFloodFromTile(tileMap, startTile));
        tileMap.setWalls(FloodFill.getWallsFromTile(tileMap, startTile));
        tileMap.setSpawns(getSpawnLocations(tileMap, filled));

        createDoors(tileMap);
        generateStairs(tileMap);
    }

    private void createDoors(TileMap tileMap) {
        for (Tile tile : tileMap.getFilled()) {


            Tile westTile = tileMap.getTileAtDirection(tile, Direction.WEST);
            Tile eastTile = tileMap.getTileAtDirection(tile, Direction.EAST);
            Tile northTile = tileMap.getTileAtDirection(tile, Direction.NORTH);
            Tile southTile = tileMap.getTileAtDirection(tile, Direction.SOUTH);
            if (westTile != null && eastTile != null && southTile != null && northTile != null) {
                if (westTile.getType() == eastTile.getType() && northTile.getType() == southTile.getType() && westTile.getType() != northTile.getType()) {
                    tile.setType(DOOR);
                }
            }
        }
    }

    private void generateStairs(TileMap tileMap) {
        Tile stairsUp = tileMap.getFilled().get(RandomNumber.range(0, tileMap.getFilled().size() - 1));
        Tile stairsDown = tileMap.getFilled().get(RandomNumber.range(0, tileMap.getFilled().size() - 1));
        if (AStar.getDistance(stairsUp, stairsDown, tileMap) < 60) {
            System.out.println((AStar.getDistance(stairsUp, stairsDown, tileMap)));
            generateStairs(tileMap);
        } else {

            stairsUp.setType(UP);
            stairsDown.setType(DOWN);
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
