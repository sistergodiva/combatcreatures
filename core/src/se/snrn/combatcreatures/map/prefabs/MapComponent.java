package se.snrn.combatcreatures.map.prefabs;


import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.Tile;

public interface MapComponent extends Renderable{

    int getWidth();
    int getHeight();
    Tile getTile(int x, int y);

    Tile[][] getTiles();
}
