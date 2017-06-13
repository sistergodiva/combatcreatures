package se.snrn.combatcreatures.map.pathfinding;


import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;

public class Node {

    private final TileType type;
    public final Tile tile;

    public Node origin;

    public int gValue;

    private int hValue;
    private int MOVEMENT_COST = 10;

    private final int x;
    private final int y;
    private int movementCost;

    public Node(Tile tile) {
        this.tile = tile;
        this.type = tile.getType();
        this.x = tile.getX();
        this.y = tile.getY();
    }

    public Node(Tile tile, int movementCost) {
        this.tile = tile;
        this.type = tile.getType();
        this.x = tile.getX();
        this.y = tile.getY();
        this.movementCost = movementCost;
        MOVEMENT_COST = movementCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setGValue(int amount) {
        this.gValue = amount;
    }


    public void calculateHValue(Node destPoint) {
        this.hValue = (Math.abs(x - destPoint.x) + Math.abs(y - destPoint.y)) * this.MOVEMENT_COST;
    }

    public void calculateGValue(Node pos) {
        this.gValue = pos.gValue + this.MOVEMENT_COST;
    }

    public int getFValue() {
        return this.gValue + this.hValue;
    }

    public TileType getType() {
        return type;
    }


}
