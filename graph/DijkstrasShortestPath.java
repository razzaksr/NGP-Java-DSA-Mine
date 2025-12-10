package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstrasShortestPath {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();
        System.out.println(n);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        // pq stores int[] where pq poll min value considering int[1]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;

            for (Edge e : graph.get(u)) {
                if (dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    pq.offer(new int[]{e.to, dist[e.to]});
                }
            }
        }

        System.out.println("Shortest distances from source " + source + ": " + Arrays.toString(dist));
    }
    public static void main(String[] args) {
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(1, 9));
        graph.get(0).add(new Edge(2, 6));
        graph.get(0).add(new Edge(3, 5));
        graph.get(0).add(new Edge(4, 3));
        graph.get(2).add(new Edge(1, 2));
        graph.get(2).add(new Edge(3, 4));

        dijkstra(graph, 0);
    }

}
/* 
**Planning the explanation structure**

Alright, I need to write a step-by-step explanation in S's style, which involves array-driven values that flow. The structure needs to be clear with sections and annotated code for walkthroughs. I’ll keep it concise, avoid redundancy, and focus on invariants and edge cases. The sections will include an overview, data structures, execution on the graph with iterations, correctness, complexity, and a trainee checklist with tests. Also, I need to remember to avoid recursion, but trace the process, sort of like Dijkstra’s algorithm without recursion. Let’s get started.
### Dijkstra’s algorithm explained step by step (with your code)

You want a clear, array-driven walkthrough that shows exactly how values flow. Below is a complete explanation of your implementation, followed by a full trace on your sample graph so trainees can see each relaxation and queue operation.

---

## Core idea and invariants

- **Goal:** Compute shortest distances from a single source to all nodes with non-negative edge weights.
- **Invariant 1:** When a node is polled from the priority queue with cost `d`, if `d > dist[u]`, that entry is stale and skipped.
- **Invariant 2:** When an edge `(u -> v, w)` is relaxed, `dist[v]` only decreases, never increases.
- **Invariant 3:** The priority queue always pops the currently known minimum tentative distance.

---

## Data structures in your code

- **Graph:** `List<List<Edge>> graph`
  - Each `graph[u]` contains `Edge(to, weight)` for all outgoing edges from `u`.
- **Distance array:** `int[] dist`
  - Holds the best known distance from `source` to every node. Initialized to `Integer.MAX_VALUE`, except `dist[source] = 0`.
- **Priority queue:** `PriorityQueue<int[]> pq`
  - Stores pairs `[node, distance]` ordered by `distance` ascending. Can hold multiple entries for the same node; stale ones are filtered by `if (d > dist[u]) continue;`.

---

## Step-by-step execution on your sample graph

#### Input graph setup

- **Vertices:** `V = 5` → nodes `0..4`
- **Edges from code:**
  - **0 → 1 (9)**
  - **0 → 2 (6)**
  - **0 → 3 (5)**
  - **0 → 4 (3)**
  - **2 → 1 (2)**
  - **2 → 3 (4)**

#### Initial state

- **dist:** `[0, ∞, ∞, ∞, ∞]`
- **pq:** `[(0, 0)]`

We’ll show each iteration as:
- Poll from pq
- Relax outgoing edges
- Update dist and push new entries

---

### Iteration 1

- **Poll:** `(u=0, d=0)` → `d == dist[0]` (valid)
- **Outgoing edges of 0:**
  - **0 → 1 (9):**
    - **Check:** `dist[0] + 9 < dist[1]` → `0 + 9 < ∞` → true
    - **Update:** `dist[1] = 9`
    - **Push:** `(1, 9)`
  - **0 → 2 (6):**
    - **Check:** `0 + 6 < ∞` → true
    - **Update:** `dist[2] = 6`
    - **Push:** `(2, 6)`
  - **0 → 3 (5):**
    - **Check:** `0 + 5 < ∞` → true
    - **Update:** `dist[3] = 5`
    - **Push:** `(3, 5)`
  - **0 → 4 (3):**
    - **Check:** `0 + 3 < ∞` → true
    - **Update:** `dist[4] = 3`
    - **Push:** `(4, 3)`

- **dist:** `[0, 9, 6, 5, 3]`
- **pq (ordered by distance):** `(4,3), (3,5), (2,6), (1,9)`

---

### Iteration 2

- **Poll:** `(u=4, d=3)` → `d == dist[4]` (valid)
- **Outgoing edges of 4:** none
- **dist:** `[0, 9, 6, 5, 3]`
- **pq:** `(3,5), (2,6), (1,9)`

---

### Iteration 3

- **Poll:** `(u=3, d=5)` → `d == dist[3]` (valid)
- **Outgoing edges of 3:** none (in given graph)
- **dist:** `[0, 9, 6, 5, 3]`
- **pq:** `(2,6), (1,9)`

---

### Iteration 4

- **Poll:** `(u=2, d=6)` → `d == dist[2]` (valid)
- **Outgoing edges of 2:**
  - **2 → 1 (2):**
    - **Check:** `dist[2] + 2 < dist[1]` → `6 + 2 < 9` → true
    - **Update:** `dist[1] = 8`
    - **Push:** `(1, 8)`
  - **2 → 3 (4):**
    - **Check:** `dist[2] + 4 < dist[3]` → `6 + 4 < 5` → false (no update)

- **dist:** `[0, 8, 6, 5, 3]`
- **pq:** `(1,8), (1,9)`  
  Note: both `(1,8)` and `(1,9)` exist, but `(1,9)` is now stale relative to `dist[1]=8`.

---

### Iteration 5

- **Poll:** `(u=1, d=8)` → `d == dist[1]` (valid)
- **Outgoing edges of 1:** none (in given graph)
- **dist:** `[0, 8, 6, 5, 3]`
- **pq:** `(1,9)`

---

### Iteration 6

- **Poll:** `(u=1, d=9)` → check `d > dist[u]` → `9 > dist[1]=8` → true
- **Action:** skip (stale entry)
- **pq:** empty

---

### Final result

- **dist:** `[0, 8, 6, 5, 3]`
- **Meaning:** Shortest distances from source `0` to nodes `0..4`.

---

## Why this works (correctness intuition)

- **Greedy selection:** Always expands the frontier from the node with the smallest current distance.
- **Monotone relaxation:** Non-negative weights ensure that once the minimum distance to a node is popped, no future path can beat it; stale entries are filtered via `if (d > dist[u]) continue`.
- **Priority queue discipline:** Enforces exploration in order of increasing tentative distance, guaranteeing optimality.

---

## Complexity and constraints

- **Time complexity:** `O((V + E) log V)` due to heap operations on relaxations and extracts.
- **Space complexity:** `O(V + E)` for the graph plus `O(V)` for `dist` and PQ entries.
- **Weight constraints:** Requires non-negative edge weights for correctness with this lazy-stale filtering approach.

---

## Trainer’s checklist and test ideas

- **Edge-case tests:**
  - **Single node:** `V=1`, no edges → dist `[0]`.
  - **Disconnected nodes:** ensure `Integer.MAX_VALUE` remains for unreachable targets.
  - **Multiple edges to same node:** verify stale entry skipping works.
  - **Graph with zero-weight edges:** confirm monotonic non-negativity still holds.

- **Concept checks for trainees:**
  - **Lead-in:** Explain why negative weights break the greedy choice.
  - **Stale detection:** Show how PQ can contain outdated entries and why the `if (d > dist[u]) continue` is essential.
  - **Relaxation discipline:** Have them trace one node’s updates and confirm strictly decreasing `dist[v]`.

If you want, I can convert this into a HackerRank-style prompt with sample inputs, detailed expected outputs, and a “trace mode” printing PQ and dist at each step to match your mentoring style.

*/