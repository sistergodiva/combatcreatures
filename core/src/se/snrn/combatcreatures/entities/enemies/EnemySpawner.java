package se.snrn.combatcreatures.entities.enemies;

import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TileMap;

import java.util.ArrayList;
import java.util.Collections;

public class EnemySpawner {

    public static void spawnTargetDummies(CreatureManager creatureManager, TileMap tileMap, int enemyBudget) {
        ArrayList<Tile> emptyTiles = tileMap.getSpawns();
        int usedBudget = 0;

        Collections.shuffle(emptyTiles);

        for (Tile emptyTile : emptyTiles) {
            if (usedBudget >= enemyBudget) {
                break;
            }

            Creature spawnedCreature = CreatureFactory.spawnTargetDummy(emptyTile, tileMap);
            creatureManager.addCreature(spawnedCreature);
            usedBudget += spawnedCreature.getCost();
        }


    }

    public static void spawnEnemies(CreatureManager creatureManager, TileMap tileMap, int enemyBudget) {
        ArrayList<Tile> emptyTiles = tileMap.getSpawns();
        int usedBudget = 0;

        Collections.shuffle(emptyTiles);

        for (Tile emptyTile : emptyTiles) {
            if (usedBudget >= enemyBudget) {
                break;
            }

            Creature spawnedCreature = CreatureFactory.spawnRandomCreature(emptyTile, tileMap);
            creatureManager.addCreature(spawnedCreature);
            usedBudget += spawnedCreature.getCost();
        }


    }

}
