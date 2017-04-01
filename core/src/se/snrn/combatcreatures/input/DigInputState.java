package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.TileType;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;
import static se.snrn.combatcreatures.map.TileType.FLOOR;

public class DigInputState implements InputState {


    private Player player;
    private ArrayList<Tile> allowedTargets;
    TileMap tileMap;
    private MapManager mapManager;

    public DigInputState(Player player, MapManager mapManager) {
        this.player = player;
        tileMap = player.getMap();
        this.mapManager = mapManager;

        allowedTargets = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            allowedTargets.add(tileMap.getTile(player.getTile().getX() + (direction.getX()), player.getTile().getY() + (direction.getY())));
        }
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        for (Tile allowedTarget : allowedTargets) {
            if (allowedTarget != null && allowedTarget.getType() == TileType.WALL) {
                ResourceManager.target.setPosition(allowedTarget.getX() * TILE_SIZE, allowedTarget.getY() * TILE_SIZE);
                ResourceManager.target.draw(batch);
            }
        }
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    public InputState defaultInputState() {
        return new DefaultInputState(player, mapManager);
    }


    @Override
    public InputState handleInput(int input) {
        Tile tile = null;
        switch (input) {
            case Input.Keys.W: {
                tile = player.getMap().getTileAtDirection(player.getTile(), Direction.NORTH);
                break;
            }
            case Input.Keys.D: {
                tile = player.getMap().getTileAtDirection(player.getTile(), Direction.EAST);
                break;
            }
            case Input.Keys.S: {
                tile = player.getMap().getTileAtDirection(player.getTile(), Direction.SOUTH);
                break;
            }
            case Input.Keys.A: {
                tile = player.getMap().getTileAtDirection(player.getTile(), Direction.WEST);
                break;
            }

        }
        if (tile != null) {
            tile.setType(FLOOR);
        }
        return defaultInputState();
    }
}
