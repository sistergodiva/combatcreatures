package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.userinterface.inventory.Inventory;
import se.snrn.combatcreatures.items.Item;

public class InventoryInputState implements InputState {

    private Inventory inventory;
    private MapManager mapManager;
    private Player player;

    public InventoryInputState(Player player, MapManager mapManager) {
        this.player = player;
        inventory = player.getInventory();
        this.mapManager = mapManager;
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
        inventory.setOpen(true);
    }

    @Override
    public void exit() {

    }

    @Override
    public InputState handleInput(int input) {

        switch (input) {
            case Input.Keys.W: {
                inventory.moveCursorDown();
                break;
            }
            case Input.Keys.S: {
                inventory.moveCursorUp();
                break;
            }

            case Input.Keys.ENTER: {
                System.out.println(inventory.getItemAtCursor());
                inventory.setOpen(false);
                return new DefaultInputState(player, mapManager);

            }
            case Input.Keys.O: {
                Item item = inventory.getItemAtCursor();
                inventory.removeItemAtCursor();
                if(item != null) {
                    player.getTile().addItem(item);
                }
                inventory.setOpen(false);
                return new DefaultInputState(player, mapManager);

            }
        }

        return null;
    }
}
