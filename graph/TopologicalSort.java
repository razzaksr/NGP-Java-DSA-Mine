package graph;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // number of vertices
        int M = sc.nextInt(); // number of edges

        // adjacency as Map<Integer, List<Integer>>
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            // add edge u -> v
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);

            // count incoming edge
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            topoOrder.add(node);

            // get neighbors safely
            List<Integer> neighbors = graph.getOrDefault(node, Collections.emptyList());
            for (int neigh : neighbors) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    q.add(neigh);
                }
            }
        }

        if (topoOrder.size() == N) {
            for (int x : topoOrder) {
                System.out.print(x + " ");
            }
        } else {
            System.out.println("IMPOSSIBLE"); // cycle detected
        }
    }
}