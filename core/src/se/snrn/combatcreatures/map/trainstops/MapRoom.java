package se.snrn.combatcreatures.map.trainstops;


import se.snrn.combatcreatures.map.Tile;

import java.util.ArrayList;

public class MapRoom {
    private ArrayList<Tile> roomTiles;

    public MapRoom(ArrayList<Tile> tiles) {

        this.roomTiles = tiles;
    }

    public ArrayList<Tile> getRoomTiles() {
        return roomTiles;
    }
}
