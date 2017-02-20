package se.snrn.combatcreatures.input;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.map.Tile;

public class DefaultInputState implements InputState{
    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public InputState handleInput(Tile tile) {
        System.out.println(tile.getMapped());
        return null;
    }
}
