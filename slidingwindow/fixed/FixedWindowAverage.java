package slidingwindow.fixed;

import java.util.*;

public class FixedWindowAverage {
    public static double[] averageOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];

        int windowSum = 0;

        // Step 1: Initialize first window
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        result[0] = (double) windowSum / k;

        // Step 2: Slide the window
        for (int end = k; end < n; end++) {
            windowSum += nums[end] - nums[end - k];
            result[end - k + 1] = (double) windowSum / k;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(averageOfSubarrays(new int[]{1,3,2,6,-1,4,1,8,2}, 5)));
        // Expected: [2.2, 2.8, 2.4, 3.6, 2.8]
    }
}

/* 
Given an integer array and a window size k, return the average of every contiguous subarray of length k.

Stepwise Trace (Your Style)
Take nums = [1,3,2,6,-1,4,1,8,2], k = 5.
- Initialize first window (first 5 elements):
- windowSum = 1+3+2+6+(-1) = 11
- Average = 11/5 = 2.2 → result[0] = 2.2
- Slide window → include index 5 = 4, remove index 0 = 1:
- windowSum = 11 + 4 - 1 = 14
- Average = 14/5 = 2.8 → result[1] = 2.8
- Slide window → include index 6 = 1, remove index 1 = 3:
- windowSum = 14 + 1 - 3 = 12
- Average = 12/5 = 2.4 → result[2] = 2.4
- Slide window → include index 7 = 8, remove index 2 = 2:
- windowSum = 12 + 8 - 2 = 18
- Average = 18/5 = 3.6 → result[3] = 3.6
- Slide window → include index 8 = 2, remove index 3 = 6:
- windowSum = 18 + 2 - 6 = 14
- Average = 14/5 = 2.8 → result[4] = 2.8
Final Answer = [2.2, 2.8, 2.4, 3.6, 2.8]

📊 Complexity
- Time: O(n) — each element enters and leaves once.
- Space: O(n-k+1) for result array.

*/