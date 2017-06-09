package se.snrn.combatcreatures.map;


import se.snrn.combatcreatures.interfaces.Renderable;

public interface MapComponent extends Renderable{

    void setPosition(int x, int y);
    int getWidth();
    int getHeight();
    Tile getTile(int x, int y);

    Tile[][] getTiles();
}
