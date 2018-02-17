package se.snrn.combatcreatures.entities.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.AttackResolver;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.Stats;
import se.snrn.combatcreatures.entities.effects.Effect;
import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.interfaces.*;
import se.snrn.combatcreatures.items.Equipment.Stat;
import se.snrn.combatcreatures.map.Direction;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.los.LineOfSight;
import se.snrn.combatcreatures.map.trainstops.TileMap;
import se.snrn.combatcreatures.userinterface.GameLog;
import se.snrn.combatcreatures.userinterface.inventory.Inventory;
import se.snrn.combatcreatures.visualeffects.AttackEffect;
import se.snrn.combatcreatures.visualeffects.DamageNumberEffect;
import se.snrn.combatcreatures.visualeffects.MoveEffect;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;
import static se.snrn.combatcreatures.MissionScreen.turnManager;

public class Player implements Updatable, Renderable, Mapped, Living, Fighter {


    private int skillPoints;
    private int hp;
    private Tile tile;
    private Sprite sprite;
    private boolean alive;
    private TileMap tileMap;
    private Stats stats;
    private PlayerEquipment playerEquipment;
    private ArrayList<Effect> effects;
    private Inventory inventory;
    private int level;
    private int score;
    private int xp;


    public Player(Tile tile, TileMap tileMap, Stats stats) {
        this.tile = tile;
        this.tileMap = tileMap;
        this.stats = stats;
        sprite = ResourceManager.player;
        alive = true;
        playerEquipment = new PlayerEquipment(this);
        inventory = new Inventory();
        effects = new ArrayList<>();
        level = 0;
        LineOfSight.getLineOfSight(tile, tileMap.getTiles());

        skillPoints = 0;

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
        if (hp <= 0 && alive) {
            die();
        }
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
        playerEquipment.render(batch);
    }


    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public TileMap getMap() {
        return null;
    }

    @Override
    public void changeTile(Tile tile) {
        this.tile.setMapped(null);
        tile.setMapped(this);
        this.tile = tile;
        tile.stepOn(this);
        LineOfSight.getLineOfSight(tile, tileMap.getTiles());
    }

    @Override
    public boolean move(Direction direction) {
        if (MissionScreen.visualEffectManager.isDone()) {
            Tile newTile = tileMap.getTile(tile.getX() + direction.getX(), tile.getY() + direction.getY());
            if (newTile != null && newTile.getType().isWalkable()) {
                if (newTile.getMapped() instanceof Creature) {
                    MissionScreen.visualEffectManager.addEffect(new AttackEffect(tile, newTile, this));
                    Creature creature = (Creature) newTile.getMapped();
                    int damage = AttackResolver.resolveNormalAttack(this, creature);
                    MissionScreen.visualEffectManager.addEffect(new DamageNumberEffect(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE, damage));
//                    MissionScreen.visualEffectManager.addEffect(new DiceEffect(tile.getX()*TILE_SIZE,tile.getY()*TILE_SIZE));
//                    MissionScreen.visualEffectManager.addEffect(new DiceEffect((tile.getX()+1)*TILE_SIZE,tile.getY()*TILE_SIZE));
                    creature.takeDamage(damage);
                    GameLog.addMessage(creature.getName() + " took " + damage + " damage");
                    turnManager.endPlayerTurn();
                    return true;
                }
                MissionScreen.visualEffectManager.addEffect(new MoveEffect(tile, newTile, this));
                changeTile(newTile);
                turnManager.endPlayerTurn();
                return true;
            }
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
        hp -= damage;
    }

    @Override
    public void healDamage(int damage) {
        hp += damage;
    }

    @Override
    public void die() {
        alive = false;
    }

    @Override
    public void tick() {

    }

    @Override
    public Stats getStats() {
        return stats;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getHp() {
        return stats.getStatFromEnum(Stat.hp);
    }

    public int getEp() {
        return stats.getStatFromEnum(Stat.ep);
    }

    public int getMp() {
        return stats.getStatFromEnum(Stat.mp);
    }


    public int getLevel() {
        return level;
    }

    public void addXp(int xp) {
        this.xp += xp;

        while (Experience.getLevel(this.xp) > level) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        skillPoints++;
        System.out.println("leveled to level " + level);
    }


    public int getSkillPoints() {
        return skillPoints;
    }

    public int getXp() {
        return xp;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public PlayerEquipment getPlayerEquipment() {
        return playerEquipment;
    }
}

