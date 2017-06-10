package se.snrn.combatcreatures.entities.enemies;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.TurnManager;
import se.snrn.combatcreatures.TurnType;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import java.util.ArrayList;

import static se.snrn.combatcreatures.MissionScreen.turnManager;

public class CreatureManager implements Updatable, Renderable {

    private ArrayList<Creature> creatures;
    private ArrayList<Creature> creaturesToRemove;
    private ArrayList<Creature> creaturesToAdd;
    private ArrayList<Corpse> corpses;
    private ArrayList<Corpse> corpsesToAdd;

    public Player getPlayer() {
        return player;
    }

    private Player player;

    public CreatureManager() {
        this.creatures = new ArrayList<>();
        this.creaturesToRemove = new ArrayList<>();
        this.creaturesToAdd = new ArrayList<>();
        this.corpses = new ArrayList<>();
        this.corpsesToAdd = new ArrayList<>();

    }

    public void addCreature(Creature creature) {
        creaturesToAdd.add(creature);
    }

    public void removeCreature(Creature creature) {
        creaturesToRemove.add(creature);
    }

    @Override
    public void update(float delta) {
        if (!corpsesToAdd.isEmpty()) {
            corpses.addAll(corpsesToAdd);
            corpsesToAdd.clear();
        }
        if (!creaturesToAdd.isEmpty()) {
            creatures.addAll(creaturesToAdd);
            creaturesToAdd.clear();
        }
        if (!creaturesToRemove.isEmpty()) {
            creatures.removeAll(creaturesToRemove);
            creaturesToRemove.clear();
        }

        for (Creature creature : creatures) {
            creature.update(delta);
        }
        for (Corpse corpse : corpses) {
            corpse.update(delta);
        }
        int finishedCreatures = 0;
        for (Creature creature : creatures) {
            if (!creature.isAlive()) {
                player.addXp(creature.getXp());
                addCorpse(new Corpse(creature));
                removeCreature(creature);
            } else if (turnManager.getTurnType() == TurnType.ENEMY) {
                if (creature.isFinished() || !creature.isActive()) {
                    finishedCreatures++;
                } else {
                    if (creature.isActive()) {

                        if (MissionScreen.visualEffectManager.isDone()) {
                            creature.act(player);
                        }

                    }
                }
            }
        }

        if (finishedCreatures == creatures.size() && turnManager.getTurnType() == TurnType.ENEMY) {
            turnManager.endEnemyTurn();
        }
    }

    @Override
    public void render(Batch batch) {
        for (Corpse corpse : corpses) {
            if (corpse.getFloor() == player.getFloor()) {
                corpse.render(batch);
            }
        }
        for (Creature creature : creatures) {
            if (creature.getFloor() == player.getFloor()) {
                creature.render(batch);
            }
        }

    }

    public void setAllUnfinished() {
        for (Creature creature : creatures) {
            if (creature.isAlive()) {
                creature.setFinished(false);
            }
        }
    }

    public void addCorpse(Corpse corpse) {
        corpsesToAdd.add(corpse);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }
}
