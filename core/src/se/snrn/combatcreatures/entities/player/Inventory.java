package se.snrn.combatcreatures.entities.player;


import se.snrn.combatcreatures.items.Item;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;
    private int money;


    public Inventory() {
        items = new ArrayList<>();
        money = 0;

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
}
