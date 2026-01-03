package dp;

import java.util.Scanner;

public class CorporateKnapsackAllocation {
    // Classic 0/1 Knapsack DP solution
    public static int maxProfit(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] weights = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) weights[i] = sc.nextInt();
        for (int i = 0; i < n; i++) values[i] = sc.nextInt();
        int capacity = sc.nextInt();
        System.out.println(maxProfit(weights, values, capacity));
    }

}
