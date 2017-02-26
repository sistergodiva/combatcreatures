package se.snrn.combatcreatures.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

public class CreatureFactory {
    static JsonValue creatureStats;
    static JsonValue creatures;

    public CreatureFactory() {
        JsonReader jsonReader = new JsonReader();
        creatureStats = jsonReader.parse(Gdx.files.internal("json/creature_stats.json")).get(0);
        creatures = jsonReader.parse(Gdx.files.internal("json/creature.json")).get(0);

    }

    public static Creature spawnCreature(Tile tile, TileMap tileMap, int id) {
        return new Creature(tile, tileMap, creatureStats.get(id), creatures.get(id));
    }



    public static Creature spawnRandomCreature(Tile tile, TileMap tileMap) {
        int id = RandomNumber.range(0, creatures.size-1);
        return new Creature(tile, tileMap, creatureStats.get(id), creatures.get(id));

    }
}
