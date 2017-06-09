package se.snrn.combatcreatures.map;

import se.snrn.combatcreatures.entities.Direction;

public class TileBitMask {


    public static int getBitMask(Tile tile, TileMap tileMap) {
        int bitMask = 0;

        for (Direction direction : Direction.values()) {
            Tile checkedTile = tileMap.getTileAtDirection(tile, direction);
            if (checkedTile != null && checkedTile.getType() == TileType.WALL) {
                bitMask += direction.getBitMask();
            }
        }

        return bitMask;
    }

}
