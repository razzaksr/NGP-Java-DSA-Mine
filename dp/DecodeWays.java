package dp;

public class DecodeWays {
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // base case: empty string

        // First character must not be '0'
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            // Single digit decode
            int one = s.charAt(i - 1) - '0';
            if (one >= 1 && one <= 9) {
                dp[i] += dp[i - 1];
            }

            // Two digit decode
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two >= 10 && two <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));    // 2
        System.out.println(numDecodings("226"));   // 3
        System.out.println(numDecodings("06"));    // 0
        System.out.println(numDecodings("11106")); // 2
    }
}