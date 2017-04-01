package se.snrn.combatcreatures;


import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.CreatureManager;

public class TurnManager {

    private static int turn;
    private TurnType turnType;
    private CreatureManager creatureManager;

    public TurnManager(CreatureManager creatureManager) {
        this.creatureManager = creatureManager;
        turn = 0;
        turnType = TurnType.PLAYER;
    }

    public void endPlayerTurn() {
        creatureManager.setAllUnfinished();
        turnType = TurnType.ENEMY;
    }

    public void endEnemyTurn() {
        advanceTurn();
        turnType = TurnType.PLAYER;
    }

    public void advanceTurn(){
        turn++;
        System.out.println("Next turn");
        creatureManager.getPlayer().tick();
        for (Creature creature : creatureManager.getCreatures()
                ) {
            creature.tick();
        }
    }

    public static int getTurn() {
        return turn;
    }

    public TurnType getTurnType() {
        return turnType;
    }

}
