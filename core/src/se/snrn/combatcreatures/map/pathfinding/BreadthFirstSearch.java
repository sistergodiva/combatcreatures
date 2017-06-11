package se.snrn.combatcreatures.map.pathfinding;


import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import java.util.ArrayList;
import java.util.HashMap;

public class BreadthFirstSearch {


    private ArrayList<Node> frontier;
    private ArrayList<Node> visited;
    private Node current;
    private int range;
    private int nextElementsToDepthIncrease;
    private int elementsToDepthIncrease;
    private int currentDepth;

    private java.util.Map<Tile, Node> nodes = new HashMap<>();


    private void generateNodes(TrainStopMap map) {

        this.nodes.clear();

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Tile point = map.getTile(x, y);
                this.nodes.put(point, new Node(point));
            }
        }
    }


    public ArrayList<Node> getHighlight(Tile start, int distance, TrainStopMap map) {
        generateNodes(map);
        range = distance;
        frontier = new ArrayList<>();
        visited = new ArrayList<>();
        Node startNode = nodes.get(start);

        elementsToDepthIncrease = 1;
        currentDepth = 0;
        nextElementsToDepthIncrease = 0;
        frontier = new ArrayList<>();
        visited = new ArrayList<>();

        frontier.add(startNode);


        while (!frontier.isEmpty()) {
            current = frontier.get(0);

            if (currentDepth == range - 1) {
                nextElementsToDepthIncrease += map.getAllNeighbours(current.tile).size();
            } else {
                nextElementsToDepthIncrease += map.getOrthogonalNeighboursTerrain(current.tile).size();
            }
            if (--elementsToDepthIncrease == 0) {
                if (++currentDepth > range) {
                    visited.add(current);
                    visited.remove(startNode);
                    return visited;
                }
                elementsToDepthIncrease = nextElementsToDepthIncrease;
                nextElementsToDepthIncrease = 0;
            }

            if (currentDepth == range - 1) {

                for (int i = 0; i < map.getAllNeighbours(current.tile).size(); i++) {
                    frontier.add(nodes.get(map.getAllNeighbours(current.tile).get(i)));
                }
            } else {
                for (int i = 0; i < map.getOrthogonalNeighboursTerrain(current.tile).size(); i++) {
                    frontier.add(nodes.get(map.getOrthogonalNeighboursTerrain(current.tile).get(i)));
                }
            }
            if (frontier.get(0).tile.getMapped() == null && !visited.contains(frontier.get(0))) {
                visited.add(frontier.get(0));
            }
            frontier.remove(0);

        }

        return visited;
    }

}


