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
    private boolean visible;
    private boolean explored;
    private Mapped mapped;

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public void render(Batch batch) {
        if(explored) {
            if (this.type == TileType.WALL) {
                ResourceManager.wall.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.wall.draw(batch);
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
}
