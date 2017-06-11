package se.snrn.combatcreatures.items;


import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.items.Equipment.Equipment;
import se.snrn.combatcreatures.items.Equipment.EquipmentSlot;
import se.snrn.combatcreatures.items.Equipment.Prefix;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

public class LootGenerator {

    public static Item generateMonsterLoot(TrainStopMap trainStopMap, Creature creature){
        int lootValue = creature.getXp();
        return null;
    }

    public static Equipment createRandomEquipment(){
        EquipmentSlot slot = EquipmentSlot.values()[RandomNumber.range(0,EquipmentSlot.values().length)];
        Prefix prefix = Prefix.values()[RandomNumber.range(0,Prefix.values().length)];
        System.out.println(prefix);
        System.out.println(slot);
        return null;
    }

}
