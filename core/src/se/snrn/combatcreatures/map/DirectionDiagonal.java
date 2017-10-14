package se.snrn.combatcreatures.map;

public enum DirectionDiagonal {
    NORTHWEST(-1, 1,0), NORTH(0, 1,2), NORTHEAST(1, 1,4), WEST(-1, 0,8), EAST(1, 0,16), SOUTHWEST(-1, -1,32), SOUTH(0, -1,64), SOUTHEAST(1, -1,128);

    private int x;
    private int y;
    private int bitMask;

    DirectionDiagonal(int x, int y, int bitMask) {
        this.x = x;
        this.y = y;
        this.bitMask = bitMask;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBitmask() {
        return bitMask;
    }

    public static boolean isDiagonal(DirectionDiagonal directionDiagonal) {
        return directionDiagonal == DirectionDiagonal.NORTHEAST
                || directionDiagonal == DirectionDiagonal.NORTHWEST
                || directionDiagonal == DirectionDiagonal.SOUTHEAST
                || directionDiagonal == DirectionDiagonal.SOUTHWEST;
    }
}
