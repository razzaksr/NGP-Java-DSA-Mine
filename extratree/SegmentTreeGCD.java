package extratree;

import java.util.*;

public class SegmentTreeGCD {
    static int[] arr;
    static int[] seg;

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static void build(int idx, int l, int r) {
        if (l == r) {
            seg[idx] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * idx, l, mid);
        build(2 * idx + 1, mid + 1, r);
        seg[idx] = gcd(seg[2 * idx], seg[2 * idx + 1]);
    }

    static void update(int idx, int l, int r, int pos, int val) {
        if (l == r) {
            seg[idx] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (pos <= mid) update(2 * idx, l, mid, pos, val);
        else update(2 * idx + 1, mid + 1, r, pos, val);
        seg[idx] = gcd(seg[2 * idx], seg[2 * idx + 1]);
    }

    static int query(int idx, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return seg[idx];
        int mid = (l + r) / 2;
        return gcd(query(2 * idx, l, mid, ql, qr),
                   query(2 * idx + 1, mid + 1, r, ql, qr));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), q = sc.nextInt();
        arr = new int[n + 1];
        seg = new int[4 * n];
        for (int i = 1; i <= n; i++) arr[i] = sc.nextInt();
        build(1, 1, n);

        while (q-- > 0) {
            int type = sc.nextInt();
            if (type == 1) {
                int i = sc.nextInt(), v = sc.nextInt();
                update(1, 1, n, i, v);
            } else {
                int l = sc.nextInt(), r = sc.nextInt();
                System.out.println(query(1, 1, n, l, r));
            }
        }
        sc.close();
    }
}