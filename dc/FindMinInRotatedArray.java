package dc;

public class FindMinInRotatedArray {
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // If mid element is greater than right element,
            // minimum must be in the right half
            if (nums[mid] > nums[right]) left = mid + 1;
            // Otherwise, minimum is in the left half (including mid)
            else right = mid;
        }
        // At the end, left == right, pointing to the minimum element
        return nums[left];
    }
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2})); // Output: 1
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2})); // Output: 0
        System.out.println(findMin(new int[]{11,13,15,17})); // Output: 11
    }
}
/* 
Stepwise Explanation
- Observation:
- A rotated sorted array has two sorted halves.
- The minimum element is the "rotation point".
- Binary Search Logic:
- Compare nums[mid] with nums[right].
- If nums[mid] > nums[right], the minimum lies in the right half.
- Otherwise, the minimum lies in the left half (including mid).
- Termination:
- Loop continues until left == right.
- That index is the minimum element.
📊 Complexity
- Time: O(\log n) — binary search halves the search space each step.
- Space: O(1) — only a few variables used.
*/