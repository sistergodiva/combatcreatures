package se.snrn.combatcreatures.map;

import se.snrn.combatcreatures.entities.DirectionDiagonal;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import java.util.HashMap;

import static se.snrn.combatcreatures.entities.DirectionDiagonal.isDiagonal;
import static se.snrn.combatcreatures.map.TileType.FLOOR;


public class TileBitmask2 {

    private static HashMap<Integer, Integer> gfx;


    private static int getBitmaskFromTile(Tile tile, TrainStopMap trainStopMap) {
        int bitMask = 0;

        for (DirectionDiagonal direction : DirectionDiagonal.values()) {
            Tile checkedTile = trainStopMap.getTileAtDirection(tile, direction);
            bitMask += getBitMask(trainStopMap, direction, checkedTile, tile);
        }
        return bitMask;
    }

    private static int getBitMask(TrainStopMap trainStopMap, DirectionDiagonal direction, Tile checkedTile, Tile tile) {

        if(checkedTile == null){
            return 0;
        }
        if (checkedTile.getType() != FLOOR) {
            if (isDiagonal(direction)) {
                if (hasOneEmptyOrthogonalNeighbour(tile, trainStopMap, direction)) {
                    return 0;
                }
                return getDirectionInt(direction);
            }
            return getDirectionInt(direction);
        }
        return 0;
    }



    static boolean hasOneEmptyOrthogonalNeighbour(Tile checkedTile, TrainStopMap trainStopMap, DirectionDiagonal directionDiagonal) {

        if (directionDiagonal == DirectionDiagonal.NORTHWEST) {
            return trainStopMap.getTileAtDirection(checkedTile, directionDiagonal.NORTH).getType() == FLOOR
                    || trainStopMap.getTileAtDirection(checkedTile, directionDiagonal.WEST).getType() == FLOOR;
        }
        if (directionDiagonal == directionDiagonal.NORTHEAST) {
            return trainStopMap.getTileAtDirection(checkedTile, directionDiagonal.NORTH).getType() == FLOOR
                    || trainStopMap.getTileAtDirection(checkedTile, directionDiagonal.EAST).getType() == FLOOR;
        }
        if (directionDiagonal == directionDiagonal.SOUTHWEST) {
            return trainStopMap.getTileAtDirection(checkedTile, directionDiagonal.SOUTH).getType() == FLOOR
                    || trainStopMap.getTileAtDirection(checkedTile, directionDiagonal.WEST).getType() == FLOOR;
        }
        if (directionDiagonal == directionDiagonal.SOUTHEAST) {
            return trainStopMap.getTileAtDirection(checkedTile, directionDiagonal.SOUTH).getType() == FLOOR
                    || trainStopMap.getTileAtDirection(checkedTile, directionDiagonal.EAST).getType() == FLOOR;
        } else return false;
    }

    private static int getDirectionInt(DirectionDiagonal direction) {
        return (int) Math.pow(2, direction.ordinal());
    }

    static HashMap<Integer, Integer> fillGfxHash() {
        gfx = new HashMap<Integer, Integer>();
        gfx.put(2, 1);
        gfx.put(8, 2);
        gfx.put(10, 3);
        gfx.put(11, 4);
        gfx.put(16, 5);
        gfx.put(18, 6);
        gfx.put(22, 7);
        gfx.put(24, 8);
        gfx.put(26, 9);
        gfx.put(27, 10);
        gfx.put(30, 11);
        gfx.put(31, 12);
        gfx.put(64, 13);
        gfx.put(66, 14);
        gfx.put(72, 15);
        gfx.put(74, 16);
        gfx.put(75, 17);
        gfx.put(80, 18);
        gfx.put(82, 19);
        gfx.put(86, 20);
        gfx.put(88, 21);
        gfx.put(90, 22);
        gfx.put(91, 23);
        gfx.put(94, 24);
        gfx.put(95, 25);
        gfx.put(104, 26);
        gfx.put(106, 27);
        gfx.put(107, 28);
        gfx.put(120, 29);
        gfx.put(122, 30);
        gfx.put(123, 31);
        gfx.put(126, 32);
        gfx.put(127, 33);
        gfx.put(208, 34);
        gfx.put(210, 35);
        gfx.put(214, 36);
        gfx.put(216, 37);
        gfx.put(218, 38);
        gfx.put(219, 39);
        gfx.put(222, 40);
        gfx.put(223, 41);
        gfx.put(248, 42);
        gfx.put(250, 43);
        gfx.put(251, 44);
        gfx.put(254, 45);
        gfx.put(255, 46);
        gfx.put(0, 47);

        return gfx;
    }

    static int getTileNumber(Tile tile, TrainStopMap compositeMap) {
        if (gfx == null) {
            gfx = fillGfxHash();
        }
        int bitMask = getBitmaskFromTile(tile, compositeMap);
        return gfx.get(bitMask);
    }
}

