package PST.intervals;

import java.util.*;

public class NonOverlappingIntervals {
    public static int eraseOverlapIntervals(int[][] intervals) {
        // Step 1: Sort by end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int removeCount = 0;
        int end = intervals[0][1]; // first interval's end

        // Step 2: Iterate
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                // Overlap → remove this interval
                removeCount++;
            } else {
                // Non-overlap → update end
                end = intervals[i][1];
            }
        }
        return removeCount;
    }
    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}));
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{2,3}}));
    }
}
/* 
Non-overlapping Intervals
Stepwise Explanation
- Sort intervals by end time → Greedy choice ensures we keep intervals that finish earliest, leaving room for others.
- Iterate through intervals:
- Track the end of the last chosen interval.
- If the current interval’s start < end, it overlaps → increment removeCount.
- Else, update end to current interval’s end.
- Return removeCount.

Example Trace (Input: [[1,2],[2,3],[3,4],[1,3]])
- Sorted → [[1,2],[1,3],[2,3],[3,4]]
- Keep [1,2], remove [1,3], keep [2,3], keep [3,4].
- Answer = 1

*/