package dc;

public class CuttingRibbons {
    public static int maxLength(int[] ribbons, int k) {
        int left = 1;
        int right = getMax(ribbons);
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCut(ribbons, k, mid)) {
                ans = mid;       // valid length, try longer
                left = mid + 1;
            } else {
                right = mid - 1; // too long, try shorter
            }
        }

        return ans;
    }

    private static int getMax(int[] ribbons) {
        int max = 0;
        for (int r : ribbons) {
            max = Math.max(max, r);
        }
        return max;
    }

    private static boolean canCut(int[] ribbons, int k, int length) {
        long count = 0;
        for (int r : ribbons) {
            count += r / length;
            if (count >= k) return true; // early exit
        }
        return count >= k;
    }
    public static void main(String[] args) {
        System.out.println(maxLength(new int[]{9,7,5}, 3)); 
        // Output: 5 → cut ribbons of length 5: [5,2], [5,2], [5]

        System.out.println(maxLength(new int[]{7,5,9}, 4)); 
        // Output: 4 → cut ribbons of length 4: [4,3], [4,1], [4,5]

        System.out.println(maxLength(new int[]{5,5,5}, 4)); 
        // Output: 3 → cut ribbons of length 3: [3,2], [3,2], [3,2], [3]
    }
}
/* 
Stepwise Explanation
- Range of possible lengths:
- Minimum = 1.
- Maximum = longest ribbon.
- Binary search:
- Check mid length.
- If we can cut ≥ k ribbons of this length → try longer.
- Otherwise → try shorter.
- Helper functions:
- getMax(ribbons) → largest ribbon length.
- canCut(ribbons, k, length) → simulate cutting and count ribbons.
- Termination:
- When loop ends, ans holds the maximum valid length.
Complexity
- Time: O(n log m)
- n = number of ribbons
- m = maximum ribbon length
- Space: O(1).
*/