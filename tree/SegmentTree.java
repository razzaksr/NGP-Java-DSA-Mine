package tree;

import java.util.Arrays;

class SegmentTree {
    int[] st;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        st = new int[4*n];
        build(arr, 0, n-1, 0);
    }

    void build(int[] arr, int l, int r, int si) {
        System.out.println("Left "+l+" right "+r+" start index "+si+" and Array is "+Arrays.toString(Arrays.copyOfRange(arr, l, (r+1))));
        if(l == r) {
            st[si] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(arr, l, mid, 2*si+1);
        build(arr, mid+1, r, 2*si+2);
        st[si] = st[2*si+1] + st[2*si+2];
    }

    int query(int qs, int qe) {
        return queryUtil(0, n-1, qs, qe, 0);
    }

    int queryUtil(int l, int r, int qs, int qe, int si) {
        if(qs <= l && qe >= r)
            return st[si];
        if(qe < l || qs > r)
            return 0;
        int mid = (l+r)/2;
        return queryUtil(l,mid,qs,qe,2*si+1) + 
               queryUtil(mid+1,r,qs,qe,2*si+2);
    }

    void update(int idx, int val) {
        updateUtil(0,n-1,idx,val,0);
    }

    void updateUtil(int l, int r, int idx, int val, int si) {
        if(l == r) {
            st[si] = val;
            return;
        }
        int mid = (l+r)/2;
        if(idx <= mid)
            updateUtil(l,mid,idx,val,2*si+1);
        else
            updateUtil(mid+1,r,idx,val,2*si+2);

        st[si] = st[2*si+1] + st[2*si+2];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        SegmentTree st = new SegmentTree(arr);
        // System.out.println(Arrays.toString(st.st));
        // System.out.println(st.query(1,3)); // 2+3+4 = 9
        // st.update(2,10); // change 3 -> 10
        // System.out.println(st.query(1,3)); // 2+10+4 = 16
    }
}