package se.snrn.combatcreatures.map;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.EnemySpawner;
import se.snrn.combatcreatures.entities.CreatureManager;
import se.snrn.combatcreatures.entities.DirectionDiagonal;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.generator.MapMerger;
import se.snrn.combatcreatures.map.generator.MapParser;

import java.util.ArrayList;

import static se.snrn.combatcreatures.map.TileType.WALL;

public class MapManager implements Renderable {

    private TileMap currentMap;
    private ArrayList<Tile> lineOfSight;
    private ArrayList<TileMap> floors;
    private int currentFloor;
    private EnemySpawner enemySpawner;
    private CreatureManager creatureManager;

    public MapManager(CreatureManager creatureManager) {
        this.creatureManager = creatureManager;
        currentFloor = 0;
        floors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            floors.add(MapFactory.generateTileMap());
            floors.get(0).setVisited(false);
        }
        enemySpawner = new EnemySpawner();

        currentMap = floors.get(currentFloor);
        lineOfSight = new ArrayList<>();



        //MapMerger mapMerger = new MapMerger();
        //currentMap = mapMerger.getMergedMap();
    }


    @Override
    public void render(Batch batch) {
        currentMap.render(batch);
    }

    public Tile getStartTile() {
        return currentMap.getStartTile();
    }

    public void moveDown() {
        if (currentFloor < floors.size()) {
            currentFloor++;
            changeFloor(currentFloor);
            System.out.println(currentFloor);
        }
    }

    public void moveUp() {
        if (currentFloor > 0) {
            currentFloor--;
            changeFloor(currentFloor);
            System.out.println(currentFloor);
        }
    }

    public void changeFloor(int newFloor){
        currentMap = floors.get(newFloor);
        if(!currentMap.isVisited()) {
            enemySpawner.spawnEnemies(creatureManager, this, 200);
            currentMap.setVisited(true);
        }
    }

    public TileMap getMap() {
        return currentMap;
    }

    public ArrayList<Tile> getLineOfSight(Tile originTile, int range) {
        ArrayList<Tile> lineOfSightList = new ArrayList<>();
        for (DirectionDiagonal dir : DirectionDiagonal.values()
                ) {
            for (int i = 1; i < range; i++) {
                Tile tmpTile = currentMap.getTile(originTile.getX() + (dir.getX() * i), originTile.getY() + (dir.getY() * i));
                if (tmpTile == null || tmpTile.getType() == WALL) {
                    break;
                } else if (!lineOfSightList.contains(tmpTile)) {
                    lineOfSightList.add(tmpTile);
                }
            }
        }

        ArrayList<Tile> los = new ArrayList<>();

        for (Tile tile : lineOfSightList
                ) {
            los.addAll(getAllNeighbours(tile));
        }

        for (Tile tile : los
                ) {
            if (!lineOfSightList.contains(tile)) {
                lineOfSightList.add(tile);
            }
        }

        los.clear();

        return lineOfSightList;
    }

    public ArrayList<Tile> getAllNeighbours(Tile tile) {
        ArrayList<Tile> allNeighbours = new ArrayList<>();
        for (DirectionDiagonal dir : DirectionDiagonal.values()
                ) {
            Tile t = currentMap.getTile(tile.getX() + dir.getX(), tile.getY() + dir.getY());
            if (t != null) {
                allNeighbours.add(t);
            }
        }
        return allNeighbours;
    }

    public void setLineOfSight(Tile tile, int range) {
        if (!lineOfSight.isEmpty()) {
            for (Tile wasVisible : lineOfSight) {
                wasVisible.setVisible(false);
            }
        }

        lineOfSight = getLineOfSight(tile, range);
        for (Tile visibleTile : lineOfSight) {
            visibleTile.setExplored(true);
            visibleTile.setVisible(true);
        }
    }

    public Tile getEndTile() {
        return currentMap.getEndTile();
    }

    public int getFloor() {
        return currentFloor;
    }
}
