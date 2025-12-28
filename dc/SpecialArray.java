package dc;

public class SpecialArray {
    public static int specialArray(int[] nums) {
        int n = nums.length;
        int left = 0, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = countGreaterOrEqual(nums, mid);
            if (count == mid) return mid; // found special number
            else if (count > mid) left = mid + 1; // need larger x
            else right = mid - 1; // need smaller x
        }
        return -1; // no special number exists
    }
    private static int countGreaterOrEqual(int[] nums, int x) {
        int count = 0;
        for (int num : nums) if (num >= x) count++;
        return count;
    }
    public static void main(String[] args) {
        System.out.println(specialArray(new int[]{3,5}));        // Output: 2
        System.out.println(specialArray(new int[]{0,0}));        // Output: -1
        System.out.println(specialArray(new int[]{0,4,3,0,4}));  // Output: 3
        System.out.println(specialArray(new int[]{3,6,7,7,0}));  // Output: -1
    }
}
/* 
Stepwise Explanation
- Observation:
- We want an integer x such that exactly x elements in nums are ≥ x.
- Possible values of x range from 0 to n (array length).
- Binary Search:
- Check mid value x.
- Count how many elements are ≥ x.
- If count == x → return x.
- If count > x → we need a larger x.
- If count < x → we need a smaller x.
- Termination:
- If no valid x found, return -1.
📊 Complexity
- Time: O(nlog n) — binary search runs log(n) times, each count scan is O(n).
- Space: O(1).
*/