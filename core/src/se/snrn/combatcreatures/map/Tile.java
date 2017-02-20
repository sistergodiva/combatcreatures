package se.snrn.combatcreatures.map;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.interfaces.Renderable;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Tile implements Renderable {

    private int x;
    private int y;
    private TileType type;
    private TileMap tileMap;
    private boolean visible;
    private boolean explored;
    private Mapped mapped;

    public Tile(int x, int y, TileType type, TileMap tileMap) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.tileMap = tileMap;
    }

    @Override
    public void render(Batch batch) {
        if(explored) {
//            if (this.type == TileType.WALL) {
//                int tileValue = TileBitMask.getBitMask(this, tileMap);
//                ResourceManager.getWallFromBitMask(tileValue).setPosition(x * TILE_SIZE, y * TILE_SIZE);
//                ResourceManager.getWallFromBitMask(tileValue).draw(batch);
//
//            }
            if (this.type == TileType.WALL) {
                int tileValue = TileBitMask.getBitMask(this, tileMap);
                ResourceManager.getDreamyWallFromBitMask(tileValue).setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.getDreamyWallFromBitMask(tileValue).draw(batch);

            }
            if (this.type == TileType.FLOOR) {
                ResourceManager.floor.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.floor.draw(batch);
            }
            if (!visible) {
                ResourceManager.fog.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.fog.draw(batch);
            }
        }
    }

    public boolean isVisible() {
        return visible;
    }
    public boolean isExplored() {
        return explored;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMapped(Mapped mapped) {
        this.mapped = mapped;
    }

    public TileType getType() {
        return type;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Mapped getMapped() {
        return mapped;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}
