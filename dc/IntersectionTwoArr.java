package dc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionTwoArr {
    public static int[] intersection(int[] nums1, int[] nums2) {
        // Sort both arrays
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> resultSet = new HashSet<>();

        // For each element in nums1, binary search in nums2
        for (int num : nums1) {
            if (binarySearch(nums2, num)) {
                resultSet.add(num); // ensure uniqueness
            }
        }

        // Convert set to array
        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            result[i++] = num;
        }
        return result;
    }

    private static boolean binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
    // alternate to DC/ binary search approach
    /* Complexity
    - Time: O(n+m) where n and m are lengths of the two arrays.
    - Space: O(n+m) due to sets.
    */
    public static int[] intersectionViaSet(int[] nums1, int[] nums2) {
        // Store elements of nums1 in a set
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        // Store intersection in another set
        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                resultSet.add(num);
            }
        }

        // Convert set to array
        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            result[i++] = num;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersection(
            new int[]{1,2,2,1}, new int[]{2,2}))); 
        // Output: [2]

        System.out.println(Arrays.toString(intersection(
            new int[]{4,9,5}, new int[]{9,4,9,8,4}))); 
        // Output: [4,9] (order may vary)

    }
}
/* 
Stepwise Explanation
- Sort both arrays → needed for binary search.
- Iterate through nums1 → for each element, run binary search in nums2.
- Use a HashSet → ensures intersection elements are unique.
- Convert set to array → return result.

Complexity
- Sorting: O(n log n+m log m)
- Binary search for each element: O(nlog m)
- Total: O(n log m+m log m)

*/