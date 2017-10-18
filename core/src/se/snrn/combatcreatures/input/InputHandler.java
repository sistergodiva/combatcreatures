package se.snrn.combatcreatures.input;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.visualeffects.SlashAnimation;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;
import static se.snrn.combatcreatures.map.Direction.*;

public class InputHandler implements InputProcessor {

    private Player player;
    private OrthographicCamera orthographicCamera;
    private InputStateMachine inputStateMachine;

    public InputHandler(InputStateMachine inputStateMachine, Player player, OrthographicCamera orthographicCamera) {
        this.inputStateMachine = inputStateMachine;

        this.player = player;
        this.orthographicCamera = orthographicCamera;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.NUMPAD_1) {
            orthographicCamera.zoom += 0.05;
        }
        if (keycode == Input.Keys.NUMPAD_3) {
            orthographicCamera.zoom -= 0.05;

        }

        if (keycode == Input.Keys.NUMPAD_4) {
            MissionScreen.visualEffectManager.addEffect(new SlashAnimation(player.getSprite().getX(), player.getSprite().getY(), WEST));
        }
        if (keycode == Input.Keys.NUMPAD_8) {
            MissionScreen.visualEffectManager.addEffect(new SlashAnimation(player.getSprite().getX(), player.getSprite().getY(), NORTH));

        }
        if (keycode == Input.Keys.NUMPAD_6) {
            MissionScreen.visualEffectManager.addEffect(new SlashAnimation(player.getSprite().getX(), player.getSprite().getY(), EAST));

        }
        if (keycode == Input.Keys.NUMPAD_2) {
            MissionScreen.visualEffectManager.addEffect(new SlashAnimation(player.getSprite().getX(), player.getSprite().getY(), SOUTH));

        }

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
