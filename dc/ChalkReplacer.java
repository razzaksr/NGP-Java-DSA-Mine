package dc;

public class ChalkReplacer {
    public static int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long[] prefix = new long[n];
        prefix[0] = chalk[0];
        for (int i = 1; i < n; i++) prefix[i] = prefix[i - 1] + chalk[i];
        long total = prefix[n - 1];
        long remaining = k % total;
        // Binary search: find first prefix[i] > remaining
        int left = 0, right = n - 1, ans = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (prefix[mid] > remaining) {ans = mid;right = mid - 1;}
            else left = mid + 1;
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(chalkReplacer(new int[]{5,1,5}, 22)); 
        // Output: 0 → total=11, 22%11=0 → student 0 needs chalk immediately.
        System.out.println(chalkReplacer(new int[]{3,4,1,2}, 25)); 
        // Output: 1 → total=10, 25%10=5 → binary search finds prefix[1]=7 > 5.
    }
}
/* 
Stepwise Explanation
- Prefix sums:
- prefix[i] = total chalk used by students 0..i.
- Example: chalk = [5,1,5] → prefix = [5,6,11].
- Modulo optimization:
- Total chalk per round = prefix[n-1].
- Only k % total matters (remaining chalk after full rounds).
- Binary search:
- Find the smallest index i such that prefix[i] > remaining.
- That student cannot continue → they replace the chalk.
Complexity
- Time: O(n log n) — build prefix sums + binary search.
- Space: O(n).
*/