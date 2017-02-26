package se.snrn.combatcreatures.interfaces;


import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

public interface Mapped {

    Tile getTile();
    TileMap getMap();

    void changeTile(Tile tile);
    boolean move(Direction direction);


}
