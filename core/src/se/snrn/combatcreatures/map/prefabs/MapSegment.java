package se.snrn.combatcreatures.map.prefabs;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.map.Direction;
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
}
