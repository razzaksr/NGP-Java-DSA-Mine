package PST.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);
        int n = 3;
        
        // Print rotated matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
                if (j < n - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Step 1: Transpose the matrix (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int index= 0; index < matrix.length; index++){
            
        }
        // mat.forEach(m->System.out.println(Arrays.toString(m)));
        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}
/*

## Algorithm Explanation:

**Two-Step Approach** - Transpose then Reverse:

### Step 1: Transpose the Matrix
Convert rows to columns (swap elements across the diagonal):
- `matrix[i][j]` ↔ `matrix[j][i]`

### Step 2: Reverse Each Row
Flip each row horizontally

## Visual Example

For `[[1,2,3],[4,5,6],[7,8,9]]`:

**Original Matrix:**
```
1  2  3
4  5  6
7  8  9
```

**After Transpose (Step 1):**
```
1  4  7    (row 0: was column 0)
2  5  8    (row 1: was column 1)
3  6  9    (row 2: was column 2)
```

**After Reverse Each Row (Step 2):**
```
7  4  1    (reversed row 0)
8  5  2    (reversed row 1)
9  6  3    (reversed row 2)
```

**Result: 90° Clockwise Rotation!** ✓

## Detailed Walkthrough

### Step 1: Transpose
```
Original:           After Transpose:
[0][0]=1 [0][1]=2   [0][0]=1 [0][1]=4 [0][2]=7
[1][0]=4 [1][1]=5   [1][0]=2 [1][1]=5 [1][2]=8
[2][0]=7 [2][1]=8   [2][0]=3 [2][1]=6 [2][2]=9

Swaps performed:
- matrix[0][1] ↔ matrix[1][0]  (2 ↔ 4)
- matrix[0][2] ↔ matrix[2][0]  (3 ↔ 7)
- matrix[1][2] ↔ matrix[2][1]  (6 ↔ 8)
```

### Step 2: Reverse Each Row
```
Row 0: [1, 4, 7] → [7, 4, 1]
Row 1: [2, 5, 8] → [8, 5, 2]
Row 2: [3, 6, 9] → [9, 6, 3]
 */