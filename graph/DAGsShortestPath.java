package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DAGsShortestPath {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to; this.weight = weight;
        }
    }

    public static void shortestPathDAG(List<List<Edge>> graph, int source) {
        int V = graph.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Topological sort
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) topoSort(i, visited, stack, graph);
        }

        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (dist[u] != Integer.MAX_VALUE) {
                for (Edge e : graph.get(u)) {
                    if (dist[u] + e.weight < dist[e.to]) {
                        dist[e.to] = dist[u] + e.weight;
                    }
                }
            }
        }

        System.out.println("Shortest distances from source " + source + ": " + Arrays.toString(dist));
    }
    private static void topoSort(int v, boolean[] visited, Stack<Integer> stack, List<List<Edge>> graph) {
        visited[v] = true;
        for (Edge e : graph.get(v)) {
            if (!visited[e.to]) topoSort(e.to, visited, stack, graph);
        }
        stack.push(v);
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(1, 5));
        graph.get(0).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 6));
        graph.get(1).add(new Edge(2, 2));
        graph.get(2).add(new Edge(4, 4));
        graph.get(2).add(new Edge(5, 2));
        graph.get(2).add(new Edge(3, 7));
        graph.get(3).add(new Edge(4, -1));
        graph.get(4).add(new Edge(5, -2));

        shortestPathDAG(graph, 1);
    }
}
/* 
**Processing Stack and Relaxation Steps**

Stack's top order is [5,4,3,2,1,0], matching the push sequence. When popping, the order will be 0,1,2,3,4,5, which is a valid topological order. I'll process nodes in this order. Starting with node 0, I skip it as dist[0] is ∞. For node 1, I update dist[3] and dist[2] based on the edges. For node 2, I relax edges and update dist[4] and dist[5]. I continue this for the other nodes. The final distances are [∞,0,2,6,5,3].
### Shortest path in DAG explained step by step (with your code)

You’ll get a clear, array-driven walkthrough showing how values flow through topological order and relaxation. Then we’ll trace your exact sample input to reveal each update.

---

## Core idea and invariants

- **Goal:** Find shortest paths from a single source in a Directed Acyclic Graph (DAG), allowing negative weights without cycles.
- **Key principle:** Compute a topological order, then relax edges once in that order. No reprocessing is needed because DAGs have no cycles.
- **Invariant 1:** When a vertex appears before another in topological order, all paths from the former to the latter are already finalized when we process the latter.
- **Invariant 2:** Distances only decrease during relaxation; unreachable nodes remain at infinity.

---

## Data structures in your code

- **Graph:** `List<List<Edge>> graph`
  - Each `graph[u]` lists `Edge(to, weight)` for outgoing edges of `u`.
- **Distance array:** `int[] dist`
  - Initialized to `Integer.MAX_VALUE` for all nodes; `dist[source] = 0`.
- **Topological stack:** `Stack<Integer> stack`
  - Filled via DFS `topoSort`: push a node after exploring all its outgoing edges. Popping yields a topological order.

---

## Topological sort trace on your sample graph

#### Graph
- **0 → 1 (5), 0 → 2 (3)**
- **1 → 3 (6), 1 → 2 (2)**
- **2 → 4 (4), 2 → 5 (2), 2 → 3 (7)**
- **3 → 4 (-1)**
- **4 → 5 (-2)**
- **5:** none

#### DFS push order (node is pushed after exploring children)
- Visit 0 → visit 1 → visit 3 → visit 4 → visit 5 → push 5 → push 4 → push 3 → visit 2 → push 2 → push 1 → push 0
- **Stack after DFS (bottom → top):** `[5, 4, 3, 2, 1, 0]`

#### Topological processing order (pop from stack)
- **Pop sequence:** `0, 1, 2, 3, 4, 5`

---

## Relaxation pass trace from source = 1

#### Initial state
- **dist:** `[∞, 0, ∞, ∞, ∞, ∞]`

We process nodes in pop order: `0, 1, 2, 3, 4, 5`.

### Process u = 0
- **Check:** `dist[0] == ∞` → unreachable, skip relaxation.

### Process u = 1
- **dist[1] = 0**
- **Relax 1 → 3 (6):**
  - **Check:** `0 + 6 < ∞` → true
  - **Update:** `dist[3] = 6`
- **Relax 1 → 2 (2):**
  - **Check:** `0 + 2 < ∞` → true
  - **Update:** `dist[2] = 2`
- **dist now:** `[∞, 0, 2, 6, ∞, ∞]`

### Process u = 2
- **dist[2] = 2**
- **Relax 2 → 4 (4):**
  - **Check:** `2 + 4 < ∞` → true
  - **Update:** `dist[4] = 6`
- **Relax 2 → 5 (2):**
  - **Check:** `2 + 2 < ∞` → true
  - **Update:** `dist[5] = 4`
- **Relax 2 → 3 (7):**
  - **Check:** `2 + 7 < 6` → false (no update)
- **dist now:** `[∞, 0, 2, 6, 6, 4]`

### Process u = 3
- **dist[3] = 6**
- **Relax 3 → 4 (-1):**
  - **Check:** `6 + (-1) < 6` → true
  - **Update:** `dist[4] = 5`
- **dist now:** `[∞, 0, 2, 6, 5, 4]`

### Process u = 4
- **dist[4] = 5**
- **Relax 4 → 5 (-2):**
  - **Check:** `5 + (-2) < 4` → true
  - **Update:** `dist[5] = 3`
- **dist now:** `[∞, 0, 2, 6, 5, 3]`

### Process u = 5
- **Outgoing edges:** none

---

## Final result

- **Shortest distances from source 1:** `[∞, 0, 2, 6, 5, 3]`
- **Interpretation:** Nodes unreachable from 1 remain `∞`. Paths finalized in one pass because topological order ensures all prerequisites are processed first.

---

## Why this works (correctness intuition)

- **Acyclic structure:** No cycles mean every path length is finite and bounded by topological order.
- **Single pass relaxation:** Processing nodes in topological order guarantees that when we relax edges out of `u`, `dist[u]` is already optimal.
- **Negative weights allowed:** Without cycles, negative edges can’t create infinite descent, so shortest paths remain well-defined.

---

## Complexity and constraints

- **Time complexity:** `O(V + E)` for topo sort plus `O(E)` for relaxation → total `O(V + E)`.
- **Space complexity:** `O(V + E)` for graph and `O(V)` for arrays/stack.
- **Constraint:** Graph must be a DAG. If a cycle exists, topological sort is invalid and correctness breaks.

---

## Trainer’s checklist and test ideas

- **Concept checks:**
  - **Topological guarantee:** Why does order prevent the need for repeated relaxations?
  - **Negative edges:** Why are they safe in DAGs but risky elsewhere?
  - **Unreachable nodes:** How does `∞` propagate through relaxation?

- **Edge-case tests:**
  - **Source with no outgoing edges:** all other nodes remain `∞`.
  - **Multiple sources effect:** run from different source nodes and compare.
  - **Long chain DAG:** verify linear-time behavior and exact accumulated weights.
  - **Edge with zero weight:** check that distances update correctly without PQ.

If you want, I can add a trace-printing version of this code that logs the stack order and each relaxation decision, mirroring the iteration format used here.

*/