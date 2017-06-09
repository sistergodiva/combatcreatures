package se.snrn.combatcreatures.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.map.Tile;

public class CreatureFactory {
    static JsonValue creatureStats;
    static JsonValue creatures;

    public CreatureFactory() {
        JsonReader jsonReader = new JsonReader();
        creatureStats = jsonReader.parse(Gdx.files.internal("json/creature_stats.json")).get(0);
        creatures = jsonReader.parse(Gdx.files.internal("json/creature.json")).get(0);

    }

    public static Creature spawnCreature(Tile tile, MapManager mapManager, int id) {
        return new Creature(tile, mapManager, creatureStats.get(id), creatures.get(id), new TargetDummyAiCore());
    }

    public static Creature spawnTargetDummy(Tile tile, MapManager mapManager) {
        int id = RandomNumber.range(0, creatures.size-1);
        return new Creature(tile, mapManager, creatureStats.get(id), creatures.get(id), new TargetDummyAiCore());

    }

    public static Creature spawnRandomCreature(Tile tile, MapManager mapManager) {
        int id = RandomNumber.range(0, creatures.size-1);
        return new Creature(tile, mapManager, creatureStats.get(id), creatures.get(id), new MeleeAiCore());

    }
}
