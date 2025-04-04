package ca.mb.wcb.dag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DirectedAcyclicGraph {
    private final Map<Character, List<Character>> adjacencyList;

    public DirectedAcyclicGraph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a directed edge between two nodes (automatically creates nodes if they don't exist)
     * @param from Source node
     * @param to Destination node
     */
    public void addEdge(char from, char to) {
        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.putIfAbsent(to, new ArrayList<>());
        adjacencyList.get(from).add(to);
    }

    /**
     * Checks if a path exists between two nodes using BFS
     * @param start Starting node
     * @param end Target node
     * @return true if path exists, false otherwise
     */
    public boolean findPath(char start, char end) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(end)) {
            return false;
        }
        
        if (start == end) return true;

        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            char current = queue.poll();

            for (char neighbor : adjacencyList.get(current)) {
                if (neighbor == end) return true;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }
}
