package se.snrn.combatcreatures.map.prefabs;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;

public class Tracks implements MapComponent {


    private int width;
    private int height;
    public Tile[][] tiles;

    public Tracks(int width) {

        this.width = width;
        this.height = 6;

        tiles = new Tile[width][height];


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (y == height - 1) {
                    tiles[x][y] = new Tile(x, y, TileType.TRACK_TOP);
                } else if (y == 0) {
                    tiles[x][y] = new Tile(x, y, TileType.TRACK_BOTTOM);
                } else {
                    tiles[x][y] = new Tile(x, y, TileType.TRACK_MIDDLE);
                }
            }
        }
    }
    @Override
    public void render(Batch batch) {
        for (Tile[] tile : tiles) {
            for (Tile aTile : tile) {
                aTile.render(batch);
            }
        }
    }


    @Override
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

    @Override
    public Tile[][] getTiles() {
        return tiles;
    }

    @Override
    public Direction getDoorDirection() {
        return null;
    }

    @Override
    public Tile getDoor() {
        return null;
    }

    @Override
    public Tile getMiddle() {
        return null;
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
