package se.snrn.combatcreatures.map;


import se.snrn.combatcreatures.map.generator.DungeonGenerator;
import se.snrn.combatcreatures.map.generator.MapGenerator;

public class MapFactory {

    public static TileMap generateCellularTileMap() {
        MapGenerator mapGenerator = new MapGenerator(60, 40);

        return mapGenerator.getCellAutoMap(0.45, 3, 4, 3);
    }

    public static TileMap createEmptyMap() {

        return new TileMap(81, 81);
    }

    public static TileMap createDungeon(int width, int height) {
        return new TileMap(DungeonGenerator.createDungeon(width, height));
    }

    public static TileMap createDungeonFromAutomata(int width, int height) {
        return new TileMap(DungeonGenerator.createDungeonFromAutomata(width, height));
    }

}



