package se.snrn.combatcreatures.items;

import se.snrn.combatcreatures.effects.PermanentBuffEffect;

public class Equipment implements Item {

    private String name;
    private String description;
    private PermanentBuffEffect permanentBuffEffect;
    private Ability ability;



    public Equipment(String name, String description, PermanentBuffEffect permanentBuffEffect, Ability ability) {
        this.name = name;
        this.description = description;
        this.permanentBuffEffect = permanentBuffEffect;
        this.ability = ability;
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
