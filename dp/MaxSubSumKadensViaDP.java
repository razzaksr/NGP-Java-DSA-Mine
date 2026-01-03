package dp;

public class MaxSubSumKadensViaDP {
    public static int maxSubArray(int[] nums) {
        int current = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either start new subarray at nums[i] or extend previous
            current = Math.max(nums[i], current + nums[i]);
            maxSum = Math.max(maxSum, current);
        }

        return maxSum;
    }

    public static int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }


    public static void main(String[] args) {
        // System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
        // System.out.println(maxSubArray(new int[]{1,2,3,4,5}));             // 15
        // System.out.println(maxSubArray(new int[]{-1,-2,-3,-4}));           // -1
        System.out.println(maxSubArrayDP(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
        System.out.println(maxSubArrayDP(new int[]{1,2,3,4,5}));             // 15
        System.out.println(maxSubArrayDP(new int[]{-1,-2,-3,-4}));           // -1
    }
}