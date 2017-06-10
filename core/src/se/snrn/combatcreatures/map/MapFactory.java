package se.snrn.combatcreatures.map;


import se.snrn.combatcreatures.map.generator.MapGenerator;

public class MapFactory {

    public static TileMap generateTileMap(){
        MapGenerator mapGenerator = new MapGenerator(60,40);

        return mapGenerator.getCellAutoMap(0.45, 3, 4, 3);
    }

    public static TileMap createEmptyMap(){

        return new TileMap(81,81);
    }

}



