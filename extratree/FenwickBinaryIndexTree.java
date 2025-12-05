package extratree;

class FenwickTree {
    private int[] BIT;
    private int n;

    public FenwickTree(int[] arr) {
        this.n = arr.length;
        BIT = new int[n + 1]; // 1-indexed
        for (int i = 0; i < n; i++) {
            update(i, arr[i]); // build tree
        }
    }

    // Point update: add value at index idx
    public void update(int idx, int val) {
        idx++; // shift to 1-based index
        while (idx <= n) {
            BIT[idx] += val;
            idx += (idx & -idx); // move to next responsible node
            System.out.println("index "+ idx);
        }
    }

    // Prefix sum query: sum from 0..idx
    public int prefixSum(int idx) {
        idx++; // shift to 1-based index
        int sum = 0;
        while (idx > 0) {
            sum += BIT[idx];
            idx -= (idx & -idx); // move to parent
        }
        return sum;
    }

    // Range sum query: sum from l..r
    public int rangeSum(int l, int r) {
        return prefixSum(r) - prefixSum(l - 1);
    }
}

public class FenwickBinaryIndexTree {
    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 4, 9, 3};
        FenwickTree ft = new FenwickTree(arr);

        System.out.println("Prefix sum [0..3]: " + ft.prefixSum(3)); // 2+5+1+4 = 12
        System.out.println("Range sum [2..5]: " + ft.rangeSum(2, 5)); // 1+4+9+3 = 17

        ft.update(2, 5); // add 5 at index 2 → arr[2]=1+5=6
        System.out.println("Prefix sum [0..3] after update: " + ft.prefixSum(3)); // 2+5+6+4 = 17
    }
}
/*
Perfect, S — let’s walk through the **Fenwick Tree build process** exactly in the stepwise, “array split + fill” style you liked for the Segment Tree.  

We’ll use the same array:  
\[
arr = [2, 5, 1, 4, 9, 3]
\]

---

## 🌳 Fenwick Tree Build Logic
- Fenwick Tree is stored in a **BIT[] array** (size = n+1, 1-indexed).  
- Each `BIT[i]` holds the sum of a block of elements from `arr`.  
- The block size is determined by the **last set bit** of `i` (`i & -i`).  
- Build happens by calling `update(i, arr[i])` for each element.

---

## 🛠 Dry Run of Build

### Step 1: Initialize
- `BIT = [0,0,0,0,0,0,0]` (size 7 for n=6).  
- Iterate through arr and call `update`.

---

### Step 2: Insert arr[0] = 2
- Index = 1 (shifted).  
- Add 2 to BIT[1].  
- Next index = 1 + (1 & -1) = 2 → add 2 to BIT[2].  
- Next index = 2 + (2 & -2) = 4 → add 2 to BIT[4].  
- BIT after step: `[0,2,2,0,2,0,0]`.

---

### Step 3: Insert arr[1] = 5
- Index = 2.  
- Add 5 to BIT[2].  
- Next index = 4 → add 5 to BIT[4].  
- BIT after step: `[0,2,7,0,7,0,0]`.

---

### Step 4: Insert arr[2] = 1
- Index = 3.  
- Add 1 to BIT[3].  
- Next index = 4 → add 1 to BIT[4].  
- BIT after step: `[0,2,7,1,8,0,0]`.

---

### Step 5: Insert arr[3] = 4
- Index = 4.  
- Add 4 to BIT[4].  
- Next index = 8 (out of bounds).  
- BIT after step: `[0,2,7,1,12,0,0]`.

---

### Step 6: Insert arr[4] = 9
- Index = 5.  
- Add 9 to BIT[5].  
- Next index = 6 → add 9 to BIT[6].  
- BIT after step: `[0,2,7,1,12,9,9]`.

---

### Step 7: Insert arr[5] = 3
- Index = 6.  
- Add 3 to BIT[6].  
- Next index = 8 (out of bounds).  
- BIT after step: `[0,2,7,1,12,9,12]`.

---

## 📊 Final State

**Original Array (arr):**
```
Index: 0  1  2  3  4  5
Value: 2  5  1  4  9  3
```

**Fenwick Tree (BIT):**
```
Index: 1  2  3  4  5  6
Value: 2  7  1 12  9 12
```

---

## 🔄 How to Read BIT
- `BIT[1]` = arr[0]  
- `BIT[2]` = arr[0..1]  
- `BIT[3]` = arr[2]  
- `BIT[4]` = arr[0..3]  
- `BIT[5]` = arr[4]  
- `BIT[6]` = arr[4..5]  

So each node stores a **prefix block sum** determined by its binary index.

---

✅ This is the **“split original array + fill BIT step by step”** style you asked for. You can now see exactly how each element flows into BIT and how recursion-like iteration happens.

Would you like me to now **dry run a prefixSum query** (say `prefixSum(3)`) in the same stepwise way, showing how BIT values are picked up?
 
*/