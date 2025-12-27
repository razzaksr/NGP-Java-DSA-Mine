package dc;

public class KokoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getMax(piles); // maximum pile size

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canEatAll(piles, h, mid)) {
                right = mid; // try smaller speed
            } else {
                left = mid + 1; // need larger speed
            }
        }

        return left;
    }

    private static int getMax(int[] piles) {
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        return max;
    }

    private static boolean canEatAll(int[] piles, int h, int speed) {
        long hours = 0;
        for (int pile : piles) {
            // ceil(pile / speed) → (pile + speed - 1) / speed
            hours += (pile + speed - 1) / speed;
        }
        return hours <= h;
    }  
    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3,6,7,11}, 8)); // Output: 4
        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20}, 5)); // Output: 30
        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20}, 6)); // Output: 23
    }  
}
/* 
Stepwise Explanation
- Range of speeds:
- Minimum speed = 1 (slowest possible).
- Maximum speed = largest pile (fastest needed).
- Binary search:
- Check mid speed.
- If Koko can eat all bananas within h hours at this speed → try smaller speed.
- Otherwise → increase speed.
- Helper functions:
- getMax(piles) → finds the largest pile.
- canEatAll(piles, h, speed) → simulates eating at given speed, summing hours.
- Termination:
- When left == right, that’s the minimum speed.
📊 Complexity
- Time: O(n\log m)
- n = number of piles
- m = maximum pile size
- Space: O(1)
*/