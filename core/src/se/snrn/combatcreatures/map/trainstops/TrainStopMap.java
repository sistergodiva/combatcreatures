package se.snrn.combatcreatures.map.trainstops;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.DirectionDiagonal;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;

import java.util.ArrayList;

public abstract class TrainStopMap implements Renderable {

    private final int width;
    private final int height;
    private Tile[][] tiles;
    private Tile startTile;

    private ArrayList<Tile> openTiles;
    private ArrayList<Tile> walls;
    private ArrayList<Tile> spawns;
    private Tile endTile;
    private ArrayList<MapRoom> rooms;


    public TrainStopMap(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        startTile = getTile(0,0);
        this.spawns = new ArrayList<>();
        walls = new ArrayList<>();
        openTiles = new ArrayList<>();

    }


    @Override
    public void render(Batch batch) {
        for (Tile[] tile : tiles) {
            for (Tile aTile : tile) {
                aTile.render(batch);
            }
        }
    }


    public Tile getTile(int x, int y) {
        if (x < width &&
                x >= 0 &&
                y < height &&
                y >= 0 &&
                tiles[x][y] != null) {
            return tiles[x][y];
        }
        return null;
    }

    public ArrayList<Tile> getOrthogonalNeighbours(Tile tile) {
        ArrayList<Tile> neighbours = new ArrayList<>();
        for (Direction dir : Direction.values()
                ) {
            Tile t = this.getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
            if (t != null) {
                neighbours.add(t);
            }
        }
        return neighbours;

    }

    public ArrayList<Tile> getAllNeighbours(Tile tile) {
        ArrayList<Tile> neighbours = new ArrayList<>();
        for (DirectionDiagonal dir : DirectionDiagonal.values()
                ) {
            Tile t = this.getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
            if (t != null) {
                neighbours.add(t);
            }
        }
        return neighbours;
    }

    public ArrayList<Tile> getOrthogonalNeighboursTerrain(Tile tile) {
        ArrayList<Tile> neighbours = new ArrayList<>();
        for (Direction dir : Direction.values()
                ) {
            Tile t = getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
            if (t != null && t.getType() == TileType.FLOOR && t.getMapped() == null) {
                neighbours.add(t);
            }
        }
        return neighbours;
    }

    public Tile getTileAtDirection(Tile tile, Direction dir) {
        return getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
    }

    public Direction getDirectionFromTile(Tile start, Tile goal) {
        if (getTileAtDirection(start, Direction.NORTH) == goal) {
            return Direction.NORTH;
        }
        if (getTileAtDirection(start, Direction.EAST) == goal) {
            return Direction.EAST;
        }
        if (getTileAtDirection(start, Direction.SOUTH) == goal) {
            return Direction.SOUTH;
        }
        if (getTileAtDirection(start, Direction.WEST) == goal) {
            return Direction.WEST;
        }
        return null;

    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public void setOpenTiles(ArrayList<Tile> openTiles) {
        this.openTiles = openTiles;
    }

    public void setWalls(ArrayList<Tile> walls) {
        this.walls = walls;
    }

    public void setSpawns(ArrayList<Tile> spawns) {
        this.spawns = spawns;
    }

    public ArrayList<Tile> getOpenTiles() {
        return openTiles;
    }

    public void setEndTile(Tile endTile) {
        this.endTile = endTile;
    }

    public void setRooms(ArrayList<MapRoom> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Tile> getWalls() {
        return walls;
    }

    public ArrayList<MapRoom> getRooms() {
        return rooms;
    }

    public ArrayList<Tile> getSpawns() {
        return spawns;
    }
}
