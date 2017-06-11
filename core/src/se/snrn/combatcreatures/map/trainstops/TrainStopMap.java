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
    private ArrayList<Tile> emptyTiles;


    public TrainStopMap(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        startTile = getTile(10,10);
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

    public ArrayList<Tile> getEmptyTiles() {
        return emptyTiles;
    }
}
