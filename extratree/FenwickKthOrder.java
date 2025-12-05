package extratree;

import java.util.*;

public class FenwickKthOrder {
    static int MAX = 1000000; // max value of x
    static int[] bit = new int[MAX + 2];

    // Fenwick Tree update
    static void update(int idx, int delta) {
        while (idx <= MAX) {
            bit[idx] += delta;
            idx += idx & -idx;
        }
    }

    // Fenwick Tree prefix sum
    static int prefixSum(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    // Find k-th smallest using binary lifting
    static int findKth(int k) {
        int idx = 0;
        int mask = 1 << 20; // since MAX ~ 1e6 < 2^20
        while (mask > 0) {
            int next = idx + mask;
            if (next <= MAX && bit[next] < k) {
                k -= bit[next];
                idx = next;
            }
            mask >>= 1;
        }
        return idx + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while (q-- > 0) {
            int type = sc.nextInt();
            if (type == 1) {
                int x = sc.nextInt();
                update(x, 1);
            } else if (type == 2) {
                int x = sc.nextInt();
                update(x, -1);
            } else {
                int k = sc.nextInt();
                System.out.println(findKth(k));
            }
        }
        sc.close();
    }
}