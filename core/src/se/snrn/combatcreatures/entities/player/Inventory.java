package se.snrn.combatcreatures.entities.player;


import se.snrn.combatcreatures.items.Item;
import se.snrn.combatcreatures.items.consumable.ConsumableFactory;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;
    private int money;


    public Inventory() {
        items = new ArrayList<>();
        money = 0;

        addItem(ConsumableFactory.getNewConsumable(0));
        addItem(ConsumableFactory.getNewConsumable(1));

    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
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

    @Override

    public String toString() {
        return "Inventory{" +
                "items=" + items +
                ", money=" + money +
                '}';
    }

    public String getInventoryString(){
        String inventoryContent = "Inventory:\n";

        for (Item item : items) {
            inventoryContent += item.getName()+"\n";
        }
        return inventoryContent;
    }
}


