package se.snrn.combatcreatures.map.los;


import com.badlogic.gdx.math.GridPoint2;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;
import se.snrn.combatcreatures.map.TileType;

import java.util.ArrayList;

public class LineOfSight {

    public static ArrayList<Tile> visibleTiles = new ArrayList<>();
    public static ArrayList<GridPoint2> visibleGridPoints = new ArrayList<>();

    public static ArrayList<GridPoint2> getLosCircle(final int centerX, final int centerY, final int radius) {

        ArrayList<GridPoint2> circle = new ArrayList<>();

        int d = 3 - (2 * radius);
        int x = 0;
        int y = radius;


        do {
            circle.add(new GridPoint2(centerX + x, centerY + y));
            circle.add(new GridPoint2(centerX + x, centerY - y));
            circle.add(new GridPoint2(centerX - x, centerY + y));
            circle.add(new GridPoint2(centerX - x, centerY - y));
            circle.add(new GridPoint2(centerX + y, centerY + x));
            circle.add(new GridPoint2(centerX + y, centerY - x));
            circle.add(new GridPoint2(centerX - y, centerY + x));
            circle.add(new GridPoint2(centerX - y, centerY - x));
            if (d < 0) {
                d = d + (4 * x) + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        } while (x <= y);

        return circle;
    }


    public static ArrayList<GridPoint2> getCircleFill(int x0, int y0, GridPoint2 gridPoint2) {
        ArrayList<GridPoint2> line = new ArrayList<>();
        line.add(new GridPoint2(x0, y0));




            int x1 = gridPoint2.x;
            int y1 = gridPoint2.y;


            int dx = Math.abs(x1 - x0);
            int dy = Math.abs(y1 - y0);
            int sx = (x0 < x1) ? 1 : -1;
            int sy = (y0 < y1) ? 1 : -1;
            int err = dx - dy;
            int e2;

            while (!(x0 == x1 && y0 == y1)) {

                e2 = 2 * err;

                if (e2 > -dy) {
                    err -= dy;
                    x0 += sx;
                }

                if (e2 < dx) {
                    err += dx;
                    y0 += sy;
                }

                line.add(new GridPoint2(x0, y0));
            }

        return line;
    }

    public static ArrayList<GridPoint2> isVisible(TileMap map, int x0, int y0, int x1, int y1) {
        ArrayList<GridPoint2> line = new ArrayList<>();
        line.add(new GridPoint2(x0, y0));
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        int e2;

        while (!(x0 == x1 && y0 == y1)) {
            if (map.getTile(x0, y0).getType() == TileType.WALL) {
                line.clear();
                return line;
            }

            e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }

            line.add(new GridPoint2(x0, y0));
        }

        return line;
    }

    public static ArrayList<GridPoint2> isVisible(Tile[][] tiles, int x0, int y0, int x1, int y1) {
        ArrayList<GridPoint2> line = new ArrayList<>();
        line.add(new GridPoint2(x0, y0));
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        int e2;

        while (!(x0 == x1 && y0 == y1)) {
            if (tiles[x0][y0].getType() == TileType.WALL) {
                line.clear();
                return line;
            }

            e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }

            line.add(new GridPoint2(x0, y0));
        }

        return line;
    }

    public static boolean visibleBool(TileMap currentMap, Tile tile, Tile loopTile) {
        ArrayList<GridPoint2> line = new ArrayList<>();

        int x0 = tile.getX();
        int y0 = tile.getY();

        int x1 = loopTile.getX();
        int y1 = loopTile.getY();

        line.add(new GridPoint2(x0, y0));
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        int e2;

        while (!(x0 == x1 && y0 == y1)) {
            if (currentMap.getTile(x0, y0).getType() == TileType.WALL) {
                line.clear();
                return true;
            }

            e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }

            line.add(new GridPoint2(x0, y0));
        }

        return true;
    }



    public static ArrayList<Tile> getLineOfSight(Tile tile, Tile[][] tiles) {

        if (!visibleTiles.isEmpty()) {
            for (Tile oldVisible : visibleTiles
                    ) {
                oldVisible.setVisible(false);
            }
            visibleTiles.clear();
        }

        ArrayList<GridPoint2> circle = getLosCircle(tile,tiles, 4);


        for (GridPoint2 circleTile : circle) {
            if (circleTile.x < tiles.length && circleTile.x >= 0 && circleTile.y < tiles[0].length && circleTile.y >= 0) {
                Tile loopTile = tiles[circleTile.x][circleTile.y];

                if (loopTile != null) {


                    ArrayList<GridPoint2> visibleTiles = isVisible(tiles, tile.getX(), tile.getY(), loopTile.getX(), loopTile.getY());
                    for (GridPoint2 visibleTile : visibleTiles) {
                        Tile myTile = tiles[visibleTile.x][visibleTile.y];

                        myTile.setExplored(true);
                        myTile.setVisible(true);
                        LineOfSight.visibleTiles.add(myTile);
                    }

                }
            }
        }
        return visibleTiles;

    }


    public static ArrayList<GridPoint2> getLosCircle(Tile tile, Tile[][] tiles, int radius) {

        ArrayList<GridPoint2> circleBorder = getLineOfSightCircle(tile.getX(), tile.getY(), radius, tiles);
        ArrayList<GridPoint2> circleFill = new ArrayList<>();
        for (GridPoint2 border : circleBorder
                ) {
            circleFill.addAll(LineOfSight.getCircleFill(tile.getX(), tile.getY(), border));
        }
        return circleFill;
    }

    public static ArrayList<Tile> getVision() {
        return visibleTiles;
    }

    public static ArrayList<GridPoint2> getLineOfSightCircle(final int centerX, final int centerY, final int radius, Tile[][] tiles) {

        ArrayList<GridPoint2> circle = new ArrayList<>();

        int d = 3 - (2 * radius);
        int x = 0;
        int y = radius;


        do {
            circle.add(new GridPoint2(centerX + x, centerY + y));
            circle.add(new GridPoint2(centerX + x, centerY - y));
            circle.add(new GridPoint2(centerX - x, centerY + y));
            circle.add(new GridPoint2(centerX - x, centerY - y));
            circle.add(new GridPoint2(centerX + y, centerY + x));
            circle.add(new GridPoint2(centerX + y, centerY - x));
            circle.add(new GridPoint2(centerX - y, centerY + x));
            circle.add(new GridPoint2(centerX - y, centerY - x));
            if (d < 0) {
                d = d + (4 * x) + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        } while (x <= y);

        return circle;
    }

}

