package dc;

public class FindInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid; // found exact match
            } else if (nums[mid] < target) {
                left = mid + 1; // search right half
            } else {
                right = mid - 1; // search left half
            }
        }

        // If not found, left is the correct insert position
        return left;
    }
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5)); // Output: 2
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2)); // Output: 1
        System.out.println(searchInsert(new int[]{1,3,5,6}, 7)); // Output: 4
        System.out.println(searchInsert(new int[]{1,3,5,6}, 0)); // Output: 0
    }
}
/* 
Stepwise Explanation
- Binary search setup:
- left = 0, right = nums.length - 1.
- Check mid element:
- If nums[mid] == target → return mid.
- If nums[mid] < target → move left to mid + 1.
- If nums[mid] > target → move right to mid - 1.
- Termination:
- When loop ends, left points to the correct insert position.
- This works because binary search narrows down where the target should be.
 Complexity
- Time: O(\log n) — binary search halves the search space each step.
- Space: O(1) — only a few variables used.
*/