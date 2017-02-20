package se.snrn.combatcreatures.map;

import se.snrn.combatcreatures.entities.Direction;

public class TileBitMask {



    private static Tile checkedTile;
    private static int bitMask;

    public static int getBitMask(Tile tile, TileMap tileMap) {
        bitMask = 0;

        for (Direction direction : Direction.values()) {
            checkedTile = tileMap.getTileAtDirection(tile, direction);
            if (checkedTile != null && checkedTile.getType() == TileType.WALL) {
                bitMask += direction.getBitMask();
            }
        }
        return bitMask;
    }

}
