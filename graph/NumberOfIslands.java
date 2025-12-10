package graph;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j); // sink this island
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        // boundary + water check
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0'; // mark visited

        // explore neighbors (up, down, left, right)
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();

        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("Output: " + solver.numIslands(grid1)); // 1

        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Output: " + solver.numIslands(grid2)); // 3
    }
}

/*
Number of Islands
Problem Restatement
We are given a 2D grid of '1' (land) and '0' (water).
- An island is formed by connecting adjacent lands horizontally or vertically.
- We need to count how many islands exist in the grid.

Stepwise Breakdown
1. Approach
- Traverse the grid cell by cell.
- When we find a '1' (land), it means we’ve discovered a new island.
- Perform DFS (Depth-First Search) or BFS (Breadth-First Search) to mark all connected land cells as visited.
- Increment island count.
This ensures each island is counted exactly once


11110
11010
11000
00000

- Start at (0,0) → land → DFS marks all connected land.
- Entire top-left block becomes water.
- Only one island found → Output = 1.


11000
11000
00100
00011

- First block (0,0) → island #1.
- Middle (2,2) → island #2.
- Bottom-right (3,3) and (3,4) → island #3.
- Output = 3.

*/