package se.snrn.combatcreatures.entities.effects;


import se.snrn.combatcreatures.entities.Stats;

public class StatBuffEffect implements Effect {

    private Stats stats;
    private String name;
    private String description;
    private int duration;

    public StatBuffEffect(Stats stats, String name, String description, int duration) {

        this.stats = stats;
        this.name = name;
        this.description = description;
        this.duration = duration;
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
        return duration;
    }

    @Override
    public void tick() {
        duration--;
    }
}
