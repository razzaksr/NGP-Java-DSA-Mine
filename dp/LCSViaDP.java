package dp;

public class LCSViaDP {
    public static int findLcs(String sample, String dna) {
        int rowSize = sample.length();
        int colSize = dna.length();

        int[][] grid = new int[rowSize + 1][colSize + 1];

        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                if (sample.charAt(row - 1) == dna.charAt(col - 1)) {
                    grid[row][col] = grid[row - 1][col - 1] + 1;
                } else {
                    grid[row][col] = Math.max(grid[row - 1][col], grid[row][col - 1]);
                }
            }
        }

        return grid[rowSize][colSize];
    }

    public static void main(String[] args) {
        System.out.println(findLcs("abcde", "abc"));      // 3
        System.out.println(findLcs("abc", "def"));        // 0
        System.out.println(findLcs("aggtab", "gxtxayb")); // 4
        System.out.println(findLcs("aaaa", "aa"));        // 2
        System.out.println(findLcs("abcdef", "acf"));     // 3
    }
}
/* 
- Time Complexity: O(m . n)
- Space Complexity: O(m . n)
*/