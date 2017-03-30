package se.snrn.combatcreatures.userinterface.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.items.Item;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Slot implements Renderable {

    private float x;
    private float y;


    private Item item;

    public Slot(float x, float y) {

        this.x = x;
        this.y = y;

    }

    @Override
    public void render(Batch batch) {
        if (item != null) {
            item.setPosition(x, y);
            item.render(batch);
            ResourceManager.font.draw(batch, item.getName(), x+TILE_SIZE, y+TILE_SIZE/2);
        }

    }


    public Item getItem() {
        if (item != null) {
            return item;
        }
        System.out.println("No item in that slot");
        return null;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getItemName() {
        if (item != null) {
            return item.getName();
        }
        else return "empty";
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void removeItem() {
        item = null;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
