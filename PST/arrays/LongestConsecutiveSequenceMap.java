package PST.arrays;

import java.util.HashMap;

public class LongestConsecutiveSequenceMap {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int longest = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int sum = left + right + 1;
                map.put(num, sum);
                // Update boundaries
                if (left > 0) map.put(num - left, sum);
                if (right > 0) map.put(num + right, sum);
                longest = Math.max(longest, sum);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequenceMap solution = new LongestConsecutiveSequenceMap();

        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive(nums1)); // Output: 4

        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(solution.longestConsecutive(nums2)); // Output: 9

        int[] nums3 = {1,0,1,2};
        System.out.println(solution.longestConsecutive(nums3)); // Output: 3
    }
}