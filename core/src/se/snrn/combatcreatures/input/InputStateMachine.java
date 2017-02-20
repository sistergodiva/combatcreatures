package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import se.snrn.combatcreatures.entities.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;


public class InputStateMachine implements Updatable, Renderable {

    private InputState currentInputState;
    private InputState newInputState;
    private TileMap tileMap;
    private Player player;


    public InputStateMachine(Player player) {

        currentInputState = new JumpInputState(player);
        tileMap = player.getMap();
        this.player = player;
    }




    @Override
    public void update(float delta) {
        if (currentInputState != null) {
            currentInputState.update(delta);
        }
    }

    @Override
    public void render(Batch batch) {
        if (currentInputState != null) {
            currentInputState.render(batch);
        }
    }


    public void handleInput(Vector3 mousePosition) {
        Tile tile = tileMap.getTile((int)mousePosition.x / TILE_SIZE, (int)mousePosition.y / TILE_SIZE);

        if (tile != null) {
            newInputState = currentInputState.handleInput(tile);
            if (newInputState != null) {
                newInputState.enter();
                currentInputState.exit();
                currentInputState = newInputState;
            }
        }
    }

    public void jump() {
        currentInputState = new JumpInputState(player);
    }
}
