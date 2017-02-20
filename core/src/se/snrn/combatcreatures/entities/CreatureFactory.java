package se.snrn.combatcreatures.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

public class CreatureFactory {


    public static Creature spawnCreature(Tile tile, TileMap tileMap, Sprite sprite){
        return new Creature(tile, tileMap, sprite);
    }
}
