package se.snrn.combatcreatures.map.generator;


import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.DirectionDiagonal;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.pathfinding.AStar;
import se.snrn.combatcreatures.map.pathfinding.FloodFill;
import se.snrn.combatcreatures.map.trainstops.MapRoom;
import se.snrn.combatcreatures.map.trainstops.TileMap;

import java.util.ArrayList;

import static se.snrn.combatcreatures.map.TileType.*;


public class MapParser {

    public void parseMap(TileMap map) {


        Tile startTile = map.getStartTile();
        map.setOpenTiles(FloodFill.getFloodFromTile(map, startTile));
        map.setWalls(FloodFill.getWallsFromTile(map, startTile));

        createDoors(map);
        generateStairs(map);
        findRooms(map);
        markWalls(map);
        findSpawnLocations(map);
        //map.setSpawns(getSpawnLocations(map, map.getOpenTiles()));
        System.out.println("set spawns");
        ArrayList<MapRoom> rooms = map.getRooms();
        for (MapRoom room: rooms
             ) {

            if(findPath(room.getRoomTiles().get(0), startTile, map) == null) {
                for (Tile tile : room.getRoomTiles()) {
                    tile.setType(DOWN);
                }
            }
        }
    }

    private void findSpawnLocations(TileMap map) {
        int fullNeighbours = 0;
        ArrayList<Tile> spawnLocations = new ArrayList<>();

        for (Tile tile : map.getOpenTiles()
                ) {
            if (!tile.isVisible()) {
                ArrayList<Tile> neighbours = map.getOrthogonalNeighbours(tile);
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
        map.setSpawns(spawnLocations);
        System.out.println(map.getSpawns().size());
    }

    public static Tile getRandomEmptyTile(TileMap tileMap) {
        Tile emptyTile = tileMap.getTile(RandomNumber.range(0, tileMap.getWidth()), RandomNumber.range(0, tileMap.getHeight()));

        if (emptyTile != null && emptyTile.getType() == FLOOR) {
            return emptyTile;
        }
        return getRandomEmptyTile(tileMap);
    }

    private void markWalls(TileMap map) {
        for (Tile wall : map.getWalls()
                ) {
            wall.setType(TileType.WALL);
        }
    }

    private void findRooms(TileMap tileMap) {
        ArrayList<MapRoom> rooms = new ArrayList<>();

        Tile[][] tiles = tileMap.getTiles();

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                Tile tile = tiles[i][j];

                if (tile.getMapRoom() == null && tile.getType() == TileType.FLOOR) {
                    ArrayList<Tile> fill = FloodFill.getFloodFromTileArray(tiles, tile);
                    MapRoom room = new MapRoom(fill);

                    for (Tile fillTile : fill
                            ) {
                        fillTile.setMapRoom(room);
                    }
                    rooms.add(room);
                }
            }
        }


        System.out.println("rooms: " + rooms.size());
        tileMap.setRooms(rooms);
    }

    private void createDoors(TileMap tileMap) {
        for (Tile tile : tileMap.getOpenTiles()) {


            Tile westTile = tileMap.getTileAtDirection(tile, DirectionDiagonal.WEST);
            Tile eastTile = tileMap.getTileAtDirection(tile, DirectionDiagonal.EAST);
            Tile northTile = tileMap.getTileAtDirection(tile, DirectionDiagonal.NORTH);
            Tile southTile = tileMap.getTileAtDirection(tile, DirectionDiagonal.SOUTH);
            if (westTile != null && eastTile != null && southTile != null && northTile != null) {
                if (westTile.getType() == eastTile.getType() && northTile.getType() == southTile.getType() && westTile.getType() != northTile.getType()) {
                    tile.setType(DOOR);
                }
            }
        }
    }

    private void generateStairs(TileMap tileMap) {
        Tile stairsUp = tileMap.getOpenTiles().get(RandomNumber.range(0, tileMap.getOpenTiles().size() - 1));
        Tile stairsDown = tileMap.getOpenTiles().get(RandomNumber.range(0, tileMap.getOpenTiles().size() - 1));
        if (AStar.getDistance(stairsUp, stairsDown, tileMap) < 60) {
            System.out.println((AStar.getDistance(stairsUp, stairsDown, tileMap)));
            generateStairs(tileMap);
        } else {

            stairsUp.setType(UP);
            stairsDown.setType(DOWN);
            tileMap.setStartTile(stairsUp);
            tileMap.setEndTile(stairsDown);

        }
    }

    private ArrayList<Tile> findPath(Tile start, Tile goal, TileMap tileMap) {
        return AStar.calculateAStarNoTerrain(start, goal, tileMap);
    }
}
