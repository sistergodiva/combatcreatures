package se.snrn.combatcreatures.userinterface.inventory;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.items.Item;
import se.snrn.combatcreatures.items.consumable.ConsumableFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Inventory implements Renderable {

    private InventoryCursor cursor;
    private int margin;
    private int inventoryHeight;
    private int inventoryBottom;
    private int inventoryWidth;
    private float x;
    private float y;
    private ArrayList<Item> items;
    private int money;
    private boolean open;
    private int right;
    private int top;
    private int cursorPos;

    ArrayList<Slot> slots;


    public Inventory() {
        margin = 8;
        slots = new ArrayList<>();
        items = new ArrayList<>();
        money = 0;
        inventoryWidth = 192;
        inventoryBottom = 0;
        inventoryHeight = Gdx.graphics.getHeight()-192+margin;
        right = Gdx.graphics.getWidth() - inventoryWidth;
        top = inventoryBottom + inventoryHeight - TILE_SIZE;




        x = 0;
        y = 0;

        createSlots();

        cursor = new InventoryCursor();


        cursorPos = 0;
        cursor.setSlot(slots.get(0));

        addItem(ConsumableFactory.getNewConsumable(0));
        addItem(ConsumableFactory.getNewConsumable(1));
        addItem(ConsumableFactory.getNewConsumable(1));
        addItem(ConsumableFactory.getNewConsumable(1));
        addItem(ConsumableFactory.getNewConsumable(1));
        addItem(ConsumableFactory.getNewConsumable(1));
        addItem(ConsumableFactory.getNewConsumable(1));

    }


    public void createSlots() {
        for (int i = 0; i < 9; i++) {
            x = right + margin;
            y = top - (i * TILE_SIZE) - (i * margin);
            slots.add(new Slot(x, y));
        }
    }

    public void addItem(Item item) {
        for (Slot slot : slots
                ) {
            if (slot.getItem() == null) {
                slot.setItem(item);
                break;
            }
        }
    }

    public Item removeItem(int slot) {
        return slots.get(slot).getItem();
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void removeMoney(int money) {
        this.money -= money;
    }

    public ArrayList<Item> getItems() {
        return items;
    }


    public String getInventoryString() {
        String inventoryContent = "Inventory:\n";

        for (Slot slot : slots) {
            inventoryContent += slot.getItemName() + "\n";
        }
        return inventoryContent;
    }

    public void sortInventory() {
        Collections.sort(slots,Comparator.comparing(Slot::getItemName));
    }

    @Override
    public void render(Batch batch) {
        ResourceManager.uiNinePatch.draw(batch, x-margin,y, inventoryWidth, inventoryHeight-y);
        sortInventory();

        for (int i = 0; i < 9; i++) {
            x = right + margin;
            y = top - (i * TILE_SIZE) - (i * margin)-margin;
            slots.get(i).setPosition(x,y);
            slots.get(i).render(batch);
        }
        cursor.render(batch);
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void moveCursorUp() {
        if (cursorPos == slots.size() - 1) {
            cursorPos = 0;
        } else {
            cursorPos++;
        }
        cursor.setSlot(slots.get(cursorPos));
    }

    public void moveCursorDown() {
        if (cursorPos == 0) {
            cursorPos = slots.size() - 1;
        } else {
            cursorPos--;
        }
        cursor.setSlot(slots.get(cursorPos));
    }

    public Item getItemAtCursor() {
        return cursor.getSlot().getItem();
    }

    public void removeItemAtCursor() {
        cursor.getSlot().removeItem();
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}


