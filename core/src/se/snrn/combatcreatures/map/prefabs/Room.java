package se.snrn.combatcreatures.map.prefabs;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;

import static se.snrn.combatcreatures.entities.Direction.SOUTH;

public class Room implements MapComponent {


    private Tile door;
    private int width;
    private int height;
    public Tile[][] tiles;
    Direction doorDirection;

    public Room(int width, int height) {


        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];

        doorDirection = SOUTH;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
                    this.tiles[x][y] = new Tile(x, y, TileType.WALL);
                    if (y == 0 && x == width / 2) {
                        door = new Tile(x, y, TileType.DOOR);
                        this.tiles[x][y] = door;

                    }
                } else {
                    this.tiles[x][y] = new Tile(x, y, TileType.FLOOR);
                }
            }
        }


        int random = RandomNumber.range(0, 3);
        for (int i = 0; i < random; i++) {
            tiles = MapComponent.rotateCW(tiles);
            int newDoorDirection = doorDirection.ordinal()+1;
            if(newDoorDirection > 3){
                newDoorDirection = 0;
            }
            doorDirection = Direction.values()[newDoorDirection];
        }

    }

    @Override
    public void render(Batch batch) {
        for (Tile[] tile : tiles) {
            for (Tile aTile : tile) {
                aTile.render(batch);
            }
        }
    }


    @Override
    public Tile getTile(int x, int y) {
        if (x < width &&
                x >= 0 &&
                y < height &&
                y >= 0 &&
                tiles[x][y] != null) {
            return tiles[x][y];
        }
        return null;
    }

    @Override
    public Tile[][] getTiles() {
        return tiles;
    }

    @Override
    public Direction getDoorDirection() {
        return doorDirection;
    }

    @Override
    public Tile getDoor() {
        return door;
    }

    @Override
    public Tile getMiddle() {
        return tiles[(width/2)-1][(height/2)-1];
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
