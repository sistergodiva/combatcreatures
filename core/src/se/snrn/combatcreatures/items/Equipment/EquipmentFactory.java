package se.snrn.combatcreatures.items.Equipment;

import se.snrn.combatcreatures.entities.Stats;
import se.snrn.combatcreatures.entities.effects.PermanentBuffEffect;

public class EquipmentFactory {

    public static Equipment getEquipment() {
        return new Equipment(
                "Hat", "A fancy hat",
                new PermanentBuffEffect(
                        new Stats(1, 0, 0, 0, 0, 0),
                        "Hat-buff",
                        "This hat makes you look cool."
                ), null, EquipmentSlot.HEAD, null
        );
    }

}
