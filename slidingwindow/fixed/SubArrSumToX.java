package slidingwindow.fixed;

public class SubArrSumToX {
    public static int countSubarrays(int[] nums, int k, int X) {
        int n = nums.length;
        int count = 0;
        int windowSum = 0;

        // Step 1: Initialize first window
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        if (windowSum >= X) count++;

        // Step 2: Slide the window
        for (int end = k; end < n; end++) {
            windowSum += nums[end] - nums[end - k];
            if (windowSum >= X) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(countSubarrays(new int[]{2,1,5,1,3,2}, 3, 7)); // 2
        System.out.println(countSubarrays(new int[]{1,2,3,4,5}, 2, 5));   // 2
        System.out.println(countSubarrays(new int[]{1,1,1,1,1}, 2, 3));   // 0
    }

}
/* 
Given an integer array, a window size k, and a threshold X, count how many contiguous subarrays of length k have a sum greater than or equal to X.

Stepwise Trace (Your Style)
Take nums = [2,1,5,1,3,2], k = 3, X = 7.
- Initialize first window (first 3 elements):
- windowSum = 2+1+5 = 8
- 8 ≥ 7 → count = 1
- Slide window → include index 3 = 1, remove index 0 = 2:
- windowSum = 8 + 1 - 2 = 7
- 7 ≥ 7 → count = 2
- Slide window → include index 4 = 3, remove index 1 = 1:
- windowSum = 7 + 3 - 1 = 9
- 9 ≥ 7 → count = 3
- Slide window → include index 5 = 2, remove index 2 = 5:
- windowSum = 9 + 2 - 5 = 6
- 6 < 7 → count remains 3
Final Answer = 3 subarrays
Complexity
- Time: O(n) — each element enters and leaves once.
- Space: O(1).

*/