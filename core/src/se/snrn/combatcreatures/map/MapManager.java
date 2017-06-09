package se.snrn.combatcreatures.map;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import se.snrn.combatcreatures.entities.enemies.EnemySpawner;
import se.snrn.combatcreatures.entities.enemies.CreatureManager;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.los.LineOfSight;

import java.util.ArrayList;

public class MapManager implements Renderable {

    private TileMap currentMap;
    private ArrayList<TileMap> floors;
    private int currentFloor;
    private EnemySpawner enemySpawner;
    private CreatureManager creatureManager;
    private ArrayList<Tile> vision;

    public MapManager(CreatureManager creatureManager) {
        this.creatureManager = creatureManager;
        currentFloor = 0;
        floors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            floors.add(MapFactory.generateTileMap());
            floors.get(0).setVisited(false);
        }
        currentMap = floors.get(currentFloor);
        currentMap.setVisited(true);
        currentMap.addMapComponent(new Train(10,10,0,0), 10, 10);
        enemySpawner = new EnemySpawner();

        vision = new ArrayList<>();



        //enemySpawner.spawnTargetDummies(creatureManager, this, 200);
        enemySpawner.spawnEnemies(creatureManager, this, 200);

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
        }
    }

    public void moveUp() {
        if (currentFloor > 0) {
            currentFloor--;
            changeFloor(currentFloor);
        }
    }

    public void changeFloor(int newFloor) {
        currentMap = floors.get(newFloor);
        System.out.println(currentFloor + " is the new floor\n map is " + currentMap);

        if (!currentMap.isVisited()) {
            enemySpawner.spawnEnemies(creatureManager, this, 200);
            currentMap.setVisited(true);
        }
    }

    public TileMap getMap() {
        return currentMap;
    }


    public ArrayList<Tile> qdLoS(Tile tile) {

        if (!vision.isEmpty()) {
            for (Tile oldVisible : vision
                    ) {
                oldVisible.setVisible(false);
            }
            vision.clear();
        }

        ArrayList<GridPoint2> circle = getLosCircle(tile, 4);


        for (GridPoint2 circleTile : circle) {
            Tile loopTile = currentMap.getTile(circleTile.x, circleTile.y);

            if (loopTile != null) {


                ArrayList<GridPoint2> visibleTiles = LineOfSight.isVisible(currentMap, tile.getX(), tile.getY(), loopTile.getX(), loopTile.getY());
                for (GridPoint2 visibleTile : visibleTiles) {
                    Tile myTile = currentMap.getTile(visibleTile.x, visibleTile.y);

                    myTile.setExplored(true);
                    myTile.setVisible(true);
                    vision.add(myTile);
                }

            }
        }

        return vision;

    }


    public ArrayList<GridPoint2> getLosCircle(Tile tile, int radius) {

        ArrayList<GridPoint2> circleBorder = LineOfSight.getLosCircle(tile.getX(), tile.getY(), radius, currentMap);
        ArrayList<GridPoint2> circleFill = new ArrayList<>();
        for (GridPoint2 border : circleBorder
                ) {
            circleFill.addAll(LineOfSight.getCircleFill(tile.getX(), tile.getY(), border));
        }
        return circleFill;
    }

    public Tile getEndTile() {
        return currentMap.getEndTile();
    }

    public int getFloor() {
        return currentFloor;
    }

    public ArrayList<Tile> getVision() {
        return vision;
    }
}
