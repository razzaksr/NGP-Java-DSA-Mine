package PST.intervals;

import java.util.*;

public class MinimumInterval {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // Sort intervals by start
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Sort queries but keep original index
        int n = queries.length;
        int[][] queryWithIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            queryWithIndex[i][0] = queries[i];
            queryWithIndex[i][1] = i;
        }
        Arrays.sort(queryWithIndex, (a, b) -> a[0] - b[0]);

        // Min-heap: [size, end]
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int[] result = new int[n];
        Arrays.fill(result, -1);

        int i = 0; // pointer for intervals
        for (int[] q : queryWithIndex) {
            int query = q[0];
            int idx = q[1];

            // Add intervals that start <= query
            while (i < intervals.length && intervals[i][0] <= query) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                int size = right - left + 1;
                heap.offer(new int[]{size, right});
                i++;
            }

            // Remove intervals that end < query
            while (!heap.isEmpty() && heap.peek()[1] < query) {
                heap.poll();
            }

            // If heap not empty, smallest interval is at top
            if (!heap.isEmpty()) {
                result[idx] = heap.peek()[0];
            }
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        MinimumInterval solver = new MinimumInterval();
        int[][] intervals1 = {{1,4},{2,4},{3,6},{4,4}};
        int[] queries1 = {2,3,4,5};
        System.out.println(Arrays.toString(solver.minInterval(intervals1, queries1)));
        // Output: [3, 3, 1, 4]

        int[][] intervals2 = {{2,3},{2,5},{1,8},{20,25}};
        int[] queries2 = {2,19,5,22};
        System.out.println(Arrays.toString(solver.minInterval(intervals2, queries2)));
        // Output: [2, -1, 4, 6]
    }
}