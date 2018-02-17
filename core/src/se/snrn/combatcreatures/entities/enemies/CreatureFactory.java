package se.snrn.combatcreatures.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TileMap;

public class CreatureFactory {
    private static JsonValue creatures;

    public CreatureFactory() {
        JsonReader jsonReader = new JsonReader();
        creatures = jsonReader.parse(Gdx.files.internal("json/creature.json")).get(0);

    }

    public static Creature spawnCreature(Tile tile, TileMap tileMap, int id) {
        return new Creature(tile, tileMap, creatures.get(id), new TargetDummyAiCore());
    }

    public static Creature spawnTargetDummy(Tile tile, TileMap tileMap) {
        int id = RandomNumber.range(0, creatures.size-1);
        return new Creature(tile, tileMap, creatures.get(id), new TargetDummyAiCore());

    }

    public static Creature spawnRandomCreature(Tile tile, TileMap tileMap) {
        int id = RandomNumber.range(0, creatures.size-1);
        return new Creature(tile, tileMap, creatures.get(id), new MeleeAiCore());

    }
}
