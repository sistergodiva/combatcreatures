package se.snrn.combatcreatures.effects;

public interface Effect {


    String getName();
    String getDescription();
    int getRemainingTurns();
    void tick();

}
