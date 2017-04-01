package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.AttackResolver;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Fighter;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.TileType;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;
import static se.snrn.combatcreatures.MissionScreen.turnManager;


public class RangedInputState implements InputState {


    private TileMap tileMap;
    private MapManager mapManager;
    private ArrayList<Mapped> allowedTargets;
    private Player player;
    private boolean done;
    private Mapped target;

    public RangedInputState(Player player, MapManager mapManager) {
        this.player = player;

        tileMap = player.getMap();
        this.mapManager = mapManager;

        allowedTargets = new ArrayList<>();

        for (Tile tile : mapManager.getVision()) {
            if (tile.getMapped() instanceof Fighter) {
                allowedTargets.add((Mapped) tile.getMapped());
            }
        }

        target = allowedTargets.get(0);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

        ResourceManager.cursor.setPosition(target.getTile().getX() * TILE_SIZE, target.getTile().getY() * TILE_SIZE);
        ResourceManager.cursor.draw(batch);

        for (Mapped allowedTarget : allowedTargets) {

            ResourceManager.target.setPosition(allowedTarget.getTile().getX() * TILE_SIZE, allowedTarget.getTile().getY() * TILE_SIZE);
            ResourceManager.target.draw(batch);

        }
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public InputState handleInput(int input) {
        switch (input) {
            case Input.Keys.ENTER: {
                AttackResolver.resolveRangedAttack(player, target);
                break;
            }
            case Input.Keys.S: {

                break;
            }
            case Input.Keys.A: {

                break;
            }
            default:
                return null;
        }
        if (done) {
            turnManager.endPlayerTurn();
            return new DefaultInputState(player, mapManager);
        }
        return null;
    }
}
