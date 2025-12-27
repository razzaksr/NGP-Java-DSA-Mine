package dc;

public class SearchInMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Map mid back to 2D indices
            int row = mid / cols;
            int col = mid % cols;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        System.out.println(searchMatrix(matrix1, 3));   // true
        System.out.println(searchMatrix(matrix1, 13));  // false
    }
}
/* 
Stepwise Explanation
- Observation:
- Each row is sorted.
- First element of each row is greater than the last element of the previous row.
- So the entire matrix can be treated as a sorted 1D array.
- Binary Search:
- Treat indices from 0 to rows*cols - 1.
- Convert mid back to (row, col) using:
- row = mid / cols
- col = mid % cols
- Compare matrix[row][col] with target.
- Termination:
- If found → return true.
- If not found after loop → return false.
Complexity
- Time: O(\log (m\cdot n)) — binary search over all elements.
- Space: O(1) — only a few variables used.
*/