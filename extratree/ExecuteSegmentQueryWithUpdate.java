package extratree;

class SegmentsTree {
    private int n;
    private int[] arr;
    private int[] sumTree;
    private int[] minTree;
    private int[] maxTree;
    private int[] lazy; // for range updates

    public SegmentsTree(int[] input) {
        this.n = input.length;
        this.arr = input.clone();
        sumTree = new int[4 * n];
        minTree = new int[4 * n];
        maxTree = new int[4 * n];
        lazy = new int[4 * n];
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

    // Push lazy updates
    private void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            sumTree[node] += (end - start + 1) * lazy[node];
            minTree[node] += lazy[node];
            maxTree[node] += lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    // Range Sum Query
    public int querySum(int l, int r) {
        return querySum(1, 0, n - 1, l, r);
    }

    private int querySum(int node, int start, int end, int l, int r) {
        push(node, start, end);
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return sumTree[node];
        int mid = (start + end) / 2;
        return querySum(node * 2, start, mid, l, r) +
               querySum(node * 2 + 1, mid + 1, end, l, r);
    }

    // Range Minimum Query
    public int queryMin(int l, int r) {
        return queryMin(1, 0, n - 1, l, r);
    }

    private int queryMin(int node, int start, int end, int l, int r) {
        push(node, start, end);
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
        push(node, start, end);
        if (r < start || end < l) return Integer.MIN_VALUE;
        if (l <= start && end <= r) return maxTree[node];
        int mid = (start + end) / 2;
        return Math.max(queryMax(node * 2, start, mid, l, r),
                        queryMax(node * 2 + 1, mid + 1, end, l, r));
    }

    // Point Update
    public void updatePoint(int idx, int value) {
        updatePoint(1, 0, n - 1, idx, value);
    }

    private void updatePoint(int node, int start, int end, int idx, int value) {
        push(node, start, end);
        if (start == end) {
            sumTree[node] = value;
            minTree[node] = value;
            maxTree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) updatePoint(node * 2, start, mid, idx, value);
            else updatePoint(node * 2 + 1, mid + 1, end, idx, value);
            sumTree[node] = sumTree[node * 2] + sumTree[node * 2 + 1];
            minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
            maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        }
    }

    // Range Update (add value to all elements in [l, r])
    public void updateRange(int l, int r, int value) {
        updateRange(1, 0, n - 1, l, r, value);
    }

    private void updateRange(int node, int start, int end, int l, int r, int value) {
        push(node, start, end);
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            lazy[node] += value;
            push(node, start, end);
            return;
        }
        int mid = (start + end) / 2;
        updateRange(node * 2, start, mid, l, r, value);
        updateRange(node * 2 + 1, mid + 1, end, l, r, value);
        sumTree[node] = sumTree[node * 2] + sumTree[node * 2 + 1];
        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
        maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
    }
}

public class ExecuteSegmentQueryWithUpdate {
    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 4, 9, 3};
        SegmentsTree st = new SegmentsTree(arr);

        System.out.println("Sum [1,4]: " + st.querySum(1, 4)); // 5+1+4+9 = 19
        System.out.println("Min [1,4]: " + st.queryMin(1, 4)); // 1
        System.out.println("Max [1,4]: " + st.queryMax(1, 4)); // 9

        st.updatePoint(2, 6); // arr[2] = 6
        System.out.println("Sum [1,4] after point update: " + st.querySum(1, 4)); // 5+6+4+9 = 24

        st.updateRange(1, 3, 2); // add 2 to arr[1..3]
        System.out.println("Sum [1,4] after range update: " + st.querySum(1, 4)); // 7+8+6+9 = 30
    }
}
