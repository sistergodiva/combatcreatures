package se.snrn.combatcreatures.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.AttackResolver;
import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.*;
import se.snrn.combatcreatures.items.consumable.ConsumableFactory;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.userinterface.GameLog;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Creature implements Updatable, Renderable, Mapped, Ai, Living, Fighter {
    private int xp;
    private String description;
    private String spriteString;
    private String deadSpriteString;
    private String name;
    private Tile tile;
    private TileMap tileMap;
    private Sprite sprite;
    private AiCore aiCore;
    private boolean finished;
    private int health;
    private boolean alive;
    private Stats stats;
    private int cost;
    private Sprite deadSprite;
    private boolean active;
    private int floor;
    MapManager mapManager;


    public Creature(Tile tile, MapManager mapManager, JsonValue stats, JsonValue appearance, AiCore aiCore) {
        this.mapManager = mapManager;
        this.tileMap = mapManager.getMap();
        this.floor = mapManager.getFloor();
        this.stats = new Stats(stats.getInt(0), stats.getInt(1), stats.getInt(2), stats.getInt(3), stats.getInt(4), stats.getInt(5));
        this.tile = tile;
        this.tile.setMapped(this);
        this.name = appearance.getString(0);
        this.description = appearance.getString(1);
        this.spriteString = appearance.getString(2);
        this.deadSpriteString = appearance.getString(3);
        this.xp = stats.getInt(6);
        this.aiCore = aiCore;
        alive = true;
        health = 3;
        sprite = ResourceManager.getCreatureSpriteFromString(spriteString);
        cost = 10;
        deadSprite = ResourceManager.getCreatureSpriteFromString(deadSpriteString);
        active = false;


    }

    @Override
    public void update(float delta) {
        if (health <= 0 && alive) {
            GameLog.addMessage(this.name + " died.");
            die();

        }
        sprite.setPosition(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
    }

    @Override
    public void render(Batch batch) {
        if (tile.isVisible()) {
            sprite.draw(batch);
        }
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
    }

    @Override
    public boolean move(Direction direction) {
        Tile newTile = tileMap.getTile(tile.getX() + direction.getX(), tile.getY() + direction.getY());
        if (newTile != null && newTile.getType() == TileType.FLOOR) {
            if (newTile.getMapped() instanceof Player) {
                Player player = (Player) newTile.getMapped();
                int damage = AttackResolver.resolveNormalAttack(this, player);
                player.takeDamage(damage);
                GameLog.addMessage("You took " +damage+ " damage");
                return true;
            }
            changeTile(newTile);
            return true;
        }

        return false;
    }


    @Override
    public void setAi(AiCore aiCore) {
        this.aiCore = aiCore;
    }

    @Override
    public AiCore getAiCore() {
        return aiCore;
    }

    @Override
    public void act(Player player) {
        if(player.getFloor() == floor) {
            aiCore.act(this, player);
            finished = true;
        }
        finished = true;
    }

    public boolean isFinished() {
        return finished;
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
        tile.setMapped(null);
        setAlive(false);
        tile.addItem(ConsumableFactory.getNewConsumable(RandomNumber.range(0,7)));

        GameLog.addMessage(name +" died.");
    }

    @Override
    public void tick() {

    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Sprite getDeadSprite() {
        return deadSprite;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getFloor() {
        return floor;
    }

    public int getXp() {
        return xp;
    }
}

