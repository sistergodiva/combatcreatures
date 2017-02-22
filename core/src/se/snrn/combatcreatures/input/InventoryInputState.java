package se.snrn.combatcreatures.input;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;

public class InventoryInputState implements InputState {

    private Player player;

    public InventoryInputState(Player player) {
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
        System.out.println(player.getInventory().getInventoryString());
    }

    @Override
    public void exit() {

    }

    @Override
    public InputState handleInput(int input) {
        if(input == 8){
            input = 1;
        }
        System.out.println(player.getInventory().getItems().get(input));

        return null;
    }
}
