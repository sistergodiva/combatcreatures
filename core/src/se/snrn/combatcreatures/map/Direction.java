package se.snrn.combatcreatures.map;

public enum Direction {
    NORTH(0, 1, 1), EAST(1, 0, 4), SOUTH(0, -1, 8), WEST(-1, 0, 2);

    private int x;
    private int y;
    private int bitMask;

    Direction(int x, int y, int bitMask) {
        this.x = x;
        this.y = y;
        this.bitMask = bitMask;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBitMask() {
        return bitMask;
    }
}
