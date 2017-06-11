package se.snrn.combatcreatures.map.prefabs;


import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.Tile;

public interface MapComponent extends Renderable{

    int getWidth();
    int getHeight();
    Tile getTile(int x, int y);

    Tile[][] getTiles();

    Direction getDoorDirection();

    Tile getDoor();

    Tile getMiddle();

    static Tile[][] rotateCW(Tile[][] mat) {
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
}
