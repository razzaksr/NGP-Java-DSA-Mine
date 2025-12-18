package PST.intervals;

import java.util.*;

public class InsertInterval {
    public static List<int[]> insertNewSlot(List<int[]> old, int[] newSlot) {
        List<int[]> updated = new ArrayList<>();
        int size = old.size();
        int index = 0;

        // Step 1: Add non-overlapping intervals before merging
        while (index < size && old.get(index)[1] < newSlot[0]) {
            updated.add(old.get(index));
            index++;
        }

        // Step 2: Merge overlapping intervals
        while (index < size && old.get(index)[0] <= newSlot[1]) {
            newSlot[0] = Math.min(old.get(index)[0], newSlot[0]);
            newSlot[1] = Math.max(old.get(index)[1], newSlot[1]);
            index++;
        }
        updated.add(newSlot);

        // Step 3: Add remaining intervals
        while (index < size) {
            updated.add(old.get(index));
            index++;
        }

        return updated;
    }

    // Utility method to print intervals
    private static void printIntervals(List<int[]> intervals) {
        for (int[] interval : intervals) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<int[]> old1 = Arrays.asList(new int[]{1,3}, new int[]{6,9});
        int[] new1 = {2,5};
        printIntervals(insertNewSlot(old1, new1));
        // Expected: [[1,5], [6,9]]

        List<int[]> old2 = Arrays.asList(
            new int[]{1,2}, new int[]{3,5}, new int[]{6,7}, new int[]{8,10}, new int[]{12,16}
        );
        int[] new2 = {4,8};
        printIntervals(insertNewSlot(old2, new2));
        // Expected: [[1,2], [3,10], [12,16]]
    }
}