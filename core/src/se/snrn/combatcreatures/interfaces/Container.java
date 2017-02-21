package se.snrn.combatcreatures.interfaces;

import se.snrn.combatcreatures.items.Item;

import java.util.ArrayList;

public interface Container {
    ArrayList<Item> getLoot();
}
