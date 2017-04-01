package se.snrn.combatcreatures.input;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.map.TileMap;


public class InputStateMachine implements Updatable, Renderable {

    private InputState currentInputState;
    private InputState newInputState;
    private TileMap tileMap;
    private MapManager mapManager;
    private Player player;


    public InputStateMachine(Player player, MapManager mapManager) {

        tileMap = player.getMap();
        this.mapManager = mapManager;
        currentInputState = new DefaultInputState(player, mapManager);
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
}
