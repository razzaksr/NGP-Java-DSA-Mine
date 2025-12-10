package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSinGraph {
    private Map<Integer, List<Integer>> adjList;
    public BFSinGraph() {
        adjList = new HashMap<>();
    }
    // Add edge (undirected graph)
    public void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        // adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        // adjList.get(dest).add(src);
    }
    public Map<Integer, List<Integer>> getAdjList() {
        return adjList;
    }
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            if(getAdjList().containsKey(node)){
                for (int neighbor : getAdjList().get(node)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
    public void dfs(int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");
        if(getAdjList().containsKey(node)){
            for (int neighbor : getAdjList().get(node)) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, visited);
                }
            }
        }
    }
    public static void main(String[] args) {
        BFSinGraph g = new BFSinGraph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        System.out.println("Graph\n"+g.getAdjList());
        // System.out.println("BFS starting from node 1:");
        // g.bfs(1);
        System.out.println("\nDFS starting from node 1:");
        Set<Integer> visited = new HashSet<>();
        g.dfs(1, visited);
    }
}
