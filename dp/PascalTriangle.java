package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        // Base case: first row
        triangle.add(Arrays.asList(1));
        for (int row = 1; row < numRows; row++) {
            List<Integer> prevRow = triangle.get(row - 1);
            List<Integer> newRow = new ArrayList<>();
            // First element is always 1
            newRow.add(1);
            // Middle elements: sum of two above
            for (int j = 1; j < row; j++) {
                newRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            // Last element is always 1
            newRow.add(1);
            triangle.add(newRow);
        }
        return triangle;
    }
    public static void main(String[] args) {
        System.out.println(generate(5));
        // Output:
        // [
        //   [1],
        //   [1,1],
        //   [1,2,1],
        //   [1,3,3,1],
        //   [1,4,6,4,1]
        // ]
    }
}
/* 
Stepwise Explanation
- Base case:
- First row is [1].
- Transition (DP relation):
- Each element in row r (except first and last) is the sum of two elements directly above it:
triangle[r][c]=triangle[r-1][c-1]+triangle[r-1][c]- Iteration:
- Build rows one by one until numRows.
Complexity
- Time: O(n^2) — each row has up to n elements.
- Space: O(n^2) — storing the triangle.
*/