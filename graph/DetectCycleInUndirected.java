package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetectCycleInUndirected {
    public Map<Integer, List<Integer>> adjList;
    int V;
    public DetectCycleInUndirected(int size) {
        adjList = new HashMap<>();
        V = size;
    }
    // Add edge (Directed graph)
    public void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }
    boolean isCyclicUtil(int v, boolean[] visited, int parent) {
        visited[v] = true;
        if(adjList.containsKey(v)){
            for (int neighbor : adjList.get(v)) {
                if (!visited[neighbor]) {
                    if (isCyclicUtil(neighbor, visited, v))
                        return true;
                } else if (neighbor != parent) {
                    return true; // cycle found
                }
            }
        }
        return false;
    }

    boolean isCyclic() {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, -1))
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        DetectCycleInUndirected detect = new DetectCycleInUndirected(5);
        detect.addEdge(0, 1);
        detect.addEdge(1, 2);
        detect.addEdge(2, 3);
        detect.addEdge(3, 4);
        // Uncomment below to create a cycle
        // detect.addEdge(4, 1);
        if (detect.isCyclic()) System.out.println("Cycle detected – Invalid network");
        else System.out.println("No cycle – Tree-like network");
    }
}

/* 
0—1, 1—2, 2—3, 3—4, 4—1
- Start DFS at 0.
- Visit 1 → 2 → 3 → 4.
- From 4(parent), edge goes back to 1(neighbor).
- 1 is already visited and not the parent → Cycle detected.
*/