package se.snrn.combatcreatures.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.CombatCreatures;
import se.snrn.combatcreatures.TurnType;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import java.util.ArrayList;

import static se.snrn.combatcreatures.MissionScreen.turnManager;

public class CreatureManager implements Updatable, Renderable {

    ArrayList<Creature> creatures;
    ArrayList<Creature> creaturesToRemove;
    ArrayList<Creature> creaturesToAdd;
    ArrayList<Corpse> corpses;
    ArrayList<Corpse> corpsesToAdd;
    private Player player;

    public CreatureManager(Player player) {
        this.player = player;
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
            if (turnManager.getTurnType() == TurnType.ENEMY) {
                if (creature.isFinished()) {

                    finishedCreatures++;
                } else {
                    creature.act(player);
                }
            }
        }
        if (finishedCreatures == creatures.size()) {
            turnManager.endEnemyTurn();
        }
    }

    @Override
    public void render(Batch batch) {
        for (Creature creature : creatures) {
            creature.render(batch);
        }
        for (Corpse corpse : corpses) {
            corpse.render(batch);
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
}
