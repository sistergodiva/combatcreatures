package se.snrn.combatcreatures.items.Equipment;

import se.snrn.combatcreatures.entities.Stats;
import se.snrn.combatcreatures.entities.effects.PermanentBuffEffect;

public class EquipmentFactory {

    public static Equipment getEquipment() {
        return new Equipment(
                "Hat", "A fancy hat",
                new PermanentBuffEffect(
                        new Stats(),
                        "Hat-buff",
                        "This hat makes you look cool."
                ), null, EquipmentSlot.HEAD
        );
    }

}
