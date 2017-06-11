package se.snrn.combatcreatures.map.trainstops;


import se.snrn.combatcreatures.map.MapFactory;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.prefabs.MapComponent;
import se.snrn.combatcreatures.map.prefabs.Tracks;

public class TrainStopFactory {

    public static TrainStopMap getTrainStop(StopType type, boolean verticalTrack) {
        switch (type) {
            case SWITCH: {
                return new SwitchStop(getTiles(type, verticalTrack));
            }
            case BROKEN: {
                return new BrokenStop(new Tile[10][10]);
            }
        }
        return null;
    }


    public static Tile[][] getTiles(StopType type, boolean verticalTrack){
        Tile[][] tiles = MapFactory.generateCellularTileMap().getTileArray();
        tiles = addMapComponent(tiles, new Tracks(), 0,0);
        return tiles;
    }

    public static Tile[][] addMapComponent(Tile[][] tiles, MapComponent mapComponent, int x, int y) {
        for (Tile[] tile : mapComponent.getTiles()) {
            for (Tile aTile : tile) {
                tiles[aTile.getX()+x][aTile.getY()+y] = aTile;
                aTile.setPosition(aTile.getX()+x,aTile.getY()+y);
            }
        }
        return tiles;
    }
}
