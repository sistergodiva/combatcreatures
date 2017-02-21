package se.snrn.combatcreatures.items;


import se.snrn.combatcreatures.effects.Effect;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsumableFactory {

    ArrayList<Consumable> consumables;
    ArrayList<Effect> effects;

    HashMap<Consumable, Effect> identity;

    public ConsumableFactory() {
        consumables = new ArrayList<>();
        effects = new ArrayList<>();
        identity = new HashMap<>();
    }

    public Effect getEffect(Consumable consumable){
        return identity.get(consumable);
    }
}
