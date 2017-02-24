package se.snrn.combatcreatures.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.Renderable;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class InventoryCursor implements Renderable {


    private Slot slot;



    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    @Override
    public void render(Batch batch) {
        ResourceManager.cursor.setPosition(slot.getX()-TILE_SIZE, slot.getY());
        ResourceManager.cursor.draw(batch);
    }

    public Slot getSlot() {
        return slot;
    }
}
