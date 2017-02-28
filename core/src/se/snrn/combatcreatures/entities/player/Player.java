package se.snrn.combatcreatures.entities.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.effects.Effect;
import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.Stats;
import se.snrn.combatcreatures.interfaces.*;
import se.snrn.combatcreatures.inventory.Inventory;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.userinterface.GameLog;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;
import static se.snrn.combatcreatures.MissionScreen.turnManager;

public class Player implements Updatable, Renderable, Mapped, Living, Fighter {
    private int health;
    private Tile tile;
    private MapManager mapManager;
    private TileMap tileMap;
    private Sprite sprite;
    private boolean alive;
    private Stats stats;
    private PlayerEquipment playerEquipment;
    private ArrayList<Effect> effects;
    private int karies;
    private Inventory inventory;
    private int mana;

    public Player(Tile tile, MapManager mapManager, Stats stats) {
        this.tile = tile;
        this.mapManager = mapManager;
        tileMap = mapManager.getMap();
        this.stats = stats;
        sprite = ResourceManager.player;
        alive = true;
        health = 10;
        mana = 5;
        karies = 3;
        playerEquipment = new PlayerEquipment();
        inventory = new Inventory();
        effects = new ArrayList<>();
    }

    public ArrayList<Effect> getAllEffects() {
        ArrayList<Effect> allEffects = new ArrayList<>();
        allEffects.addAll(playerEquipment.getPermanentBuffEffects());
        allEffects.addAll(effects);
        return allEffects;
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
        return tile;
    }

    @Override
    public TileMap getMap() {
        return tileMap;
    }

    @Override
    public void changeTile(Tile tile) {
        this.tile.setMapped(null);
        tile.setMapped(this);
        this.tile = tile;
        tile.stepOn(this);
        mapManager.setLineOfSight(tile, 4);
    }

    @Override
    public boolean move(Direction direction) {
        Tile newTile = tileMap.getTile(tile.getX() + direction.getX(), tile.getY() + direction.getY());
        if (newTile != null && newTile.getType() == TileType.FLOOR) {
            if (newTile.getMapped() instanceof Creature) {
                Creature creature = (Creature) newTile.getMapped();
                creature.takeDamage(1);
                GameLog.addMessage(creature.getName() + " took 1 damage");
                turnManager.endPlayerTurn();
                return true;
            }

            changeTile(newTile);
            turnManager.endPlayerTurn();
            return true;
        }
        if (newTile != null && newTile.getType() == TileType.DOWN) {
            mapManager.moveDown();
            tileMap = mapManager.getMap();
            changeTile(mapManager.getStartTile());
            return true;
        }
        if (newTile != null && newTile.getType() == TileType.UP) {
            mapManager.moveUp();
            tileMap = mapManager.getMap();
            changeTile(mapManager.getEndTile());
            return true;
        }
        return false;
    }


    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void healDamage(int damage) {
        health += damage;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void die() {

    }

    @Override
    public Stats getStats() {
        return stats;
    }

    public void addKaries(int karies) {
        this.karies += karies;
    }

    public void removeKaries(int karies) {
        this.karies -= karies;
    }

    public int getKaries() {
        return karies;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getMana() {
        return mana;
    }

    public int getFloor() {
        return mapManager.getFloor();
    }
}

