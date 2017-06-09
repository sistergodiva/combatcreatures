package se.snrn.combatcreatures.map.generator;

import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

import static se.snrn.combatcreatures.map.TileType.FLOOR;
import static se.snrn.combatcreatures.map.TileType.WALL;

public class MapMerger {

    MapGenerator mapGenerator;

    public MapMerger() {
        mapGenerator = new MapGenerator(50, 50);

    }

    public TileMap getMergedMap() {
        boolean[][] map1 = mapGenerator.getBooleanMap(0.40, 3, 4, 6);
        boolean[][] map2 = mapGenerator.getBooleanMap(0.40, 3, 4, 6);

        map1 = mergeMaps(map1, map2);

        return getMapFromBool(map1, map1.length, map1[0].length);
    }


    private TileMap getMapFromBool(boolean[][] cellGrid, int width, int height) {
        TileMap map = new TileMap(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (cellGrid[i][j]) {
                    map.getTileArray()[i][j] = new Tile(i, j, WALL, map);
                }
                if (!cellGrid[i][j]) {
                    map.getTileArray()[i][j] = new Tile(i, j, FLOOR, map);
                }

            }
        }
        MapParser mapParser = new MapParser();
        mapParser.parseMap(map);
        return map;
    }


    public boolean[][] mergeMaps(boolean[][] map1, boolean[][] map2) {
        int width = map1.length;
        int height = map1[0].length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map1[x][y] && map2[x][y]) {
                    map1[x][y] = false;
                }
                if (!map1[x][y] && !map2[x][y]) {
                    map1[x][y] = true;

                }
            }

        }
        return map1;
    }
}