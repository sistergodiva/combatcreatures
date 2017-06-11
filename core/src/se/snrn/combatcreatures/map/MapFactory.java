package se.snrn.combatcreatures.map;


import se.snrn.combatcreatures.map.generator.MapGenerator;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

public class MapFactory {

    public static TrainStopMap generateCellularTileMap(int seed) {
        MapGenerator mapGenerator = new MapGenerator(60, 40, seed);
        return mapGenerator.getCellAutoMap(0.45, 3, 4, 3);
    }
}



