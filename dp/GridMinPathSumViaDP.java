package dp;

public class GridMinPathSumViaDP {
    public static int findGridMinPath(int[][] grid) {
        int lenRow = grid.length;
        int lenColumn = grid[0].length;

        // initialize 0th column of all rows
        for (int row = 1; row < lenRow; row++) {
            grid[row][0] += grid[row - 1][0];
        }

        // initialize 0th row of all columns
        for (int col = 1; col < lenColumn; col++) {
            grid[0][col] += grid[0][col - 1];
        }

        // fill the rest of the grid
        for (int row = 1; row < lenRow; row++) {
            for (int col = 1; col < lenColumn; col++) {
                grid[row][col] += Math.min(grid[row][col - 1], grid[row - 1][col]);
            }
        }

        return grid[lenRow - 1][lenColumn - 1];
    }

    public static void main(String[] args) {
        System.out.println(findGridMinPath(new int[][]{{1,3,1},{1,5,1},{4,2,1}})); // 7
        System.out.println(findGridMinPath(new int[][]{{1,2},{1,1}}));             // 3
        System.out.println(findGridMinPath(new int[][]{{5}}));                     // 5
        System.out.println(findGridMinPath(new int[][]{{1,2,3},{4,5,6}}));         // 12
        System.out.println(findGridMinPath(new int[][]{{1,1,1},{1,1,1},{1,1,1}})); // 5
    }
}
/* 
Complexity
- Time: O(m . n) where m = rows, n = columns.
- Space: O(1) extra (in-place updates).
*/