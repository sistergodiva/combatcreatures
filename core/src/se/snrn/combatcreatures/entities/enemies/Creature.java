package se.snrn.combatcreatures.entities.enemies;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.AttackResolver;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.items.Equipment.Stat;
import se.snrn.combatcreatures.map.Direction;
import se.snrn.combatcreatures.entities.Stats;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.*;
import se.snrn.combatcreatures.items.consumable.ConsumableFactory;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.trainstops.TileMap;
import se.snrn.combatcreatures.userinterface.GameLog;
import se.snrn.combatcreatures.visualeffects.AttackEffect;
import se.snrn.combatcreatures.visualeffects.MoveEffect;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Creature implements Updatable, Renderable, Mapped, Ai, Living, Fighter {
    private Stats stats;
    private int xp;
    private String description;
    private String spriteString;
    private String deadSpriteString;
    private String name;
    private Tile tile;
    private Sprite sprite;
    private AiCore aiCore;
    private boolean finished;
    private int health;
    private boolean alive;
    private TileMap tileMap;
    private int cost;
    private Sprite deadSprite;
    private boolean active;
    private PointLight light;


    public Creature(Tile tile, TileMap tileMap, JsonValue definition, AiCore aiCore) {
        this.tileMap = tileMap;
        this.stats = new Stats(definition.get("stats"));
        this.tile = tile;
        this.tile.setMapped(this);
        this.name = definition.getString(0);
        this.description = definition.getString(1);
        this.spriteString = definition.getString(2);
        this.deadSpriteString = definition.getString(3);

        this.xp = definition.get("stats").getInt("xp");
        this.aiCore = aiCore;
        alive = true;
        health = this.stats.getStatFromEnum(Stat.hp);
        sprite = ResourceManager.getCreatureSpriteFromString(spriteString);
        cost = 10;
        deadSprite = ResourceManager.getCreatureSpriteFromString(deadSpriteString);
        active = false;
        light = MissionScreen.box2DWorld.newLight(50, Color.LIGHT_GRAY, 50, tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
    }

    @Override
    public void update(float delta) {
        if (health <= 0 && alive) {
            GameLog.addMessage(this.name + " died.");
            die();

        }
        light.setPosition(sprite.getX() + TILE_SIZE / 2, sprite.getY() + TILE_SIZE / 2);

        if (MissionScreen.visualEffectManager.isDone()) {
            sprite.setPosition(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
        }
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
        if (MissionScreen.visualEffectManager.isDone()) {
            Tile newTile = tileMap.getTile(tile.getX() + direction.getX(), tile.getY() + direction.getY());
            if (newTile != null && newTile.getType() == TileType.FLOOR) {
                if (newTile.getMapped() instanceof Player) {
                    MissionScreen.visualEffectManager.addEffect(new AttackEffect(tile, newTile, this));
                    Player player = (Player) newTile.getMapped();
                    int damage = AttackResolver.resolveNormalAttack(this, player);
                    player.takeDamage(damage);
                    GameLog.addMessage("You took " + damage + " damage");
                    return true;
                }
                MissionScreen.visualEffectManager.addEffect(new MoveEffect(tile, newTile, this));
                changeTile(newTile);
                return true;
            }
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
        aiCore.act(this, player);
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
    public int getHp() {
        return health;
    }

    @Override
    public void die() {
        tile.setMapped(null);
        setAlive(false);
        tile.addItem(ConsumableFactory.getNewConsumable(RandomNumber.range(0, 7)));

        GameLog.addMessage(name + " died.");
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

    public int getXp() {
        return xp;
    }

    public Sprite getSprite() {
        return sprite;
    }
}

