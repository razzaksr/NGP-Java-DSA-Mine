package dp;

public class FindNthTribonacci {
    public static int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println(tribonacci(4)); 
        // Output: 4 → sequence: 0,1,1,2,4

        System.out.println(tribonacci(25)); 
        // Output: 1389537

    }
}
/* 
Stepwise Explanation
- Base cases:
- dp[0] = 0, dp[1] = 1, dp[2] = 1.
- Transition:
- For i\geq 3:
dp[i]=dp[i-1]+dp[i-2]+dp[i-3]- Answer:
- Return dp[n].

Complexity
- Time: O(n) — one pass through sequence.
- Space: O(n) with DP array.

🚀 Space Optimized Version
We only need the last three values at each step, so we can reduce space to O(1):
class Solution {
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int a = 0, b = 1, c = 1;
        for (int i = 3; i <= n; i++) {
            int next = a + b + c;
            a = b;
            b = c;
            c = next;
        }
    }
}
*/