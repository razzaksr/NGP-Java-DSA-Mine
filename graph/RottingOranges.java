package graph;

import java.util.*;

public class RottingOranges {
    public static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 2) q.offer(new int[]{i,j});
                if(grid[i][j] == 1) fresh++;
            }
        }
        
        int minutes = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        while(!q.isEmpty() && fresh > 0) {
            int size = q.size();
            for(int k=0; k<size; k++) {
                int[] cell = q.poll();
                for(int[] d: dirs) {
                    int x = cell[0]+d[0], y = cell[1]+d[1];
                    if(x<0||y<0||x>=m||y>=n||grid[x][y]!=1) continue;
                    grid[x][y] = 2;
                    fresh--;
                    q.offer(new int[]{x,y});
                }
            }
            minutes++;
        }
        
        return fresh==0 ? minutes : -1;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        int[][] grid = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(orangesRotting(grid));
        sc.close();
    }
}