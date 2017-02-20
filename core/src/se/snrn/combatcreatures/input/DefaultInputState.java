package se.snrn.combatcreatures.input;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.Player;

public class DefaultInputState implements InputState{
    private Player player;

    public DefaultInputState(Player player) {
        this.player = player;
    }

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
    public InputState handleInput(int input) {

        switch (input){
            case Input.Keys.W: {
                player.move(Direction.NORTH);
                break;
            }
            case Input.Keys.D: {
                player.move(Direction.EAST);
                break;
            }
            case Input.Keys.S: {
                player.move(Direction.SOUTH);
                break;
            }
            case Input.Keys.A: {
                player.move(Direction.WEST);
                break;
            }
            case Input.Keys.J: {
                return new JumpInputState(player);
            }
            case Input.Keys.K: {
                return new DigInputState(player);
            }

        }
        return null;
    }
}
