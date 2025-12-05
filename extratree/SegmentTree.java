package extratree;

import java.util.Arrays;

class SegmentTree {
    private int n;
    private int[] arr;
    private int[] sumTree;
    private int[] minTree;
    private int[] maxTree;

    public SegmentTree(int[] input) {
        this.n = input.length;
        this.arr = input.clone();
        sumTree = new int[4 * n];
        minTree = new int[4 * n];
        maxTree = new int[4 * n];
        build(1, 0, n - 1);
    }

    // Build the tree
    private void build(int node, int start, int end) {
        if (start == end) {
            sumTree[node] = arr[start];
            minTree[node] = arr[start];
            maxTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(node * 2, start, mid);
            build(node * 2 + 1, mid + 1, end);
            sumTree[node] = sumTree[node * 2] + sumTree[node * 2 + 1];
            minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
            maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        }
    }

    // Range Sum Query
    public int querySum(int l, int r) {
        return querySum(1, 0, n - 1, l, r);
    }

    private int querySum(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) {
            System.out.println(sumTree[node]+" "+start+" "+end+" "+l+" "+r);
            return sumTree[node];
        }
        int mid = (start + end) / 2;
        return querySum(node * 2, start, mid, l, r) +
               querySum(node * 2 + 1, mid + 1, end, l, r);
    }

    // Range Minimum Query
    public int queryMin(int l, int r) {
        return queryMin(1, 0, n - 1, l, r);
    }

    private int queryMin(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MAX_VALUE;
        if (l <= start && end <= r) return minTree[node];
        int mid = (start + end) / 2;
        return Math.min(queryMin(node * 2, start, mid, l, r),
                        queryMin(node * 2 + 1, mid + 1, end, l, r));
    }

    // Range Maximum Query
    public int queryMax(int l, int r) {
        return queryMax(1, 0, n - 1, l, r);
    }

    private int queryMax(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MIN_VALUE;
        if (l <= start && end <= r) return maxTree[node];
        int mid = (start + end) / 2;
        return Math.max(queryMax(node * 2, start, mid, l, r),
                        queryMax(node * 2 + 1, mid + 1, end, l, r));
    }
}