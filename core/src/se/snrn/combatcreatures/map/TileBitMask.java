package se.snrn.combatcreatures.map;

import se.snrn.combatcreatures.entities.DirectionDiagonal;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import java.util.ArrayList;

import static se.snrn.combatcreatures.MissionScreen.trainStopMap;
import static se.snrn.combatcreatures.map.TileType.FLOOR;

public class TileBitMask {


    public static int getBitMaskFromArray(Tile tile, TrainStopMap trainStopMap) {
        int bitMask = 0;

        for (DirectionDiagonal directionDiagonal : DirectionDiagonal.values()) {
            bitMask += getBitMask(tile, trainStopMap, directionDiagonal);

        }
        ArrayList bajs = trainStopMap.getAllNeighbours(tile);

        System.out.println(bitMask);
        return bitMask;
    }

    private static int getBitMask(Tile tile, TrainStopMap trainStopMap, DirectionDiagonal directionDiagonal) {
        Tile checkedTile = getTileAtDirection(tile, directionDiagonal, trainStopMap);
//        if (checkedTile != null && checkedTile.getType() == TileType.WALL || checkedTile != null && checkedTile.getType() == TileType.EARTH) {
//            if (isDiagonal(directionDiagonal)) {
//                if (hasOneEmptyOrthogonalNeighbour(checkedTile, directionDiagonal)) {
//                    return getDirectionInt(directionDiagonal);
//                }
//            } else {
//                return getDirectionInt(directionDiagonal);
//            }
//            return getDirectionInt(directionDiagonal);
//        }
//        return 0;
        if(checkedTile == null){
            return 0;
        }
        if (checkedTile.getType() != FLOOR) {
            if (isDiagonal(directionDiagonal)) {
                if (hasOneEmptyOrthogonalNeighbour(tile, directionDiagonal)) {
                    return 0;
                }
                return getDirectionInt(directionDiagonal);
            }
            return getDirectionInt(directionDiagonal);
        }
        return 0;
    }

    private static boolean isDiagonal(DirectionDiagonal directionDiagonal) {

        //return DirectionDiagonal.isDiagonal(directionDiagonal);
        return (directionDiagonal.ordinal() % 2) == 0;
    }

    private static int getDirectionInt(DirectionDiagonal directionDiagonal) {
        //return (int) Math.pow(2, directionDiagonal.ordinal());
        return directionDiagonal.getBitmask();
    }


    private static boolean isFloor(Tile checkedTile, DirectionDiagonal direction) {
        return trainStopMap.getTileAtDirection(checkedTile, direction) != null
                && trainStopMap.getTileAtDirection(checkedTile, direction).getType() == FLOOR;
    }

    private static boolean hasOneEmptyOrthogonalNeighbour(Tile checkedTile, DirectionDiagonal directionDiagonal) {
        if (checkedTile == null) {
            return true;
        }
        switch (directionDiagonal) {
            case NORTHWEST:
                return isFloor(checkedTile, DirectionDiagonal.NORTH)
                        || isFloor(checkedTile, DirectionDiagonal.WEST);
            case NORTHEAST:
                return isFloor(checkedTile, DirectionDiagonal.NORTH)
                        || isFloor(checkedTile, DirectionDiagonal.EAST);
            case SOUTHWEST:
                return isFloor(checkedTile, DirectionDiagonal.SOUTH)
                        || isFloor(checkedTile, DirectionDiagonal.WEST);
            case SOUTHEAST:
                return isFloor(checkedTile, DirectionDiagonal.SOUTH)
                        || isFloor(checkedTile, DirectionDiagonal.EAST);
            default:
                return false;
        }
    }


    private static Tile getTileAtDirection(Tile tile, DirectionDiagonal directionDiagonal, TrainStopMap trainStopMap) {
        return trainStopMap.getTileAtDirection(tile, directionDiagonal);
    }
}
