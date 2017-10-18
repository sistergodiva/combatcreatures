package se.snrn.combatcreatures.interfaces;


import se.snrn.combatcreatures.map.Direction;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TileMap;

public interface Mapped {

    Tile getTile();
    TileMap getMap();

    void changeTile(Tile tile);
    boolean move(Direction direction);


}
