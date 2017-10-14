package se.snrn.combatcreatures.map.generator;

import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.prefabs.MapComponent;
import se.snrn.combatcreatures.map.prefabs.Room;
import se.snrn.combatcreatures.map.trainstops.SwitchStop;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import java.util.ArrayList;


public class DungeonGenerator {

    public static Tile prevTile;
    public static Tile nextTile;
    static ArrayList<Room> rooms;

    public static TrainStopMap getDungeonMap(int width, int height) {
        Tile[][] tiles = getFilledMap(width, height);

        rooms = new ArrayList<>();


        SwitchStop switchStop = new SwitchStop(tiles);

        Tile startTile = tiles[RandomNumber.range(0, width - 1)][6];
        startTile.setType(TileType.DOWN);
        switchStop.setStartTile(startTile);

        prevTile = startTile;

        for (int i = 0; i < 10; i++) {

            addRandomRoom(tiles);
            makeCorridor(tiles, prevTile, nextTile);
            prevTile = nextTile;
        }


        return switchStop;
    }

    private static Tile[][] addRandomRoom(Tile[][] tiles) {
        Room room = new Room(RandomNumber.range(4, 9), RandomNumber.range(4, 9));
        rooms.add(room);
        nextTile = room.getMiddle();
        room.getMiddle().setType(TileType.DOWN);
        return addMapComponent(tiles, room, RandomNumber.range(0, (tiles.length - room.getWidth()) - 1), RandomNumber.range(6, (tiles[0].length - room.getHeight()) - 1));
    }

    private static Tile[][] makeCorridor(Tile[][] tiles, Tile start, Tile end) {
        int currentX = start.getX();
        int currentY = start.getY();

        int endX = end.getX();
        int endY = end.getY();

        while (currentX != endX) {
            if (currentX > endX) {
                currentX--;
                tiles[currentX][currentY].setType(TileType.FLOOR);
            } else if (currentX < endX) {
                currentX++;
                tiles[currentX][currentY].setType(TileType.FLOOR);
            }
        }
        while (currentY != endY) {
            if (currentY > endY) {
                currentY--;
                tiles[currentX][currentY].setType(TileType.FLOOR);
            } else if (currentY < endY) {
                currentY++;
                tiles[currentX][currentY].setType(TileType.FLOOR);
            }
        }
        return tiles;
    }

    private static Tile[][] addMapComponent(Tile[][] tiles, MapComponent mapComponent, int x, int y) {
        for (Tile[] tile : mapComponent.getTiles()) {
            for (Tile aTile : tile) {

                if(tiles[aTile.getX() + x][aTile.getY() + y].getType() == TileType.FLOOR
                        && aTile.getType() == TileType.WALL){
                    aTile.setType(TileType.FLOOR);
                }

                tiles[aTile.getX() + x][aTile.getY() + y] = aTile;
                aTile.setPosition(aTile.getX() + x, aTile.getY() + y);

            }
        }
        return tiles;
    }

    private static Tile[][] getFilledMap(int width, int height) {
        Tile[][] tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y, TileType.EARTH);
            }
        }
        return tiles;
    }
}
