package graph;

import java.util.*;

public class PacificAtlantic {
    static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public static List<int[]> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        for(int i=0; i<m; i++) {
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, n-1);
        }
        for(int j=0; j<n; j++) {
            dfs(heights, pacific, 0, j);
            dfs(heights, atlantic, m-1, j);
        }
        
        List<int[]> res = new ArrayList<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[]{i,j});
                }
            }
        }
        return res;
    }
    
    private static void dfs(int[][] heights, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        for(int[] d: dirs) {
            int x = i+d[0], y = j+d[1];
            if(x<0||y<0||x>=heights.length||y>=heights[0].length||visited[x][y]) continue;
            if(heights[x][y] >= heights[i][j]) {
                dfs(heights, visited, x, y);
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        int[][] heights = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                heights[i][j] = sc.nextInt();
            }
        }
        List<int[]> ans = pacificAtlantic(heights);
        for(int[] cell: ans) {
            System.out.println(cell[0] + " " + cell[1]);
        }
        sc.close();
    }
}