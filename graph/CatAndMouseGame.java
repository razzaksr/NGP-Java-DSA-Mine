package graph;

import java.util.*;

public class CatAndMouseGame {
    // Constants for outcomes
    private static final int DRAW = 0;
    private static final int MOUSE_WIN = 1;
    private static final int CAT_WIN = 2;

    public static int catMouseGame(int[][] graph) {
        int n = graph.length;
        // dp[mouse][cat][turn] = result
        int[][][] dp = new int[n][n][2];
        return helper(graph, dp, 1, 2, 0);
    }

    private static int helper(int[][] graph, int[][][] dp, int mouse, int cat, int turn) {
        if (mouse == 0) return MOUSE_WIN;
        if (cat == mouse) return CAT_WIN;
        if (dp[mouse][cat][turn] != 0) return dp[mouse][cat][turn];

        dp[mouse][cat][turn] = DRAW; // mark as visited to detect cycles

        if (turn == 0) { // Mouse's turn
            boolean catWin = true;
            for (int next : graph[mouse]) {
                int res = helper(graph, dp, next, cat, 1);
                if (res == MOUSE_WIN) return dp[mouse][cat][turn] = MOUSE_WIN;
                if (res != CAT_WIN) catWin = false;
            }
            if (catWin) return dp[mouse][cat][turn] = CAT_WIN;
        } else { // Cat's turn
            boolean mouseWin = true;
            for (int next : graph[cat]) {
                if (next == 0) continue; // Cat cannot go to hole
                int res = helper(graph, dp, mouse, next, 0);
                if (res == CAT_WIN) return dp[mouse][cat][turn] = CAT_WIN;
                if (res != MOUSE_WIN) mouseWin = false;
            }
            if (mouseWin) return dp[mouse][cat][turn] = MOUSE_WIN;
        }
        return dp[mouse][cat][turn];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                graph[i] = new int[0];
            } else {
                String[] parts = line.split(" ");
                graph[i] = new int[parts.length];
                for (int j = 0; j < parts.length; j++) {
                    graph[i][j] = Integer.parseInt(parts[j]);
                }
            }
        }
        System.out.println(catMouseGame(graph));
        sc.close();
    }
}