package dp;

public class GridPathCount {
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // First row and first column have only 1 way
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // Fill the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 3)); // 6
        System.out.println(uniquePaths(2, 2)); // 2
        System.out.println(uniquePaths(1, 5)); // 1
        System.out.println(uniquePaths(4, 1)); // 1
        System.out.println(uniquePaths(4, 3)); // 10
    }
}