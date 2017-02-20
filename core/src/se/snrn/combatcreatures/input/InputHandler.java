package se.snrn.combatcreatures.input;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.Player;

public class InputHandler implements InputProcessor {

    private Player player;
    private InputStateMachine inputStateMachine;
    private Vector3 mousePosition;

    public InputHandler(InputStateMachine inputStateMachine, Player player) {
        this.inputStateMachine = inputStateMachine;

        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        inputStateMachine.handleInput(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
