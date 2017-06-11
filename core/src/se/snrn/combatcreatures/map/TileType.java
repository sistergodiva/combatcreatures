package se.snrn.combatcreatures.map;


public enum TileType {
    EARTH(false),WALL(false), FLOOR(true), UP(true), DOOR(false), TRACK_TOP(true), TRACK_BOTTOM(true), TRACK_MIDDLE(true), DOWN(true), START(true);

    private boolean walkable;

    public boolean isWalkable() {
        return walkable;
    }

    TileType(boolean walkable) {

        this.walkable = walkable;
    }
}
