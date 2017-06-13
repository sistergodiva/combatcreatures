package se.snrn.combatcreatures.map;

import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;
import se.snrn.combatcreatures.worldmap.WorldMap;

public class TileBitMask {




    public static int getBitMaskFromArray(Tile tile, TrainStopMap trainStopMap) {
        int bitMask = 0;

        for (Direction direction : Direction.values()) {
            Tile checkedTile = getTileAtDirection(tile, direction, trainStopMap);
            if (checkedTile != null && checkedTile.getType() == TileType.WALL || checkedTile != null && checkedTile.getType() == TileType.EARTH) {
                bitMask += direction.getBitMask();
            }
        }
        return bitMask;
    }


    public static int getBitMaskFromArray(Tile tile, WorldMap worldMap) {
        int bitMask = 0;

        for (Direction direction : Direction.values()) {
            Tile checkedTile = getTileAtDirection(tile, direction, worldMap);
            if (checkedTile != null && checkedTile.getType() == TileType.TRACK || checkedTile != null && checkedTile.getType() == TileType.BROKEN_TRACK) {
                bitMask += direction.getBitMask();
            }
        }
        return bitMask;
    }

    public static Tile getTileAtDirection(Tile tile, Direction dir, WorldMap worldMap) {
        return worldMap.getTileAtDirection(tile, dir);
    }

    public static Tile getTileAtDirection(Tile tile, Direction dir, TrainStopMap trainStopMap) {
        return trainStopMap.getTileAtDirection(tile, dir);
    }
}
