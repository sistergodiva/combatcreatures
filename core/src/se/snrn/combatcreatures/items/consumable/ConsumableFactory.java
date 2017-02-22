package se.snrn.combatcreatures.items.consumable;

import se.snrn.combatcreatures.items.consumable.consumableeffect.ConsumableEffectFactory;

public class ConsumableFactory {

    public static Consumable getNewConsumable(int id){
        Consumable consumable = ConsumableAppearanceFactory.getConsumableAppearance(id);
        consumable.setConsumableEffect(ConsumableEffectFactory.getConsumableEffect(id));
        return consumable;
    }
}
