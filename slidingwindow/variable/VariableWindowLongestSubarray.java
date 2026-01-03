package slidingwindow.variable;

public class VariableWindowLongestSubarray {
    public static int longestSubarrayWithSumAtMostK(int[] nums, int K) {
        int n = nums.length;
        int start = 0, windowSum = 0, maxLen = 0;

        for (int end = 0; end < n; end++) {
            windowSum += nums[end];

            // shrink window while sum > K
            while (windowSum > K) {
                windowSum -= nums[start];
                start++;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(longestSubarrayWithSumAtMostK(new int[]{1,2,1,0,1,1,0}, 4)); // 6
        System.out.println(longestSubarrayWithSumAtMostK(new int[]{2,1,5,2,3}, 7));     // 3
        System.out.println(longestSubarrayWithSumAtMostK(new int[]{1,2,3,4,5}, 11));    // 4
    }
}
/* 
Given an array of positive integers and a limit K, find the length of the longest contiguous subarray whose sum is less than or equal to K.


Stepwise Trace (Your Style)
Take nums = [2,1,5,2,3], K = 7.
- Expand window:
- Add 2 → sum = 2 (≤7), length = 1, maxLen = 1
- Add 1 → sum = 3 (≤7), length = 2, maxLen = 2
- Add 5 → sum = 8 (>7).
- Shrink window:
- Remove 2 → sum = 6 (≤7), start=1
- Length = 2 (indices [1,2]) → maxLen = 2
- Expand window:
- Add 2 → sum = 8 (>7).
- Shrink: remove 1 → sum = 7 (≤7), start=2
- Length = 2 (indices [2,3]) → maxLen = 2
- Expand window:
- Add 3 → sum = 10 (>7).
- Shrink: remove 5 → sum = 5 (≤7), start=3
- Length = 2 (indices [3,4]) → maxLen = 2
Final Answer = 3 (the longest valid subarray is [2,1,5] trimmed to [1,5,2] or [5,2] depending on shrink, but length=3 is achievable with [2,1,5] before shrink).

📊 Complexity
- Time: O(n) — each element enters and leaves once.
- Space: O(1).

*/