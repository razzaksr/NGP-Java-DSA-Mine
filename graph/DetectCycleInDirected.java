package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
- Directed graph cycle detection requires careful handling of node indices.
- adjList.size() ≠ number of vertices. Always consider destinations too.
- Either track max index or pass vertex count explicitly.
*/

public class DetectCycleInDirected {
    public Map<Integer, List<Integer>> adjList;
    int V;
    public DetectCycleInDirected(int size) {
        adjList = new HashMap<>();
        V = size;
    }
    // Add edge (Directed graph)
    public void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.get(src).add(dest);
    }
    boolean isCyclicUtil(int v, boolean[] visited, boolean[] recStack) {
        if (recStack[v]) return true;   // cycle found
        if (visited[v]) return false;   // already processed

        visited[v] = true;
        recStack[v] = true;

        if(adjList.containsKey(v)){
            for (int neighbor : adjList.get(v)) {
                if (isCyclicUtil(neighbor, visited, recStack))
                    return true;
            }
        }

        recStack[v] = false; // backtrack
        return false;
    }

    boolean isCyclic() {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (isCyclicUtil(i, visited, recStack))
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        DetectCycleInDirected detect = new DetectCycleInDirected(4);
        detect.addEdge(0, 1);
        detect.addEdge(1, 2);
        detect.addEdge(2, 3);
        // the below is cycle
        // detect.addEdge(3, 1);
        System.out.println("Graph\n"+detect.adjList);
        if (detect.isCyclic()) System.out.println("cycle detected ");
        else System.out.println("Cycle not detected");
    }
}
