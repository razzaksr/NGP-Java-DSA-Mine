package PST.greedy;

import java.util.*;

public class MinMovesToSeat {
    public static int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int moves = 0;
        for (int i = 0; i < seats.length; i++) {
            moves += Math.abs(seats[i] - students[i]);
        }
        return moves;
    }
    public static void main(String[] args) {
        System.out.println(minMovesToSeat(new int[]{3,1,5}, new int[]{2,7,4}));
        System.out.println(minMovesToSeat(new int[]{4,1,5,9}, new int[]{1,3,2,6}));
        System.out.println(minMovesToSeat(new int[]{2,2,6,6}, new int[]{1,3,2,6}));
    }
}

/* 
Minimum Number of Moves to Seat Everyone
Stepwise Explanation
- Sort both arrays seats and students.
- Pair each student with corresponding seat (same index after sorting).
- Sum absolute differences → minimum moves

Example Trace (Input: seats=[3,1,5], students=[2,7,4])
- Sorted seats = [1,3,5], students = [2,4,7]
- Pairwise differences: 1-2=1, 3-4=1, 5-7=2
- Total = 4
- Answer = 4
*/