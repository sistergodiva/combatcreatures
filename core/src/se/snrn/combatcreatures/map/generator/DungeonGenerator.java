package se.snrn.combatcreatures.map.generator;


import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.DirectionDiagonal;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.prefabs.ImageToPrefab;
import se.snrn.combatcreatures.map.prefabs.MapComponent;
import se.snrn.combatcreatures.map.prefabs.PrefabFactory;

import java.util.ArrayList;
import java.util.List;

import static se.snrn.combatcreatures.map.TileType.*;

public class DungeonGenerator {

    private static List<MapComponent> placedRoomsArray;


    public static Tile[][] createDungeonFromAutomata(int width, int height) {
        placedRoomsArray = new ArrayList<>();

        MapGenerator mapGenerator = new MapGenerator(width, height);

        Tile[][] tiles = mapGenerator.getCellAutoMap(0.45, 3, 4, 3).getTileArray();


        int tries = 1000;
        int placedRooms = 0;
        for (int i = 0; i < tries; i++) {
            if (placeRoom(tiles, PrefabFactory.getRandomRoom())) {
                placedRooms++;
            }
        }


        digCorridor(tiles, tiles[1][1], 10, Direction.EAST);
        digCorridor(tiles, tiles[1][1], 10, Direction.NORTH);

        placedRoomsArray.get(1).getTiles()[0][RandomNumber.range(1, placedRoomsArray.get(1).getHeight())].setType(DOOR);
        int placedX = placedRoomsArray.get(0).getTiles()[1][1].getX();
        int placedY = placedRoomsArray.get(0).getTiles()[1][1].getY();

        tiles[placedX][placedY].setType(WALL);

        System.out.println("placed rooms: " + placedRooms);
        return tiles;
    }


    public static Tile[][] createDungeon(int width, int height) {
        placedRoomsArray = new ArrayList<>();
        Tile[][] tiles = new Tile[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(i, j, WALL);
            }
        }


        int tries = 1000;
        int placedRooms = 0;
        for (int i = 0; i < tries; i++) {
            if (placeRoom(tiles, PrefabFactory.getRandomRoom())) {
                placedRooms++;
                if (!placedRoomsArray.isEmpty()) {
                    MapComponent lastRoomPlaced = placedRoomsArray.get(placedRoomsArray.size() - 1);
                    digCorridor(tiles, lastRoomPlaced.getTiles()[lastRoomPlaced.getWidth() - 2][lastRoomPlaced.getHeight() - 2], 15, Direction.values()[RandomNumber.range(0, Direction.values().length - 1)]);
                }
            }
        }

//        digCorridor(tiles, tiles[1][1], 10, Direction.EAST);
//        digCorridor(tiles, tiles[1][1], 10, Direction.NORTH);

        int placedX = placedRoomsArray.get(0).getTiles()[1][1].getX();
        int placedY = placedRoomsArray.get(0).getTiles()[1][1].getY();

        tiles[placedX][placedY].setType(WALL);

        System.out.println("placed rooms: " + placedRooms);
        return tiles;
    }


    public static void digCorridor(Tile[][] tiles, Tile startTile, int length, Direction direction) {
        for (int i = 0; i < length; i++) {

            int xHere = startTile.getX() + direction.getX() * i;
            int yHere = startTile.getY() + direction.getY() * i;
            if (xHere >= 0 && yHere >= 0 && xHere < tiles.length && yHere < tiles[0].length && tiles[xHere] != null && tiles[xHere][yHere] != null) {
                tiles[xHere][yHere].setType(TileType.FLOOR);
            }
        }
    }

    public static boolean placeRoom(Tile[][] tiles, MapComponent room) {
        int xTry = RandomNumber.range(0, tiles.length - room.getTiles().length);
        int yTry = RandomNumber.range(0, tiles[0].length - room.getTiles()[0].length);

        boolean success = true;

        for (Tile[] tile : room.getTiles()) {
            for (Tile aTile : tile) {
                if (tiles[aTile.getX() + xTry][aTile.getY() + yTry].getType() == FLOOR) {
                    success = false;
                }
            }
        }
        if (success) {
            placedRoomsArray.add(room);
            for (Tile[] tile : room.getTiles()) {
                for (Tile aTile : tile) {
                    if (tiles[aTile.getX() + xTry][aTile.getY() + yTry].getType() == WALL) {
                        tiles[aTile.getX() + xTry][aTile.getY() + yTry] = aTile;
                        aTile.setPosition(aTile.getX() + xTry, aTile.getY() + yTry);
                    }
                }
            }
        }
        return success;
    }

}
