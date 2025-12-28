package dc;

public class MinTimeToCompleteBusTrip {
    public static long minimumTime(int[] time, int totalTrips) {
        long left = 1;
        long right = (long)1e14; // safe upper bound
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canComplete(time, totalTrips, mid)) {
                ans = mid;
                right = mid - 1; // try smaller time
            } else {
                left = mid + 1; // need more time
            }
        }

        return ans;
    }

    private static boolean canComplete(int[] time, int totalTrips, long givenTime) {
        long trips = 0;
        for (int t : time) {
            trips += givenTime / t; // trips each bus can finish
            if (trips >= totalTrips) return true; // early exit
        }
        return trips >= totalTrips;
    }

    public static void main(String[] args) {
        System.out.println(minimumTime(new int[]{1,2,3}, 5));
        // Output: 3
        // Explanation: In 3 units of time → bus1=3 trips, bus2=1 trip, bus3=1 trip → total=5.

        System.out.println(minimumTime(new int[]{2}, 1));
        // Output: 2
    }
}

/* 
 Stepwise Explanation
- Range of possible time:
- Minimum = 1.
- Maximum = very large (e.g., 10^{14}) because trips can be huge.
- Binary search:
- Guess a time mid.
- Check if buses can complete ≥ totalTrips in mid.
- If yes → try smaller time.
- If no → increase time.
- Feasibility check (canComplete):
- Each bus contributes givenTime / time[i] trips.
- Sum across all buses.
- If total ≥ totalTrips, feasible.
Complexity
- Time: O(nlog M)
- n = number of buses
- M = search range (up to 10^{14})
- Space: O(1).
*/