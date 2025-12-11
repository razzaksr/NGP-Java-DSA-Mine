package graph;

import java.util.*;

public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        // Step 1: Mark all 'O's connected to border
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);         // left border
            dfs(board, i, n - 1);     // right border
        }
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);         // top border
            dfs(board, m - 1, j);     // bottom border
        }

        // Step 2: Flip all remaining 'O' to 'X', and restore '#' to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // captured
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O'; // restore
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O') return;

        board[i][j] = '#'; // mark as safe
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

    // For testing
    public static void main(String[] args) {
        SurroundedRegions sr = new SurroundedRegions();

        char[][] board1 = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        sr.solve(board1);
        System.out.println(Arrays.deepToString(board1));

        char[][] board2 = {
            {'X'}
        };
        sr.solve(board2);
        System.out.println(Arrays.deepToString(board2));
    }
}