package se.snrn.combatcreatures.map.pathfinding;


import com.badlogic.gdx.Gdx;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TileMap;

import java.util.*;

import static se.snrn.combatcreatures.map.TileType.WALL;


public class AStar {

    private static int width;
    private static int height;

    private static final Map<Tile, Node> nodes = new HashMap<>();


    private static final Comparator fComparator = (Comparator<Node>) (a, b) -> {
        return Integer.compare(a.getFValue(), b.getFValue()); //ascending to get the lowest
    };


    private static void generateNodes(TileMap tileMap) {
        nodes.clear();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile point = tileMap.getTile(x, y);
                nodes.put(point, new Node(point));
            }
        }
    }

    public static ArrayList<Tile> calculateAStarNoTerrain(Tile p1, Tile p2, TileMap tileMap) {
        Gdx.app.log("AStar", "Doing expensive AStar");
        width = tileMap.getWidth();
        height = tileMap.getHeight();
        generateNodes(tileMap);
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node destNode = nodes.get(p2);

        Node currentNode = nodes.get(p1);
        currentNode.origin = null;
        currentNode.setGValue(0);
        openList.add(currentNode);

        while (!openList.isEmpty()) {

            Collections.sort(openList, fComparator);
            currentNode = openList.get(0);

            if (currentNode.equals(destNode)) {
                return calculatePath(destNode);
            }

            openList.remove(currentNode);
            closedList.add(currentNode);

            for (Tile adjPoint : tileMap.getOrthogonalNeighbours(currentNode.tile)
                    ) {
                if (!isInsideBounds(adjPoint.getX(), adjPoint.getY())) {
                    continue;
                }

                Node adjNode = nodes.get(adjPoint);
                if (adjNode.getType() == WALL || adjNode.tile.getMapped() != null && adjNode.tile != p2) {
                    continue;
                }

                if (!closedList.contains(adjNode)) {
                    if (!openList.contains(adjNode)) {
                        adjNode.origin = currentNode;
                        adjNode.calculateGValue(currentNode);
                        adjNode.calculateHValue(destNode);
                        openList.add(adjNode);
                    } else {
                        if (adjNode.gValue < currentNode.gValue) {
                            adjNode.calculateGValue(currentNode);
                            currentNode = adjNode;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static boolean isReachable(Tile start, Tile goal, TileMap map) {
        return calculateAStarNoTerrain(start, goal, map) != null;
    }


    private static ArrayList<Tile> calculatePath(Node destinationNode) {
        ArrayList<Tile> path = new ArrayList<>();
        Node node = destinationNode;
        while (node.origin != null) {
            path.add(node.tile);
            node = node.origin;
        }
        Collections.reverse(path);
        return path;
    }

    private static boolean isInsideBounds(int x, int y) {
        return x >= 0 &&
                x < width &&
                y >= 0 &&
                y < height;
    }

    public static int getDistance(Tile start, Tile goal, TileMap map) {
        return calculateAStarNoTerrain(start, goal, map).size();
    }
}