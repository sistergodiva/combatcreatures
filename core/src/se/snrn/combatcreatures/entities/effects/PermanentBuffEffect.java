package se.snrn.combatcreatures.entities.effects;

import se.snrn.combatcreatures.entities.Stats;

public class PermanentBuffEffect implements Effect {

    private Stats stats;
    private String name;
    private String description;

    public PermanentBuffEffect(Stats stats, String name, String description) {

        this.stats = stats;
        this.name = name;
        this.description = description;

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
    public int getRemainingTurns() {
        return -1;
    }

    @Override
    public void tick() {

    }
}
