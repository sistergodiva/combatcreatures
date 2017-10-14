package se.snrn.combatcreatures.map.trainstops;


import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.MapFactory;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.prefabs.MapComponent;

public class TrainStopFactory {

    public static TrainStopMap getTrainStop() {

        return MapFactory.generateCellularTileMap(RandomNumber.range(1, 100));

    }


    public static Tile[][] addMapComponent(Tile[][] tiles, MapComponent mapComponent, int x, int y) {
        for (Tile[] tile : mapComponent.getTiles()) {
            for (Tile aTile : tile) {
                tiles[aTile.getX() + x][aTile.getY() + y] = aTile;
                aTile.setPosition(aTile.getX() + x, aTile.getY() + y);
            }
        }
        return tiles;
    }

}
