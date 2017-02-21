package se.snrn.combatcreatures.items;

import se.snrn.combatcreatures.effects.PermanentBuffEffect;

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

    public PermanentBuffEffect getPermanentBuffEffect(){
        return permanentBuffEffect;
    }
    public Ability getAbility() {
        return ability;
    }

}
