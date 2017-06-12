package se.snrn.combatcreatures.worldmap;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;

public class Worldmap implements Renderable {

    private final int width;
    private final int height;
    private Tile[][] tiles;

    public Worldmap(int width, int height) {

        this.width = width;
        this.height = height;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y, TileType.FLOOR);
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
}
