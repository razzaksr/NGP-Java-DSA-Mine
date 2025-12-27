package dc;

public class ArrangeCoins {
    public static int arrangeCoins(int n) {
        long left = 1, right = n; // use long to avoid overflow

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long coinsUsed = mid * (mid + 1) / 2; // sum of first mid numbers

            if (coinsUsed == n) {
                return (int) mid; // exact fit
            } else if (coinsUsed < n) {
                left = mid + 1; // can build more rows
            } else {
                right = mid - 1; // too many coins, reduce rows
            }
        }

        return (int) right; // right points to the last valid full row
    }
    public static void main(String[] args) {
        System.out.println(arrangeCoins(5));  // Output: 2
        // Explanation: 2 full rows (1 + 2), leftover 2 coins.

        System.out.println(arrangeCoins(8));  // Output: 3
        // Explanation: 3 full rows (1 + 2 + 3), leftover 2 coins.

        System.out.println(arrangeCoins(1));  // Output: 1

    }
}
/* 
Stepwise Explanation
- Observation:
- To build k rows, we need 1 + 2 + ... + k = k*(k+1)/2 coins.
- We want the largest k such that k*(k+1)/2 ≤ n.
- Binary Search:
- Search between 1 and n rows.
- Compute coins needed for mid rows.
- If coinsUsed == n → return mid.
- If coinsUsed < n → move left up (we can build more).
- If coinsUsed > n → move right down (too many coins).
- Termination:
- When loop ends, right points to the largest valid number of rows.
Complexity
- Time: O(\log n) — binary search halves the range each step.
- Space: O(1) — only a few variables used.
*/