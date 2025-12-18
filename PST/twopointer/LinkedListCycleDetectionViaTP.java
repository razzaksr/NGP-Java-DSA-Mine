package PST.twopointer;

import java.util.*;

public class LinkedListCycleDetectionViaTP {
    public static boolean hasCycle(int[] next) {
        int slow = 0, fast = 0;
        while (true) {
            // Move slow by 1 step
            slow = (slow == -1) ? -1 : next[slow];
            // Move fast by 2 steps
            if (fast == -1 || next[fast] == -1) fast = -1;
            else fast = next[next[fast]];

            if (slow == -1 || fast == -1) return false; // reached end
            if (slow == fast) return true; // cycle detected
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = sc.nextInt();
        }
        System.out.println(hasCycle(next));
        sc.close();
    }
}