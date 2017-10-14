package se.snrn.combatcreatures.map.pathfinding;


import se.snrn.combatcreatures.map.Direction;
import se.snrn.combatcreatures.map.DirectionDiagonal;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import java.util.ArrayList;
import java.util.List;

import static se.snrn.combatcreatures.map.TileType.EARTH;
import static se.snrn.combatcreatures.map.TileType.FLOOR;
import static se.snrn.combatcreatures.map.TileType.WALL;

public class FloodFill {


    public static ArrayList<Tile> getFloodFromTileArray(Tile[][] tiles, Tile startTile) {
        ArrayList<Tile> filledTiles = new ArrayList<>();
        List<Tile> openList = new ArrayList<>();
        List<Tile> closedList = new ArrayList<>();

        Tile currentTile = tiles[startTile.getX()][startTile.getY()];
        openList.add(currentTile);
        filledTiles.add(currentTile);
        while (!openList.isEmpty()) {
            currentTile = openList.get(0);
            for (Tile consideredTile : getOrthoNeighbours(currentTile, tiles)
                    ) {

                if (consideredTile.getType() == FLOOR && !openList.contains(consideredTile) && !closedList.contains(consideredTile)) {
                    openList.add(consideredTile);
                    filledTiles.add(consideredTile);
                }
            }
            openList.remove(currentTile);
            closedList.add(currentTile);

        }
        return filledTiles;

    }

    public static ArrayList<Tile> getWallsFromTileArray(Tile[][] tiles, Tile startTile) {
        ArrayList<Tile> walls = new ArrayList<>();
        ArrayList<Tile> filledTiles = new ArrayList<>();
        List<Tile> openList = new ArrayList<>();
        List<Tile> closedList = new ArrayList<>();

        Tile currentTile = tiles[startTile.getX()][startTile.getY()];
        openList.add(currentTile);
        filledTiles.add(currentTile);
        while (!openList.isEmpty()) {
            currentTile = openList.get(0);
            for (Tile consideredTile : getAllNeighbours(tiles, currentTile)
                    ) {

                if (consideredTile.getType() != WALL && !openList.contains(consideredTile) && !closedList.contains(consideredTile)) {
                    openList.add(consideredTile);
                    filledTiles.add(consideredTile);
                }
                if (consideredTile.getType() == WALL && !walls.contains(consideredTile)) {
                    walls.add(consideredTile);
                }
            }
            openList.remove(currentTile);
            closedList.add(currentTile);

        }
        return walls;
    }

    public static ArrayList<Tile> getOrthoNeighbours(Tile startTile, Tile[][] tiles) {
        ArrayList<Tile> allNeighbours = new ArrayList<>();
        for (Direction direction : Direction.values()
                ) {
            if (startTile.getX() + direction.getX() >= 0 && startTile.getY() + direction.getY() >= 0) {
                if (startTile.getX() + direction.getX() < tiles.length && startTile.getY() + direction.getY() < tiles[0].length) {
                    if (tiles[startTile.getX() + direction.getX()] != null && tiles[startTile.getX() + direction.getX()][startTile.getY() + direction.getY()] != null) {
                        Tile t = tiles[startTile.getX() + direction.getX()][startTile.getY() + direction.getY()];
                        if (t != null) {
                            allNeighbours.add(t);
                        }
                    }
                }
            }
        }
        return allNeighbours;

    }

    public static ArrayList<Tile> getAllNeighbours(Tile[][] tiles, Tile startTile) {
        ArrayList<Tile> allNeighbours = new ArrayList<>();
        for (DirectionDiagonal direction : DirectionDiagonal.values()
                ) {
            if (startTile.getX() + direction.getX() >= 0 && startTile.getY() + direction.getY() >= 0) {
                if (startTile.getX() + direction.getX() < tiles.length && startTile.getY() + direction.getY() < tiles[0].length) {
                    if (tiles[startTile.getX() + direction.getX()] != null && tiles[startTile.getX() + direction.getX()][startTile.getY() + direction.getY()] != null) {
                        Tile t = tiles[startTile.getX() + direction.getX()][startTile.getY() + direction.getY()];
                        if (t != null) {
                            allNeighbours.add(t);
                        }
                    }
                }
            }
        }
        return allNeighbours;
    }

    public static ArrayList<Tile> getFloodFromTile(TrainStopMap tileMap, Tile startTile) {
        ArrayList<Tile> filledTiles = new ArrayList<>();
        List<Tile> openList = new ArrayList<>();
        List<Tile> closedList = new ArrayList<>();

        Tile currentTile = tileMap.getTile(startTile.getX(), startTile.getY());
        openList.add(currentTile);
        filledTiles.add(currentTile);
        while (!openList.isEmpty()) {
            currentTile = openList.get(0);
            for (Tile consideredTile : tileMap.getOrthogonalNeighbours(currentTile)
                    ) {

                if (consideredTile.getType() == FLOOR && !openList.contains(consideredTile) && !closedList.contains(consideredTile)) {
                    openList.add(consideredTile);
                    filledTiles.add(consideredTile);
                }
            }
            openList.remove(currentTile);
            closedList.add(currentTile);

        }
        return filledTiles;

    }


    public static ArrayList<Tile> getWallsFromTile(TrainStopMap tileMap, Tile startTile) {
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
                if (consideredTile.getType() == WALL || consideredTile.getType() == EARTH && !walls.contains(consideredTile)) {
                    walls.add(consideredTile);
                }
            }
            openList.remove(currentTile);
            closedList.add(currentTile);

        }
        return walls;
    }
}
