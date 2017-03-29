package se.snrn.combatcreatures.items.consumable;

import se.snrn.combatcreatures.items.consumable.consumableeffect.ConsumableEffect;
import se.snrn.combatcreatures.items.consumable.consumableeffect.ConsumableEffectFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ConsumableFactory {

    public static HashMap<Integer, ConsumableEffect> consumableEffectTable;


    public ConsumableFactory() {

        ArrayList<ConsumableEffect> effects = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            effects.add(ConsumableEffectFactory.getConsumableEffect(i));
        }

        Collections.shuffle(effects);

        consumableEffectTable = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            consumableEffectTable.put(i, effects.get(i));
        }


    }

    public static Consumable getNewConsumable(int id) {
        Consumable consumable = ConsumableAppearanceFactory.getConsumableAppearance(id);
        consumable.setConsumableEffect(consumableEffectTable.get(id));
        System.out.println(consumable);
        return consumable;
    }
}
