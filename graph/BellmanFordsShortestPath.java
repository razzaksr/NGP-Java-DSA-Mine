package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordsShortestPath {
    static class Edge {
        int u, v, weight;
        Edge(int u, int v, int weight) {
            this.u = u; this.v = v; this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, int V, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        for (int i = 1; i < V; i++) {
            for (Edge e : edges) {
                if (dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.weight < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.weight;
                }
            }
        }
        System.out.println(Arrays.toString(dist));
        // Check for negative weight cycles
        for (Edge e : edges) {
            if (dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.weight < dist[e.v]) {
                System.out.println("Graph contains a negative weight cycle!");
                return;
            }
        }
        System.out.println("Shortest distances from source " + source + ": " + Arrays.toString(dist));
    }
    public static void main(String[] args) {
        int V = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, -1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(3, 2, 5));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(4, 3, -3));

        bellmanFord(edges, V, 0);
    }
}
/*
Perfect, S 👌. Let’s break down **Bellman-Ford’s shortest path algorithm** in the same **stepwise, array-driven style** you prefer, so trainees can clearly see how values flow and why this algorithm differs from Dijkstra.

---

## 🧩 Core Idea

- **Goal:** Find shortest paths from a single source to all vertices, even when edge weights can be negative.  
- **Key principle:** Repeatedly relax all edges **V−1 times** (where V = number of vertices).  
- **Why V−1?** The longest possible simple path in a graph has at most V−1 edges.  
- **Extra step:** After relaxation, check once more for improvements → if any edge can still be relaxed, a **negative weight cycle** exists.

---

## ⚙️ Data Structures in Your Code

- **Edges list:** `List<Edge> edges`  
  Each edge has `(u, v, weight)` meaning an edge from `u → v` with cost `weight`.  
- **Distance array:** `int[] dist`  
  Stores shortest known distance from `source` to every vertex. Initialized to `∞` except `dist[source] = 0`.  

---

## 🔄 Step-by-Step Execution on Your Sample Graph

### Graph setup
Vertices: `V = 5` → nodes `0..4`  
Edges:  
- 0 → 1 (-1)  
- 0 → 2 (4)  
- 1 → 2 (3)  
- 1 → 3 (2)  
- 1 → 4 (2)  
- 3 → 2 (5)  
- 3 → 1 (1)  
- 4 → 3 (-3)  

---

### Initial state
- **dist:** `[0, ∞, ∞, ∞, ∞]`  
- Source = 0  

---

### Iteration 1 (first relaxation pass)
Relax all edges:

- 0→1 (-1): `0 + (-1) < ∞` → `dist[1] = -1`  
- 0→2 (4): `0 + 4 < ∞` → `dist[2] = 4`  
- 1→2 (3): `-1 + 3 < 4` → `dist[2] = 2`  
- 1→3 (2): `-1 + 2 < ∞` → `dist[3] = 1`  
- 1→4 (2): `-1 + 2 < ∞` → `dist[4] = 1`  
- 3→2 (5): `1 + 5 < 2` → false  
- 3→1 (1): `1 + 1 < -1` → false  
- 4→3 (-3): `1 + (-3) < 1` → `dist[3] = -2`  

**dist after Iteration 1:** `[0, -1, 2, -2, 1]`

---

### Iteration 2
Relax all edges again:

- 0→1 (-1): `0 + (-1) < -1` → false  
- 0→2 (4): `0 + 4 < 2` → false  
- 1→2 (3): `-1 + 3 < 2` → false  
- 1→3 (2): `-1 + 2 < -2` → false  
- 1→4 (2): `-1 + 2 < 1` → false  
- 3→2 (5): `-2 + 5 < 2` → `dist[2] = 3`  
- 3→1 (1): `-2 + 1 < -1` → `dist[1] = -1` (no change)  
- 4→3 (-3): `1 + (-3) < -2` → false  

**dist after Iteration 2:** `[0, -1, 3, -2, 1]`

---

### Iteration 3
Relax all edges again:

- 0→1 (-1): no change  
- 0→2 (4): no change  
- 1→2 (3): `-1 + 3 < 3` → `dist[2] = 2`  
- 1→3 (2): no change  
- 1→4 (2): no change  
- 3→2 (5): `-2 + 5 < 2` → `dist[2] = 3`  
- 3→1 (1): no change  
- 4→3 (-3): no change  

**dist after Iteration 3:** `[0, -1, 3, -2, 1]`

---

### Iteration 4
Relax all edges again (final pass before cycle check):

- No further improvements → distances remain `[0, -1, 3, -2, 1]`.

---

### Negative cycle check
Run through all edges once more:
- If any edge can still reduce a distance, a negative cycle exists.  
- In this graph, **no further relaxation is possible**, so **no negative cycle**.

---

### ✅ Final Result
Shortest distances from source `0`:  
`[0, -1, 3, -2, 1]`

---

## 📝 Why Bellman-Ford Works

- **Exhaustive relaxation:** Every edge is considered in every iteration, ensuring correctness even with negative weights.  
- **V−1 passes:** Guarantees shortest paths are found if no negative cycles exist.  
- **Cycle detection:** One extra pass detects if improvements are still possible → signals a negative cycle.  

---

## 📊 Complexity

- **Time:** `O(V * E)` → slower than Dijkstra but handles negative weights.  
- **Space:** `O(V)` for distance array.  

---

## 🎯 Trainer’s Checklist

- **Concept checks:**  
  - Why do we need V−1 iterations?  
  - What happens if there’s a negative cycle?  
  - Compare with Dijkstra: why PQ is not used here?  

- **Edge-case tests:**  
  - Graph with all positive weights → should match Dijkstra’s result.  
  - Graph with negative cycle → algorithm must detect and report.  
  - Disconnected graph → unreachable nodes remain `∞`.  

---

Would you like me to **add a trace-printing version of this code** (printing `dist[]` after each iteration) so your trainees can visually follow the relaxation process, just like we did in the explanation?

*/