package se.snrn.combatcreatures.interfaces;

import se.snrn.combatcreatures.entities.Item;

import java.util.ArrayList;

public interface Container {
    ArrayList<Item> getLoot();
}
