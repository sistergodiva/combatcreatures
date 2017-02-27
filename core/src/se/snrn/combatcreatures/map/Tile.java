package se.snrn.combatcreatures.map;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.items.Item;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Tile implements Renderable {

    private int x;
    private int y;
    private TileType type;
    private TileMap tileMap;
    private boolean visible;
    private boolean explored;
    private Mapped mapped;
    private ArrayList<Item> items;

    public Tile(int x, int y, TileType type, TileMap tileMap) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.tileMap = tileMap;
        items = new ArrayList<>();
        if(MissionScreen.debug = true){
            explored = true;
        }
    }

    @Override
    public void render(Batch batch) {
        if (explored) {
            if (this.type == TileType.DOOR) {
                ResourceManager.up.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.up.draw(batch);
            }

            if (this.type == TileType.UP) {
                ResourceManager.up.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.up.draw(batch);
            }

            if (this.type == TileType.DOWN) {
                ResourceManager.down.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.down.draw(batch);
            }
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

        if (!items.isEmpty()) {
            for (Item item : items) {
                item.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                item.render(batch);
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
        if(mapped != null && mapped instanceof Creature){
            Creature creature = (Creature)mapped;
            creature.setActive(true);
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Mapped getMapped() {
        return mapped;
    }

    public void stepOn(Player player){
        for (Item item: items
             ) {
            player.getInventory().addItem(item);

        }
        items.clear();
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(int itemInt) {
        return items.get(itemInt);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
