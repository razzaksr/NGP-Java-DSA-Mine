package graph;

import java.util.*;

public class VehicleQueueCycleDetection {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static boolean[] recStack;

    static boolean dfs(int node) {
        visited[node] = true;
        recStack[node] = true;

        for (int neigh : graph.get(node)) {
            if (!visited[neigh] && dfs(neigh)) {
                return true;
            } else if (recStack[neigh]) {
                return true; // cycle found
            }
        }

        recStack[node] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // number of intersections
        int M = sc.nextInt(); // number of roads

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
        }

        visited = new boolean[N + 1];
        recStack = new boolean[N + 1];

        boolean hasCycle = false;
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && dfs(i)) {
                hasCycle = true;
                break;
            }
        }

        System.out.println(hasCycle ? "YES" : "NO");
    }
}