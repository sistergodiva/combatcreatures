package se.snrn.combatcreatures.items.Equipment;

import se.snrn.combatcreatures.effects.PermanentBuffEffect;
import se.snrn.combatcreatures.items.Ability;
import se.snrn.combatcreatures.items.Item;

public class Equipment implements Item {

    private String name;
    private String description;
    private PermanentBuffEffect permanentBuffEffect;
    private Ability ability;
    private EquipmentSlot equipmentSlot;



    public Equipment(String name, String description, PermanentBuffEffect permanentBuffEffect, Ability ability, EquipmentSlot equipmentSlot) {
        this.name = name;
        this.description = description;
        this.permanentBuffEffect = permanentBuffEffect;
        this.ability = ability;
        this.equipmentSlot = equipmentSlot;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSpriteString() {
        return null;
    }

    public PermanentBuffEffect getPermanentBuffEffect(){
        return permanentBuffEffect;
    }
    public Ability getAbility() {
        return ability;
    }

}
