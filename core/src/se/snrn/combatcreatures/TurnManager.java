package se.snrn.combatcreatures;


import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.enemies.CreatureManager;
import se.snrn.combatcreatures.userinterface.GameLog;

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
        GameLog.logs.add("Ending player turn");
        turnType = TurnType.ENEMY;
    }

    public void endEnemyTurn() {
        advanceTurn();
        turnType = TurnType.PLAYER;
    }

    public void advanceTurn(){
        turn++;
        GameLog.logs.add("Next turn");
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
