package se.snrn.combatcreatures.map;


public enum TileType {
    EARTH(false),
    WALL(false),
    FLOOR(true),
    UP(true),
    DOOR(false),
    DOWN(true);

    private boolean walkable;

    public boolean isWalkable() {
        return walkable;
    }

    TileType(boolean walkable) {

        this.walkable = walkable;
    }
}
