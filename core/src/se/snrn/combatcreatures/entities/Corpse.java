package se.snrn.combatcreatures.entities;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.interfaces.Container;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.items.Item;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Corpse implements Updatable, Renderable, Mapped, Container {


    private final Tile tile;
    private final TileMap tileMap;
    private final Sprite sprite;
    private int floor;


    public Corpse(Creature creature) {
        tile = creature.getTile();
        tileMap = creature.getMap();
        sprite = creature.getDeadSprite();
        floor = creature.mapManager.getFloor();
    }

    @Override
    public void update(float delta) {
        sprite.setPosition(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public Tile getTile() {
        return null;
    }

    @Override
    public TileMap getMap() {
        return null;
    }

    @Override
    public void changeTile(Tile tile) {

    }

    @Override
    public boolean move(Direction direction) {
        return false;
    }

    @Override
    public ArrayList<Item> getLoot() {
        return null;
    }

    public int getFloor() {
        return floor;
    }
}
