package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Input;
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

        tileMap = player.getMap();
        currentInputState = new DefaultInputState(player);
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


    public void handleInput(int input) {


        newInputState = currentInputState.handleInput(input);
        if (newInputState != null) {
            newInputState.enter();
            currentInputState.exit();
            currentInputState = newInputState;
        }

    }

    public void jump() {
        currentInputState = new JumpInputState(player);
    }
}
