package se.snrn.combatcreatures.map.prefabs;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.map.Tile;


public class MapSegment implements MapComponent {


    private Tile[][] tiles;
    int width;
    int height;


    public MapSegment(Tile[][] tiles) {

        this.width = 9;
        this.height = 9;

        this.tiles = tiles;






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

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    @Override
    public Tile[][] getTiles() {
        return tiles;
    }
}
