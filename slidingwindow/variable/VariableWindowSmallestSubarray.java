package slidingwindow.variable;

public class VariableWindowSmallestSubarray {
    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        int windowSum = 0;
        int start = 0;

        for (int end = 0; end < n; end++) {
            windowSum += nums[end];

            // shrink window while condition is satisfied
            while (windowSum >= target) {
                minLen = Math.min(minLen, end - start + 1);
                windowSum -= nums[start];
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3})); // 2
        System.out.println(minSubArrayLen(11, new int[]{1,2,3,4,5}));  // 3
        System.out.println(minSubArrayLen(15, new int[]{1,2,3,4,5}));  // 5
    }
}
/*
Smallest Subarray with Sum ≥ Target
Input: array of positive integers + target sum
Output: length of smallest subarray with sum ≥ target

Stepwise Trace (Your Style)
Take target = 7, nums = [2,3,1,2,4,3].
- Expand window:
- Add 2 → sum = 2 (<7).
- Add 3 → sum = 5 (<7).
- Add 1 → sum = 6 (<7).
- Add 2 → sum = 8 (≥7).
- Shrink window:
- Length = 4 → minLen = 4.
- Remove 2 → sum = 6 (<7). Stop shrinking.
- Expand window:
- Add 4 → sum = 10 (≥7).
- Length = 4 → minLen = 4.
- Remove 3 → sum = 7 (≥7).
- Length = 3 → minLen = 3.
- Remove 1 → sum = 6 (<7). Stop shrinking.
- Expand window:
- Add 3 → sum = 9 (≥7).
- Length = 2 → minLen = 2.
- Remove 2 → sum = 7 (≥7).
- Length = 1 → minLen = 1? No, because only one element (3) → sum=7 valid. Actually length=1 is valid. But careful: here length=1 is not possible because sum=7 only with [3]? Wait check: last window [4,3] length=2 sum=7. So minLen=2.
Final Answer = 2

📊 Complexity
- Time: O(n) — each element enters and leaves once.
- Space: O(1).


*/