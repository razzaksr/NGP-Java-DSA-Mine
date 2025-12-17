package PST.Bits;

import java.util.Arrays;

public class CountingBits {
    /**
     * Calculates the number of set bits for every integer from 0 to n using Dynamic Programming.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for the result array.
     */
    public static int[] countBits(int n) {
        // Create the result array of size n + 1
        int[] ans = new int[n + 1];
        
        // Base case: ans[0] = 0 (0 has 0 set bits)
        if (n >= 0) {
            ans[0] = 0;
        }

        // DP loop from 1 up to n
        for (int i = 1; i <= n; i++) {
            // Recurrence Relation: ans[i] = ans[i / 2] + (i % 2)
            
            // i >> 1 is equivalent to i / 2 (integer division)
            // i & 1 is equivalent to i % 2 (checking the LSB/parity)
            System.out.println(ans[i >> 1]+" "+ (i & 1));
            ans[i] = ans[i / 2] + (i & 1);
        }

        return ans;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(2)));
        System.out.println(Arrays.toString(countBits(5)));
    }
}