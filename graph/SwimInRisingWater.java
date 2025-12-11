package graph;

import java.util.*;

public class SwimInRisingWater {
    static class Cell {
        int row, col, elevation;
        Cell(int r, int c, int e) {
            row = r; col = c; elevation = e;
        }
        public String toString(){
            return "\nrow:"+row+",col:"+col+",elevation:"+elevation+"\n";
        }
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        
        // Min-Heap based on elevation
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.elevation));
        
        // Start from (0,0)
        pq.add(new Cell(0, 0, grid[0][0]));
        visited[0][0] = true;
        
        int maxElevation = 0;
        
        // Directions: up, down, left, right
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            System.out.println("polled cell "+curr);
            maxElevation = Math.max(maxElevation, curr.elevation);
            System.out.println("New max elevation "+maxElevation);
            // If we reached destination
            if (curr.row == n-1 && curr.col == n-1) {
                System.out.println("Reached");
                return maxElevation;
            }
            
            // Explore neighbors
            for (int[] d : dirs) {
                int nr = curr.row + d[0];
                int nc = curr.col + d[1];
                System.out.println("neighbor's row "+nr+" col "+nc);
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    System.out.println("new visit @ "+nr+" "+nc);
                    pq.add(new Cell(nr, nc, grid[nr][nc]));
                    System.out.println("After new visit queue "+pq);
                }
            }
        }
        
        return -1; // Should never happen
    }

    // Driver code for testing
    public static void main(String[] args) {
        SwimInRisingWater obj = new SwimInRisingWater();
        
        
        
        int[][] grid2 = {
            {0,1,2,3,4},
            {24,23,22,21,5},
            {12,13,14,15,16},
            {11,17,18,19,20},
            {10,9,8,7,6}
        };
        System.out.println(obj.swimInWater(grid2)); // Output: 16
    }
}
/* 
Step 1: Problem Restatement in Simple Terms
- We have an n x n grid.
- Each cell has an elevation.
- Water rises over time t.
- At time t, you can only move through cells with elevation <= t.
- Goal: Find the minimum t such that you can travel from (0,0) to (n-1,n-1).

Step 2: Core Idea
- This is essentially a minimum path problem where the "cost" is the maximum elevation encountered along the path.
- We want the path from start to end that minimizes the maximum elevation.
- That maximum elevation is the answer (since water must rise to at least that level).

Step 3: Algorithm Choice
- Use Dijkstra’s algorithm / Min-Heap BFS:
- Treat each cell as a node.
- Priority Queue ensures we always expand the lowest elevation cell first.
- Track the maximum elevation seen so far.
- Once we reach (n-1,n-1), that maximum elevation is the minimum time.

Step 4: Flow of Values
- Start at (0,0) with elevation grid[0][0].
- Push into a min-heap: (elevation, row, col).
- Pop the smallest elevation cell.
- For each neighbor (up, down, left, right):
- If not visited, push into heap with its elevation.
- Maintain maxElevation = max(maxElevation, currentCellElevation).
- Stop when we pop (n-1,n-1) → return maxElevation.

Step 5: Trace Example Input: [[0,2],[1,3]]
- Start (0,0) → elevation 0 → maxElevation = 0.
- Neighbors: (1,0) elevation 1, (0,1) elevation 2.
- Heap pops (1,0) → maxElevation = 1.
- Next neighbor (1,1) elevation 3.
- Heap pops (0,1) → maxElevation = 2.
- Heap pops (1,1) → maxElevation = 3 → reached destination. Answer = 3.


Trace:
I'll explain this **Swim in Rising Water** problem step by step with a detailed walkthrough.

## Problem Understanding

You're in a grid where each cell has an elevation value. You start at `(0,0)` and want to reach `(n-1, n-1)`. At time `t`, you can only walk on cells with elevation ≤ `t`. Find the minimum time needed to reach the destination.

**Key Insight:** The answer is the **maximum elevation** encountered on the optimal path from start to end.

---

## Algorithm Strategy

This uses **Dijkstra's algorithm** / **Modified BFS with Priority Queue**:
- Use a **min-heap** to always explore the cell with the **lowest elevation** first
- Track the **maximum elevation** seen so far on the path
- This greedy approach ensures we find the path that minimizes the maximum elevation

---

## Step-by-Step Execution

Let me trace through your grid:

```
Grid:
 0   1   2   3   4
24  23  22  21   5
12  13  14  15  16
11  17  18  19  20
10   9   8   7   6
```

### **Initial State**

```java
visited[0][0] = true
pq = [(0,0,elevation=0)]
maxElevation = 0
```

---

### **Iteration 1**

**Poll:** `Cell(row=0, col=0, elevation=0)`
```
maxElevation = max(0, 0) = 0
```

**Explore neighbors (up, down, left, right):**
- **Right (0,1):** elevation=1, not visited ✓
  - Mark visited, add to PQ
- **Down (1,0):** elevation=24, not visited ✓
  - Mark visited, add to PQ

**PQ after iteration 1:**
```
[(0,1,1), (1,0,24)]  // Sorted by elevation
```

---

### **Iteration 2**

**Poll:** `Cell(row=0, col=1, elevation=1)`
```
maxElevation = max(0, 1) = 1
```

**Explore neighbors:**
- **Right (0,2):** elevation=2, not visited ✓
- **Down (1,1):** elevation=23, not visited ✓

**PQ after iteration 2:**
```
[(0,2,2), (1,1,23), (1,0,24)]
```

---

### **Iteration 3**

**Poll:** `Cell(row=0, col=2, elevation=2)`
```
maxElevation = max(1, 2) = 2
```

**Explore neighbors:**
- **Right (0,3):** elevation=3, not visited ✓
- **Down (1,2):** elevation=22, not visited ✓

**PQ after iteration 3:**
```
[(0,3,3), (1,2,22), (1,1,23), (1,0,24)]
```

---

### **Iteration 4**

**Poll:** `Cell(row=0, col=3, elevation=3)`
```
maxElevation = max(2, 3) = 3
```

**Explore neighbors:**
- **Right (0,4):** elevation=4, not visited ✓
- **Down (1,3):** elevation=21, not visited ✓

**PQ after iteration 4:**
```
[(0,4,4), (1,3,21), (1,2,22), (1,1,23), (1,0,24)]
```

---

### **Iteration 5**

**Poll:** `Cell(row=0, col=4, elevation=4)`
```
maxElevation = max(3, 4) = 4
```

**Explore neighbors:**
- **Down (1,4):** elevation=5, not visited ✓

**PQ after iteration 5:**
```
[(1,4,5), (1,3,21), (1,2,22), (1,1,23), (1,0,24)]
```

---

### **Iteration 6**

**Poll:** `Cell(row=1, col=4, elevation=5)`
```
maxElevation = max(4, 5) = 5
```

**Explore neighbors:**
- **Down (2,4):** elevation=16, not visited ✓

**PQ after iteration 6:**
```
[(2,4,16), (1,3,21), (1,2,22), (1,1,23), (1,0,24)]
```

---

### **Iteration 7**

**Poll:** `Cell(row=2, col=4, elevation=16)`
```
maxElevation = max(5, 16) = 16
```

**Explore neighbors:**
- **Down (3,4):** elevation=20, not visited ✓
- **Left (2,3):** elevation=15, not visited ✓

**PQ after iteration 7:**
```
[(2,3,15), (3,4,20), (1,3,21), (1,2,22), (1,1,23), (1,0,24)]
```

---

### **Key Iterations Continue...**

The algorithm continues polling the cell with the **lowest elevation** from the PQ. Eventually:

**Poll:** `Cell(row=4, col=4, elevation=6)`
```
maxElevation = max(16, 6) = 16  // 16 remains the max
```

**Check destination:**
```java
if (curr.row == 4 && curr.col == 4) {
    return maxElevation; // Return 16
}
```

---

## Why This Works

1. **Priority Queue ensures greedy selection:** Always explore the path with the smallest elevation increase next
2. **Track maximum elevation:** The bottleneck (highest point) on the path determines when you can complete the journey
3. **Visited array prevents cycles:** Each cell is processed once

## Optimal Path Found

The path taken: `(0,0)→(0,1)→(0,2)→(0,3)→(0,4)→(1,4)→(2,4)→(3,4)→(4,4)`

Elevations: `0→1→2→3→4→5→16→20→6`

**Maximum elevation = 16** (the bottleneck at position `(2,4)`)

---

## Time Complexity

- **Time:** O(n² log n²) = O(n² log n) - Each cell added to PQ once
- **Space:** O(n²) - For visited array and PQ

This is essentially **Dijkstra's algorithm** where the "cost" is the maximum elevation encountered rather than cumulative distance.

Great observation! Let me explain why it moved to `(2,4)` instead of `(1,3)` after reaching `(1,4)`.

## The Key: Priority Queue (Min-Heap)

The algorithm doesn't just explore neighbors in order. It **always picks the cell with the LOWEST elevation** from the priority queue.

---

## What Happened at Position (1,4)

When at `(1,4)` with elevation `5`:

**Neighbors explored:**
- **Down (2,4):** elevation = **16** ✓ Added to PQ
- **Left (1,3):** elevation = **21** ✓ Added to PQ (already visited from earlier? Let me check...)

Actually, let me trace this more carefully:

---

## Detailed Trace After (1,4)

### At Iteration 6 - Position (1,4)

**Current PQ before polling (1,4):**
```
[(1,4,5), (1,3,21), (1,2,22), (1,1,23), (1,0,24)]
```

**Poll:** `(1,4, elevation=5)`

**Explore neighbors from (1,4):**
- **Up (0,4):** Already visited ✗
- **Down (2,4):** elevation=16, not visited ✓ → Add to PQ
- **Left (1,3):** elevation=21, not visited ✓ → Add to PQ
- **Right:** Out of bounds ✗

**PQ after exploring (1,4):**
```
[(2,4,16), (1,3,21), (1,3,21), (1,2,22), (1,1,23), (1,0,24)]
```

Wait, there might be a duplicate `(1,3)` here. Let me reconsider...

---

## Actually, Here's What Really Happens

When we polled `(0,3)` earlier (Iteration 4), we added:
- `(1,3, elevation=21)` to the PQ

So `(1,3)` was **already added** to the PQ before we even reached `(1,4)`.

When at `(1,4)`, we try to add neighbors:
- **Left (1,3):** But the `visited` array check prevents adding it again:
  ```java
  if (!visited[nr][nc]) {  // This is FALSE for (1,3)
      visited[nr][nc] = true;
      pq.add(new Cell(nr, nc, grid[nr][nc]));
  }
  ```

So `(1,3)` was NOT added again from `(1,4)`.

---

## Why (2,4) is Chosen Next

**PQ state after polling (1,4):**
```
[(2,4,16), (1,3,21), (1,2,22), (1,1,23), (1,0,24)]
                ↑
         Min elevation = 16
```

The priority queue **automatically sorts by elevation**. Since:
- `(2,4)` has elevation **16**
- `(1,3)` has elevation **21**

The min-heap picks `(2,4)` next because **16 < 21**.

---

## Visual Representation

```
Path so far: (0,0) → (0,1) → (0,2) → (0,3) → (0,4) → (1,4)
            [  0  ] → [ 1 ] → [ 2 ] → [ 3 ] → [ 4 ] → [ 5 ]

Options in PQ:
- (2,4): elevation 16  ← CHOSEN (lowest)
- (1,3): elevation 21
- (1,2): elevation 22
- (1,1): elevation 23
- (1,0): elevation 24
```

---

## Summary

**Why it went to (2,4) instead of (1,3):**

1. **Priority Queue orders by elevation** (min-heap)
2. Cell `(2,4)` has elevation **16**
3. Cell `(1,3)` has elevation **21**
4. Since **16 < 21**, the algorithm chooses `(2,4)` first
5. This greedy strategy ensures we find the path with the **minimum maximum elevation**

This is the beauty of using a **priority queue with Dijkstra's approach** - it automatically explores the most promising (lowest elevation) paths first, ensuring we find the optimal solution! 🎯


*/