package graph;

import java.util.*;

public class SwimInRisingWater {
    static class Cell {
        int row, col, elevation;
        Cell(int r, int c, int e) {
            row = r; col = c; elevation = e;
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
            maxElevation = Math.max(maxElevation, curr.elevation);
            
            // If we reached destination
            if (curr.row == n-1 && curr.col == n-1) {
                return maxElevation;
            }
            
            // Explore neighbors
            for (int[] d : dirs) {
                int nr = curr.row + d[0];
                int nc = curr.col + d[1];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.add(new Cell(nr, nc, grid[nr][nc]));
                }
            }
        }
        
        return -1; // Should never happen
    }

    // Driver code for testing
    public static void main(String[] args) {
        SwimInRisingWater obj = new SwimInRisingWater();
        
        int[][] grid1 = {{0,2},{1,3}};
        System.out.println(obj.swimInWater(grid1)); // Output: 3
        
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
- We have an n\times n grid.
- Each cell has an elevation.
- Water rises over time t.
- At time t, you can only move through cells with elevation \leq t.
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

*/