package se.snrn.combatcreatures;

import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.CreatureFactory;
import se.snrn.combatcreatures.entities.CreatureManager;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

import java.util.ArrayList;
import java.util.Collections;

public class EnemySpawner {

    public void spawnTargetDummies(CreatureManager creatureManager, MapManager mapManager, int enemyBudget) {
        TileMap tileMap = mapManager.getMap();
        ArrayList<Tile> emptyTiles = tileMap.getFilled();
        int usedBudget = 0;

        Collections.shuffle(emptyTiles);

        for (Tile emptyTile : emptyTiles) {
            if(usedBudget >= enemyBudget){
                break;
            }

            Creature spawnedCreature = CreatureFactory.spawnTargetDummy(emptyTile, mapManager);
            creatureManager.addCreature(spawnedCreature);
            usedBudget += spawnedCreature.getCost();
        }


    }

    public void spawnEnemies(CreatureManager creatureManager, MapManager mapManager, int enemyBudget) {
        TileMap tileMap = mapManager.getMap();
        ArrayList<Tile> emptyTiles = tileMap.getFilled();
        int usedBudget = 0;

        Collections.shuffle(emptyTiles);

        for (Tile emptyTile : emptyTiles) {
            if(usedBudget >= enemyBudget){
                break;
            }

            Creature spawnedCreature = CreatureFactory.spawnRandomCreature(emptyTile, mapManager);
            creatureManager.addCreature(spawnedCreature);
            usedBudget += spawnedCreature.getCost();
        }


    }

}
