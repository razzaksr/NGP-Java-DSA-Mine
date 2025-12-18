package PST.greedy;

public class JumpGameII {
    // // not handled unreachable
    // public static int jump(int[] nums) {
    //     int jumps = 0;          // Count of jumps taken
    //     int currentEnd = 0;     // End of the range for the current jump
    //     int farthest = 0;       // Farthest index reachable so far

    //     // We stop at n-2 because once we reach the last index, no more jumps are needed
    //     for (int i = 0; i < nums.length - 1; i++) {
    //         // Update the farthest we can reach from current index
    //         farthest = Math.max(farthest, i + nums[i]);

    //         // If we’ve reached the end of the current jump range
    //         if (i == currentEnd) {
    //             jumps++;              // Take a jump
    //             currentEnd = farthest; // Update the range to the farthest reachable
    //         }
    //     }
    //     return jumps;
    // }
    // unreachable handled
    public static int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // If currentEnd cannot move forward, break early
                if (currentEnd <= i) {
                    return -1; // unreachable
                }
            }
        }

        // If farthest never reaches the last index
        return farthest >= nums.length - 1 ? jumps : -1;
    }
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
        System.out.println(jump(new int[]{2,3,0,1,4}));
        System.out.println(jump(new int[]{3,2,1,0,4}));
    }
}