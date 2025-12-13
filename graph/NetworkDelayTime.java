package graph;

import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        // Step 2: Min-heap for Dijkstra (stores [time, node])
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});

        // Step 3: Distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Step 4: Dijkstra’s algorithm
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0], node = curr[1];

            if (time > dist[node]) continue;

            if (graph.containsKey(node)) {
                for (int[] nei : graph.get(node)) {
                    int next = nei[0], weight = nei[1];
                    if (dist[next] > time + weight) {
                        dist[next] = time + weight;
                        pq.offer(new int[]{dist[next], next});
                    }
                }
            }
        }

        // Step 5: Find max time
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }

    // Driver code for testing
    public static void main(String[] args) {
        NetworkDelayTime solver = new NetworkDelayTime();

        int[][] times1 = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(solver.networkDelayTime(times1, 4, 2)); // Output: 2

        int[][] times2 = {{1,2,1}};
        System.out.println(solver.networkDelayTime(times2, 2, 1)); // Output: 1

        int[][] times3 = {{1,2,1}};
        System.out.println(solver.networkDelayTime(times3, 2, 2)); // Output: -1
    }
}

/* 
Great, let’s walk through the **priority queue (min-heap) evolution step by step** for the first test case so your trainees can *see* how Dijkstra’s algorithm expands nodes.

---

### 📚 Input Recap
```
times1 = {{2,1,1}, {2,3,1}, {3,4,1}}
n = 4, k = 2
```
Graph:
```
2 → (1,1), (3,1)
3 → (4,1)
```

---

### 🧮 Heap Evolution Trace

#### Initial State
- `dist = [∞, ∞, 0, ∞, ∞]` (node 2 = 0, others = ∞)
- Heap = `[(0,2)]`

---

#### Iteration 1
- Pop `(0,2)` → current node = 2, time = 0
- Neighbors:  
  - `(1,1)` → dist[1] = 1  
  - `(3,1)` → dist[3] = 1  
- Push `(1,1), (1,3)` into heap

Heap after iteration:
```
[(1,1), (1,3)]
```
Dist array:
```
[∞, 1, 0, 1, ∞]
```

---

#### Iteration 2
- Pop `(1,1)` → current node = 1, time = 1
- Node 1 has no neighbors → nothing added

Heap after iteration:
```
[(1,3)]
```
Dist array unchanged:
```
[∞, 1, 0, 1, ∞]
```

---

#### Iteration 3
- Pop `(1,3)` → current node = 3, time = 1
- Neighbors:  
  - `(4,1)` → dist[4] = 2  
- Push `(2,4)` into heap

Heap after iteration:
```
[(2,4)]
```
Dist array:
```
[∞, 1, 0, 1, 2]
```

---

#### Iteration 4
- Pop `(2,4)` → current node = 4, time = 2
- Node 4 has no neighbors → nothing added

Heap after iteration:
```
[]
```
Dist array unchanged:
```
[∞, 1, 0, 1, 2]
```

---

### ✅ Final Result
- All nodes reachable.  
- Maximum distance = 2.  
- Output = `2`.

---

### Visual Recap
Heap evolution sequence:
```
Start: [(0,2)]
→ [(1,1), (1,3)]
→ [(1,3)]
→ [(2,4)]
→ []
```

This shows clearly how the **min-heap always expands the shortest path first**, ensuring correctness of Dijkstra’s algorithm.

---

Would you like me to also **draw this as a timeline diagram** (nodes expanding in order with arrows showing updates) so your trainees can visualize the flow even more intuitively?




Alright S, let’s break this **Network Delay Time** code down in the same **objective + overview + stepwise trace style** you liked for Alien Dictionary.  

---

### 🎯 Objective
The goal of this program is to calculate the **minimum time required for a signal to reach all nodes in a network** starting from a given source node `k`.  
If some nodes are unreachable, return `-1`.  

This is solved using **Dijkstra’s algorithm** (shortest path in weighted graph).

---

### 📝 Overview
1. **Input**:  
   - `times`: list of edges `[u, v, w]` meaning signal travels from `u → v` in `w` units of time.  
   - `n`: number of nodes.  
   - `k`: starting node.  
2. **Process**:  
   - Build adjacency list.  
   - Use a **min-heap (priority queue)** to always expand the shortest path first.  
   - Maintain a `dist[]` array of shortest times to each node.  
   - Run Dijkstra’s algorithm until all reachable nodes are processed.  
3. **Output**:  
   - Maximum time among all shortest paths.  
   - If any node is unreachable → return `-1`.

---

### 🔍 Stepwise Explanation

#### Step 1: Build Graph
```java
Map<Integer, List<int[]>> graph = new HashMap<>();
for (int[] edge : times) {
    graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
}
```
- Each edge `[u, v, w]` means: from node `u` → go to `v` with cost `w`.  
- Stored as adjacency list: `graph[u] = list of {v, w}`.

👉 Example: `times1 = {{2,1,1},{2,3,1},{3,4,1}}`  
Graph:
```
2 : [ [1,1], [3,1] ]
3 : [ [4,1] ]
```

---

#### Step 2: Min-Heap for Dijkstra
```java
PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
pq.offer(new int[]{0, k});
```
- Heap stores `[time, node]`.  
- Start with source `k` at time `0`.

👉 Example: Start with `(0,2)`.

---

#### Step 3: Distance Array
```java
int[] dist = new int[n + 1];
Arrays.fill(dist, Integer.MAX_VALUE);
dist[k] = 0;
```
- `dist[i]` = shortest known time to reach node `i`.  
- Initially all are `∞` except source `k`.

👉 Example: `dist = [∞, ∞, 0, ∞, ∞]` for `n=4, k=2`.

---

#### Step 4: Dijkstra’s Algorithm
```java
while (!pq.isEmpty()) {
    int[] curr = pq.poll();
    int time = curr[0], node = curr[1];

    if (time > dist[node]) continue;

    if (graph.containsKey(node)) {
        for (int[] nei : graph.get(node)) {
            int next = nei[0], weight = nei[1];
            if (dist[next] > time + weight) {
                dist[next] = time + weight;
                pq.offer(new int[]{dist[next], next});
            }
        }
    }
}
```

- Always expand the node with smallest time.  
- Update neighbors if a shorter path is found.  
- Push updated distances into heap.

👉 Example trace for `times1`:
1. Pop `(0,2)` → neighbors `(1,1)` and `(3,1)` → update `dist[1]=1`, `dist[3]=1`.  
   Heap = `(1,1), (1,3)`  
2. Pop `(1,1)` → no neighbors.  
3. Pop `(1,3)` → neighbor `(4,1)` → update `dist[4]=2`.  
   Heap = `(2,4)`  
4. Pop `(2,4)` → no neighbors.  
Done.

Final `dist = [∞,1,0,1,2]`.

---

#### Step 5: Find Max Time
```java
int max = 0;
for (int i = 1; i <= n; i++) {
    if (dist[i] == Integer.MAX_VALUE) return -1;
    max = Math.max(max, dist[i]);
}
return max;
```
- If any node is unreachable (`∞`) → return `-1`.  
- Otherwise, return the maximum distance (time taken for the farthest node).

👉 Example: `max = 2` → Output = `2`.

---

### 🧮 Example Runs

#### Case 1
```
times1 = {{2,1,1},{2,3,1},{3,4,1}}, n=4, k=2
dist = [∞,1,0,1,2]
Output = 2
```

#### Case 2
```
times2 = {{1,2,1}}, n=2, k=1
dist = [∞,0,1]
Output = 1
```

#### Case 3
```
times3 = {{1,2,1}}, n=2, k=2
dist = [∞,∞,0]
Node 1 unreachable → Output = -1
```

---

### 📌 Final Outputs
```
2
1
-1
```

---

Would you like me to also **draw the heap evolution step by step** (showing what’s inside the priority queue at each iteration) so your trainees can visualize how Dijkstra expands nodes?

*/