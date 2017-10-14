package se.snrn.combatcreatures.interfaces;


import se.snrn.combatcreatures.map.Direction;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

public interface Mapped {

    Tile getTile();
    TrainStopMap getMap();

    void changeTile(Tile tile);
    boolean move(Direction direction);


}
