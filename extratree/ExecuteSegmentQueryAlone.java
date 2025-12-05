package extratree;

public class ExecuteSegmentQueryAlone {
    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 4, 9, 3};
        SegmentTree st = new SegmentTree(arr);

        System.out.println("Sum [1,4]: " + st.querySum(1, 4)); // 19
        System.out.println("Sum [1,2]: " + st.querySum(1, 2)); // 0
        // System.out.println("Min [1,4]: " + st.queryMin(1, 4)); // 1
        // System.out.println("Max [1,4]: " + st.queryMax(1, 4)); // 9
    }
}
/* 
Excellent — let’s **trace the recursion step by step** and show how the **original array** and the **sumTree array** get filled during the `build` method.  

We’ll use the example:  
\[
arr = [2, 5, 1, 4, 9, 3]
\]

---

## 🌳 Key Idea
- `sumTree` is a binary tree stored in an array (like a heap).  
- Each node index `node` covers a segment `[start, end]`.  
- Leaves store single elements, internal nodes store the sum of their children.  
- Recursion splits `[start, end]` into two halves until reaching leaves.

---

## 🧩 Dry Run of `build(1, 0, 5)`

### Step 1: Root
- `node=1, start=0, end=5` → split into `[0,2]` and `[3,5]`.  
- Calls: `build(2,0,2)` and `build(3,3,5)`.

---

### Step 2: Left Subtree `[0,2]`
- `node=2, start=0, end=2` → split into `[0,1]` and `[2,2]`.  
- Calls: `build(4,0,1)` and `build(5,2,2)`.

#### Leaf `[0,0]`
- `node=8, start=0, end=0` → arr[0]=2.  
- `sumTree[8]=2`.

#### Leaf `[1,1]`
- `node=9, start=1, end=1` → arr[1]=5.  
- `sumTree[9]=5`.

#### Combine `[0,1]`
- `node=4` → sumTree[4] = sumTree[8] + sumTree[9] = 2+5=7.

#### Leaf `[2,2]`
- `node=5` → arr[2]=1.  
- `sumTree[5]=1`.

#### Combine `[0,2]`
- `node=2` → sumTree[2] = sumTree[4] + sumTree[5] = 7+1=8.

---

### Step 3: Right Subtree `[3,5]`
- `node=3, start=3, end=5` → split into `[3,4]` and `[5,5]`.  
- Calls: `build(6,3,4)` and `build(7,5,5)`.

#### Leaf `[3,3]`
- `node=12` → arr[3]=4.  
- `sumTree[12]=4`.

#### Leaf `[4,4]`
- `node=13` → arr[4]=9.  
- `sumTree[13]=9`.

#### Combine `[3,4]`
- `node=6` → sumTree[6] = 4+9=13.

#### Leaf `[5,5]`
- `node=7` → arr[5]=3.  
- `sumTree[7]=3`.

#### Combine `[3,5]`
- `node=3` → sumTree[3] = 13+3=16.

---

### Step 4: Combine Root `[0,5]`
- `node=1` → sumTree[1] = sumTree[2] + sumTree[3] = 8+16=24.

---

## 📊 Final Arrays

**Original Array (arr):**
```
Index: 0  1  2  3  4  5
Value: 2  5  1  4  9  3
```

**SumTree Array (partial view):**
```
Node Index : Value (segment)
1 : 24  [0..5]
2 : 8   [0..2]
3 : 16  [3..5]
4 : 7   [0..1]
5 : 1   [2..2]
6 : 13  [3..4]
7 : 3   [5..5]
8 : 2   [0..0]
9 : 5   [1..1]
12: 4   [3..3]
13: 9   [4..4]
```

---

## 🔄 Recursion Flow Summary
1. Start at root `[0..5]`.  
2. Split into left `[0..2]` and right `[3..5]`.  
3. Each half splits further until single-element leaves.  
4. Leaf nodes take values directly from `arr`.  
5. Internal nodes sum their children’s values.  
6. Root ends up with total sum = 24.

---

👉 This breakdown shows **how recursion fills values step by step**.  

Would you like me to **draw a tree diagram (levels with nodes and sums)** so you can visually see how the recursion builds the hierarchy? That often makes the process click instantly.


*/