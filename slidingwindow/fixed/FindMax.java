package slidingwindow.fixed;

public class FindMax {
    public static int maxSumSubarray(int[] nums, int k) {
        int n = nums.length;
        int windowSum = 0;

        // Step 1: Initialize first window
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        int maxSum = windowSum;

        // Step 2: Slide the window
        for (int end = k; end < n; end++) {
            // subtract element going out, add element coming in
            windowSum += nums[end] - nums[end - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSumSubarray(new int[]{2,1,5,1,3,2}, 3)); // 9
        System.out.println(maxSumSubarray(new int[]{2,3,4,1,5}, 2));   // 7
        System.out.println(maxSumSubarray(new int[]{1,1,1,1,1}, 2));   // 2
    }
}
/* 
Given an integer array and a window size k, find the maximum sum of any contiguous subarray of length k.


Stepwise Trace (Your Style)
Take nums = [2,1,5,1,3,2], k = 3.
- Initialize first window (first 3 elements):
- windowSum = 2 + 1 + 5 = 8
- maxSum = 8
- Slide window → include next element (index 3 = 1), remove index 0 = 2:
- windowSum = 8 + 1 - 2 = 7
- maxSum = max(8,7) = 8
- Slide window → include index 4 = 3, remove index 1 = 1:
- windowSum = 7 + 3 - 1 = 9
- maxSum = max(8,9) = 9
- Slide window → include index 5 = 2, remove index 2 = 5:
- windowSum = 9 + 2 - 5 = 6
- maxSum = max(9,6) = 9
Final Answer = 9
Complexity
- Time: O(n) — each element enters and leaves the window once.
- Space: O(1) — only a few variables.

*/