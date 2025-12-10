package graph;

import java.util.*;


// DFS
public class LibraryNetworkConnectedComponents {
    static List<List<Integer>> graph;
    static boolean[] visited;

    static void dfs(int node) {
        visited[node] = true;
        for (int neigh : graph.get(node)) {
            if (!visited[neigh]) {
                dfs(neigh);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // number of branches
        int M = sc.nextInt(); // number of connections

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u); // undirected
        }

        visited = new boolean[N + 1];
        int components = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
                components++;
            }
        }

        System.out.println(components);
    }
}