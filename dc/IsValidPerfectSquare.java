package dc;

public class IsValidPerfectSquare {
    public static boolean isPerfectSquare(int num) {
        if (num < 2) return true; // 0 and 1 are perfect squares
        long left = 2, right = num / 2; // search range
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            if (square == num) return true;
            else if (square < num) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16)); // true (4*4)
        System.out.println(isPerfectSquare(14)); // false
        System.out.println(isPerfectSquare(1));  // true
        System.out.println(isPerfectSquare(808201)); // true (899*899)
    }
}

/* 
Stepwise Explanation
- Base case:
- If num < 2, return true (since 0 and 1 are perfect squares).
- Binary search range:
- Possible square roots lie between 2 and num/2.
- Check mid:
- Compute mid * mid.
- If equal to num → return true.
- If smaller → move left up.
- If larger → move right down.
- Termination:
- If no exact square found, return false.

Complexity
- Time: O(\log n) — binary search halves the range each step.
- Space: O(1) — only a few variables used.
*/