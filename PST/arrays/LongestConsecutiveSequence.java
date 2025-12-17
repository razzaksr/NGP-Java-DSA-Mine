package PST.arrays;

import java.util.HashSet;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int longest = 0;
        for (int num : set) {
            // Only start counting if 'num' is the beginning of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num; int currentStreak = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum++; currentStreak++;
                }
                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }

    // Example usage
    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();

        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive(nums1)); // Output: 4

        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(solution.longestConsecutive(nums2)); // Output: 9

        int[] nums3 = {1,0,1,2};
        System.out.println(solution.longestConsecutive(nums3)); // Output: 3
    }
}