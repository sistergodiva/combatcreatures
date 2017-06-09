package se.snrn.combatcreatures.map;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.DirectionDiagonal;
import se.snrn.combatcreatures.interfaces.Renderable;

import java.util.ArrayList;

public class TileMap implements Renderable {

    private final int width;
    private final int height;
    private Tile[][] tiles;
    private ArrayList<Tile> filled;
    private ArrayList<Tile> walls;
    private ArrayList<Tile> spawns;
    private Tile startTile;
    private Tile endTile;
    private boolean visited;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
    }


    @Override
    public void render(Batch batch) {
        for (Tile[] tile : tiles) {
            for (Tile aTile : tile) {
                aTile.render(batch);
            }
        }
    }

    public void addMapComponent(MapComponent mapComponent, int x, int y){
        for (Tile[] tile : mapComponent.getTiles()) {
            for (Tile aTile : tile) {
                tiles[aTile.getX()+x][aTile.getY()+y] = aTile;
                aTile.setPosition(aTile.getX()+x,aTile.getY()+y);
                aTile.setMap(this);
                System.out.println(aTile.getX()+" : "+aTile.getY());
            }
        }
    }


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

    public ArrayList<Tile> getOrthoNeighbours(Tile tile) {
        ArrayList<Tile> allNeighbours = new ArrayList<>();
        for (Direction dir : Direction.values()
                ) {
            Tile t = this.getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
            if (t != null) {
                allNeighbours.add(t);
            }
        }
        return allNeighbours;

    }

    public ArrayList<Tile> getAllNeighbours(Tile tile) {
        ArrayList<Tile> allNeighbours = new ArrayList<>();
        for (DirectionDiagonal dir : DirectionDiagonal.values()
                ) {
            Tile t = this.getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
            if (t != null) {
                allNeighbours.add(t);
            }
        }
        return allNeighbours;
    }

    public ArrayList<Tile> getOrthoNeighboursTerrain(Tile tile) {
        ArrayList<Tile> orthoNeighbours = new ArrayList<>();
        for (Direction dir : Direction.values()
                ) {
            Tile t = getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
            if (t != null && t.getType() == TileType.FLOOR && t.getMapped() == null) {
                orthoNeighbours.add(t);
            }
        }
        return orthoNeighbours;
    }

    public Tile getTileAtDirection(Tile tile, Direction dir) {
        return getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
    }

    public Direction getDirectionFromTile(Tile start, Tile goal) {
        if (getTileAtDirection(start, Direction.NORTH) == goal) {
            return Direction.NORTH;
        }
        if (getTileAtDirection(start, Direction.EAST) == goal) {
            return Direction.EAST;
        }
        if (getTileAtDirection(start, Direction.SOUTH) == goal) {
            return Direction.SOUTH;
        }
        if (getTileAtDirection(start, Direction.WEST) == goal) {
            return Direction.WEST;
        }
        return null;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setFilled(ArrayList<Tile> filled) {
        this.filled = filled;
    }

    public void setWalls(ArrayList<Tile> walls) {
        this.walls = walls;
    }

    public void setSpawns(ArrayList<Tile> spawns) {

        this.spawns = spawns;
    }

    public ArrayList<Tile> getFilled() {
        return filled;
    }

    public ArrayList<Tile> getWalls() {
        return walls;
    }

    public ArrayList<Tile> getSpawns() {
        return spawns;
    }


    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public Tile getEndTile() {
        return endTile;
    }

    public void setEndTile(Tile endTile) {
        this.endTile = endTile;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public Tile[][] getTileArray() {
        return tiles;
    }
}
