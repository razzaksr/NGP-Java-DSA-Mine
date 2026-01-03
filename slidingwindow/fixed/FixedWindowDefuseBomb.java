package slidingwindow.fixed;

import java.util.*;

public class FixedWindowDefuseBomb {
    public static int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];

        if (k == 0) return result; // all zeros

        int sum = 0;

        if (k > 0) {
            // initialize first window
            for (int i = 1; i <= k; i++) {
                sum += code[i % n];
            }
            result[0] = sum;

            // slide window forward
            for (int i = 1; i < n; i++) {
                sum -= code[i];
                sum += code[(i + k) % n];
                result[i] = sum;
            }
        } else {
            k = -k; // make positive
            // initialize first window
            for (int i = 1; i <= k; i++) {
                sum += code[(n - i) % n];
            }
            result[0] = sum;

            // slide window backward
            for (int i = 1; i < n; i++) {
                sum -= code[(n - i) % n];
                sum += code[(n - i + k) % n];
                result[i] = sum;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(decrypt(new int[]{5,7,1,4}, 3)));  // [12,10,16,13]
        System.out.println(Arrays.toString(decrypt(new int[]{1,2,3,4}, 0)));  // [0,0,0,0]
        System.out.println(Arrays.toString(decrypt(new int[]{2,4,9,3}, -2))); // [12,5,6,13]
    }
}
/* 
Stepwise Trace (Your Style)
Take code = [5,7,1,4], k = 3.
- Initialize first window (next 3 after index 0):
- Sum = 7 + 1 + 4 = 12 → result[0] = 12
- Slide window to index 1:
- Remove code[1] = 7, add code[(1+3)%4 = 0] = 5
- Sum = 12 - 7 + 5 = 10 → result[1] = 10
- Slide window to index 2:
- Remove code[2] = 1, add code[(2+3)%4 = 1] = 7
- Sum = 10 - 1 + 7 = 16 → result[2] = 16
- Slide window to index 3:
- Remove code[3] = 4, add code[(3+3)%4 = 2] = 1
- Sum = 16 - 4 + 1 = 13 → result[3] = 13
Final Answer = [12,10,16,13]
Complexity
- Time: O(n) — each element enters and leaves once.
- Space: O(n) for result array.

*/