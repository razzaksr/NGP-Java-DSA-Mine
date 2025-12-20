package PST.greedy;

import java.util.*;

public class HandOfStraights {
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        while (!map.isEmpty()) {
            int first = map.firstKey(); // smallest card
            for (int i = 0; i < groupSize; i++) {
                int curr = first + i;
                if (!map.containsKey(curr)) return false;
                map.put(curr, map.get(curr) - 1);
                if (map.get(curr) == 0) map.remove(curr);
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3));
        System.out.println(isNStraightHand(new int[]{1,2,3,4,5}, 4));
    }
}
/* 
Hand of Straights
Stepwise Explanation
- Check divisibility: If hand.length % groupSize != 0, return false immediately.
- Use TreeMap:
- Stores card values in sorted order.
- Key = card value, Value = frequency.
- Iterate through smallest card repeatedly:
- For each group, try to take groupSize consecutive cards.
- If any card is missing → return false.
- Reduce frequency, remove if zero.
- If all groups formed → return true.
Example Trace (Input: [1,2,3,6,2,3,4,7,8], groupSize=3)
- Sorted map → {1:1,2:2,3:2,4:1,6:1,7:1,8:1}
- First group → [1,2,3]
- Next group → [2,3,4]
- Next group → [6,7,8]
- Answer = true
*/