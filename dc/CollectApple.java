package dc;

public class CollectApple {
    public static long minimumPerimeter(long neededApples) {
        long left = 1, right = (long)1e6; // safe upper bound
        long ans = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long apples = totalApples(mid);

            if (apples >= neededApples) {
                ans = mid;
                right = mid - 1; // try smaller side length
            } else {
                left = mid + 1; // need larger side length
            }
        }
        return ans * 8; // perimeter = 8 * side length
    }
    // Total apples collected in square of side length n
    private static long totalApples(long n) {
        // Formula: 2 * n * (n + 1) * (2n + 1)
        return 2 * n * (n + 1) * (2 * n + 1);
    }
    public static void main(String[] args) {
        System.out.println(minimumPerimeter(1)); 
        // Output: 8 (smallest perimeter)

        System.out.println(minimumPerimeter(13)); 
        // Output: 16

        System.out.println(minimumPerimeter(1000000000)); 
        // Output: 5040
    }
}
/* 
Stepwise Explanation
- Observation:
- Apples are distributed such that in a square of side length n,
total apples = 2n(n+1)(2n+1).
- We need the smallest n such that total apples ≥ neededApples.
- Binary Search:
- Search n between 1 and a large bound (e.g., 10^6).
- Compute apples for mid.
- If enough apples → try smaller n.
- Otherwise → increase n.
- Perimeter:
- Once we find n, perimeter = 8n
Complexity
- Time: O(log n) — binary search on side length.
- Space: O(1).
*/