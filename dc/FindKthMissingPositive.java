package dc;

public class FindKthMissingPositive {
    public static int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Missing numbers until arr[mid]
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                left = mid + 1; // need more missing numbers
            } else {
                right = mid - 1; // too many missing, go left
            }
        }

        // After loop, left is the position where kth missing lies
        return left + k;
    }
    public static void main(String[] args) {
        System.out.println(findKthPositive(new int[]{2,3,4,7,11}, 5)); 
        // Output: 9 (missing numbers are [1,5,6,8,9,...])

        System.out.println(findKthPositive(new int[]{1,2,3,4}, 2)); 
        // Output: 6 (missing numbers are [5,6,...])

        System.out.println(findKthPositive(new int[]{2}, 1)); 
        // Output: 1

    }

}
/* 
Stepwise Explanation
- Observation:
- At index i, the number of missing positives before arr[i] is arr[i] - (i+1).
- Example: arr = [2,3,4,7,11]
- At i=0: missing = 2 - (0+1) = 1 → missing [1]
- At i=3: missing = 7 - (3+1) = 3 → missing [1,5,6]
- Binary Search:
- If missing count at mid is less than k, move right.
- Otherwise, move left.
- Termination:
- When loop ends, left points to the smallest index where missing ≥ k.
- Formula: left + k gives the kth missing number.
Complexity
- Time: O(\log n) — binary search over array indices.
- Space: O(1).
*/