package se.snrn.combatcreatures.input;


import com.badlogic.gdx.InputProcessor;
import se.snrn.combatcreatures.entities.player.Player;

public class InputHandler implements InputProcessor {

    private Player player;
    private InputStateMachine inputStateMachine;

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
