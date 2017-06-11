package se.snrn.combatcreatures.entities.enemies;

import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import java.util.ArrayList;
import java.util.Collections;

public class EnemySpawner {

    public void spawnTargetDummies(CreatureManager creatureManager, TrainStopMap trainStopMap, int enemyBudget) {
        ArrayList<Tile> emptyTiles = trainStopMap.getOpenTiles();
        int usedBudget = 0;

        Collections.shuffle(emptyTiles);

        for (Tile emptyTile : emptyTiles) {
            if (usedBudget >= enemyBudget) {
                break;
            }

            Creature spawnedCreature = CreatureFactory.spawnTargetDummy(emptyTile, trainStopMap);
            creatureManager.addCreature(spawnedCreature);
            usedBudget += spawnedCreature.getCost();
        }


    }

    public void spawnEnemies(CreatureManager creatureManager, TrainStopMap trainStopMap, int enemyBudget) {
        ArrayList<Tile> emptyTiles = trainStopMap.getSpawns();
        int usedBudget = 0;

        //Collections.shuffle(emptyTiles);

        for (Tile emptyTile : emptyTiles) {
            if (usedBudget >= enemyBudget) {
                break;
            }

            Creature spawnedCreature = CreatureFactory.spawnRandomCreature(emptyTile, trainStopMap);
            creatureManager.addCreature(spawnedCreature);
            usedBudget += spawnedCreature.getCost();
        }


    }

}
