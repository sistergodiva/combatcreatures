package se.snrn.combatcreatures.map;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.items.Item;
import se.snrn.combatcreatures.map.trainstops.MapRoom;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Tile implements Renderable {

    private int x;
    private int y;
    private TileType type;
    private boolean visible;
    private boolean explored = true;
    private Mapped mapped;
    private ArrayList<Item> items;
    private MapRoom mapRoom;
    private int tileValue = 999;

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        items = new ArrayList<>();
        mapRoom = null;
    }



    @Override
    public void render(Batch batch) {

        if (explored) {
            if (this.type == TileType.DOOR) {
                ResourceManager.grass.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.grass.draw(batch);
                ResourceManager.doorClosed.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.doorClosed.draw(batch);
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

                if(tileValue == 999 || MissionScreen.trainStopMap.isDirty()) {
                    tileValue = TileBitmask.getTileNumber(this, MissionScreen.trainStopMap);
                }
                ResourceManager.grass.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.grass.draw(batch);
                ResourceManager.getWallFromBitmask(tileValue).setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.getWallFromBitmask(tileValue).draw(batch);

            }

            if (this.type == TileType.FLOOR) {
                ResourceManager.grass.setPosition(x * TILE_SIZE, y * TILE_SIZE);
                ResourceManager.grass.draw(batch);
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
        if (mapped != null && mapped instanceof Creature) {
            Creature creature = (Creature) mapped;
            creature.setActive(true);
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Mapped getMapped() {
        return mapped;
    }

    public void stepOn(Player player) {
        for (Item item : items
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


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setMapRoom(MapRoom mapRoom) {
        this.mapRoom = mapRoom;
    }

    public MapRoom getMapRoom() {
        return mapRoom;
    }
}
