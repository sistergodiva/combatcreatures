package se.snrn.combatcreatures.worldmap;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;

import java.util.ArrayList;

public class WorldMap implements Renderable {

    private final int width;
    private final int height;
    private Tile[][] tiles;
    private Tile startTile;
    private Tile endTile;
    private ArrayList<Tile> trainTrack;

    public WorldMap(int width, int height) {
        new AStar();

        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (RandomNumber.range(0, 5) < 2) {
                    tiles[x][y] = new Tile(x, y, TileType.HILL);

                } else {
                    tiles[x][y] = new Tile(x, y, TileType.FLOOR);
                }
            }
        }
        startTile = getTile(0, 0);
        endTile = getTile(width - 1, height - 1);
        drawTrack();
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

    public void drawTrack() {

        trainTrack = AStar.calculateAStarNoTerrain(startTile, endTile, this);


        for (Tile track: trainTrack
             ) {
            track.setType(TileType.TRACK);
        }


        trainTrack.get(10).setType(TileType.BROKEN_TRACK);
    }


    @Override
    public void render(Batch batch) {
        for (Tile[] tile : tiles) {
            for (Tile aTile : tile) {
                aTile.render(batch);
            }
        }
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

    public ArrayList<Tile> getTrainTrack() {
        return trainTrack;
    }

    public Tile getNextTrackTile(Tile tile) {
        return trainTrack.get(trainTrack.indexOf(tile) + 1);
    }

    public Tile getLastTrackTile(Tile tile) {
        return trainTrack.get(trainTrack.indexOf(tile) - 1);
    }
}
