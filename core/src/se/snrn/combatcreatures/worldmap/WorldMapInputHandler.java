package se.snrn.combatcreatures.worldmap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import se.snrn.combatcreatures.input.InputHandler;
import se.snrn.combatcreatures.worldmap.PlayerTrain;


public class WorldMapInputHandler implements InputProcessor {
    private PlayerTrain playerTrain;

    public WorldMapInputHandler(PlayerTrain playerTrain) {

        this.playerTrain = playerTrain;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE) {
            playerTrain.advance();
        }
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
