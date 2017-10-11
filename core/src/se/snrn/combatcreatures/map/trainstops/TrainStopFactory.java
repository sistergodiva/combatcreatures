package se.snrn.combatcreatures.map.trainstops;


import se.snrn.combatcreatures.map.MapFactory;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.generator.DungeonGenerator;
import se.snrn.combatcreatures.map.prefabs.MapComponent;
import se.snrn.combatcreatures.map.prefabs.Tracks;

public class TrainStopFactory {

    public static TrainStopMap getTrainStop(StopType type, boolean verticalTrack) {
        switch (type) {
            case SWITCH: {
                TrainStopMap trainStopMap = MapFactory.generateCellularTileMap(666);
                //addMapComponent(trainStopMap.getTiles(), new Tracks(trainStopMap.getWidth()), 0,0);
                return trainStopMap;
            }
            case BROKEN: {
                return new BrokenStop(new Tile[10][10]);
            }
        }
        return null;
    }

    public static TrainStopMap getDungeonMap(){
        return DungeonGenerator.getDungeonMap(60,40);
    }


    public static Tile[][] getTiles(StopType type, boolean verticalTrack){
        Tile[][] tiles = MapFactory.generateCellularTileMap(666).getTiles();
        tiles = addMapComponent(tiles, new Tracks(tiles.length), 0,0);

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
