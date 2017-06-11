package se.snrn.combatcreatures;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.prefabs.MapComponent;

public class Train implements MapComponent {


    private int width;
    private int height;
    public Tile[][] tiles;

    public Train() {

        this.width = 12;
        this.height = 6;

        tiles = new Tile[width][height];


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {


                if (y == 0 || y == height - 1) {
                    tiles[x][y] = new Tile(x, y, TileType.WALL);
                } else {
                    tiles[x][y] = new Tile(x, y, TileType.FLOOR);
                }
            }
        }

        //tiles = rotateCW(tiles);


    }


    Tile[][] rotateCW(Tile[][] mat) {
        final int M = mat.length;
        final int N = mat[0].length;
        Tile[][] ret = new Tile[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M - 1 - r] = mat[r][c];
                ret[c][M - 1 - r].setPosition(c, M - 1 - r);
            }
        }
        return ret;
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
