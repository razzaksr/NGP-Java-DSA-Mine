package dp;

import java.util.Arrays;

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
            System.out.println("Processing index "+(s.charAt(i - 1) - '0')+" at position "+(s.charAt(i-1))+" for "+i);
            int one = s.charAt(i - 1) - '0';
            if (one >= 1 && one <= 9) {
                dp[i] += dp[i - 1];
            }
            System.out.println(Arrays.toString(dp)+" after processing index single");
            // Two digit decode
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two >= 10 && two <= 26) {
                dp[i] += dp[i - 2];
            }
            System.out.println(Arrays.toString(dp)+" after processing index "+i+" for two digit");
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // System.out.println(numDecodings("12"));    // 2
        System.out.println(numDecodings("226"));   // 3
        // System.out.println(numDecodings("06"));    // 0
        // System.out.println(numDecodings("11106")); // 2
        // System.out.println(numDecodings("2206")); 
        // System.out.println(numDecodings("1169217")); 
    }
}