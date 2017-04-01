package se.snrn.combatcreatures.entities.effects;

public interface Effect {


    String getName();
    String getDescription();
    int getRemainingTurns();
    void tick();

}
