package se.snrn.combatcreatures.map.pathfinding;


import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

import java.util.ArrayList;
import java.util.List;

import static se.snrn.combatcreatures.map.TileType.WALL;

public class FloodFill {



    public FloodFill() {

    }


    public static ArrayList<Tile> getFloodFromTile(TileMap tileMap, Tile startTile) {
        ArrayList<Tile> filledTiles = new ArrayList<>();
        List<Tile> openList = new ArrayList<>();
        List<Tile> closedList = new ArrayList<>();

        Tile currentTile = tileMap.getTile(startTile.getX(), startTile.getY());
        openList.add(currentTile);
        filledTiles.add(currentTile);
        while (!openList.isEmpty()) {
            currentTile = openList.get(0);
            for (Tile consideredTile : tileMap.getOrthoNeighbours(currentTile)
                    ) {

                if (consideredTile.getType() != WALL && !openList.contains(consideredTile) && !closedList.contains(consideredTile)) {
                    openList.add(consideredTile);
                    filledTiles.add(consideredTile);
                }
            }
            openList.remove(currentTile);
            closedList.add(currentTile);

        }
        return filledTiles;

    }

    public static ArrayList<Tile> getWallsFromTile(TileMap tileMap, Tile startTile) {
        ArrayList<Tile> walls = new ArrayList<>();
        ArrayList<Tile> filledTiles = new ArrayList<>();
        List<Tile> openList = new ArrayList<>();
        List<Tile> closedList = new ArrayList<>();

        Tile currentTile = tileMap.getTile(startTile.getX(), startTile.getY());
        openList.add(currentTile);
        filledTiles.add(currentTile);
        while (!openList.isEmpty()) {
            currentTile = openList.get(0);
            for (Tile consideredTile : tileMap.getAllNeighbours(currentTile)
                    ) {

                if (consideredTile.getType() != WALL && !openList.contains(consideredTile) && !closedList.contains(consideredTile)) {
                    openList.add(consideredTile);
                    filledTiles.add(consideredTile);
                }
                if(consideredTile.getType() == WALL && !walls.contains(consideredTile)) {
                    walls.add(consideredTile);
                }
            }
            openList.remove(currentTile);
            closedList.add(currentTile);

        }
        return walls;
    }
}
