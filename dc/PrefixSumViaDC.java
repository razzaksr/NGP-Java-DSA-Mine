package dc;

import java.util.Arrays;

/* 
Excellent observation! You're asking a very insightful question about what truly constitutes "dividing" in divide and conquer. Let me clarify this carefully.

---

## The Key Insight: It's About the **Problem Space**, Not Physical Array Splitting

You're right that **neither method physically creates new arrays**. But that's not what matters for D&C!

**Divide and Conquer is about:**
- How you **conceptually partition the problem**
- How **independent** the subproblems are
- The **recursion tree structure**

---

## Method 1 Analysis: Linear Recursion (NOT D&C)

```java
public static void performPrefixSum(int[] arr, int[] result, int current) {
    if (current == 0) {
        result[0] = arr[0];
        return;
    }
    performPrefixSum(arr, result, current - 1);  // Process index 0 to current-1
    result[current] = result[current - 1] + arr[current];  // Then process current
}
```

### Why This is NOT D&C:

**1. Sequential Dependency:**
```
To solve position 'current', you MUST first solve position 'current-1'

solve(4) depends on solve(3)
solve(3) depends on solve(2)
solve(2) depends on solve(1)
solve(1) depends on solve(0)

This is a CHAIN of dependencies!
```

**2. Single Recursive Path:**
```
Call stack at any moment:
performPrefixSum(..., 4)
  └─ performPrefixSum(..., 3)
       └─ performPrefixSum(..., 2)
            └─ performPrefixSum(..., 1)
                 └─ performPrefixSum(..., 0)

Only ONE active branch at a time
```

**3. Cannot Parallelize:**
```
You CANNOT compute result[4] until result[3] is done
You CANNOT compute result[3] until result[2] is done
...
Everything is sequential!
```

---

## Method 2 Analysis: True D&C

```java
public static void computePrefix(int[] arr, int[] result, int left, int right) {
    if (left == right) {
        result[left] = arr[left];
        return;
    }

    int mid = (left + right) / 2;

    // DIVIDE: Split problem space into TWO independent ranges
    computePrefix(arr, result, left, mid);        // Range [left, mid]
    computePrefix(arr, result, mid + 1, right);   // Range [mid+1, right]

    // COMBINE: Merge the two solved subproblems
    int offset = result[mid];
    for (int i = mid + 1; i <= right; i++) {
        result[i] += offset;
    }
}
```

### Why This IS D&C:

**1. Independent Subproblems:**
```
The LEFT half [left, mid] can be solved INDEPENDENTLY
The RIGHT half [mid+1, right] can be solved INDEPENDENTLY

These two calls don't depend on each other!
```

**2. Multiple Recursive Branches (Tree Structure):**
```
Call tree:
                  computePrefix(0, 7)
                 /                  \
         computePrefix(0, 3)    computePrefix(4, 7)
            /         \              /          \
    compute(0,1)  compute(2,3)  compute(4,5)  compute(6,7)
       /    \       /    \        /    \        /    \
     (0)    (1)   (2)    (3)    (4)    (5)    (6)    (7)

TWO active branches at each level!
```

**3. Can Parallelize:**
```
You CAN compute [left, mid] and [mid+1, right] in PARALLEL!

Thread 1: Solve left half
Thread 2: Solve right half (at the same time!)

Then combine results
```

---

## The Critical Difference: Index Ranges Define the "Division"

### Method 1 (NOT D&C):
```java
performPrefixSum(arr, result, current - 1);  // Solves [0 to current-1]
// Then use that result for current

Problem space: [0, current] → [0, current-1] → [0, current-2] → ...
Reduction: n → n-1 → n-2 → ... (linear chain)
```

### Method 2 (IS D&C):
```java
computePrefix(arr, result, left, mid);        // Solves [left, mid]
computePrefix(arr, result, mid + 1, right);   // Solves [mid+1, right]

Problem space: [left, right] → [left, mid] AND [mid+1, right]
Division: n → n/2 + n/2 (binary split into independent parts)
```

---

## Addressing Your Concern: "mid, mid+1 doesn't split the array"

You're right that we're not creating new arrays. But look at what we're doing:

### Method 2 - Range-Based Division:

```java
computePrefix(arr, result, 0, 7)  // Process entire range [0, 7]
                ↓
    /                              \
computePrefix(0, 3)          computePrefix(4, 7)
[Solve indices 0-3]          [Solve indices 4-7]
     ↓                              ↓
These are INDEPENDENT problems operating on different index ranges!
```

**The "splitting" happens logically through the `left` and `right` parameters:**
- `left` and `right` define the **problem boundary**
- Each recursive call works on a **different range**
- These ranges **don't overlap** and are **independent**

---

## Visual Proof: Problem Space vs Physical Array

### The Array (Physical - Never Split):
```
arr = [1, 2, 3, 4, 5, 6, 7, 8]
      ↑                       ↑
  Always the same array, never copied or split
```

### Method 1 - Problem Space (Sequential):
```
Call 1: Process index 7 (needs result from index 6)
Call 2: Process index 6 (needs result from index 5)
Call 3: Process index 5 (needs result from index 4)
...

Linear dependency chain - NOT divisible into independent parts
```

### Method 2 - Problem Space (Divided):
```
Level 0: [0, 1, 2, 3, 4, 5, 6, 7] ← Process entire range
              ↓         ↓
Level 1: [0, 1, 2, 3] | [4, 5, 6, 7] ← Two independent ranges
            ↓     ↓       ↓     ↓
Level 2: [0,1] [2,3]  [4,5] [6,7] ← Four independent ranges
          ↓ ↓   ↓ ↓    ↓ ↓   ↓ ↓
Level 3: [0][1][2][3][4][5][6][7] ← Eight independent ranges

Each range can be solved WITHOUT waiting for others!
```

---

## Concrete Example: arr = [1, 2, 3, 4]

### Method 1 Execution (Sequential):

```
Time 0: Start performPrefixSum(arr, result, 3)
Time 1:   Call performPrefixSum(arr, result, 2)
Time 2:     Call performPrefixSum(arr, result, 1)
Time 3:       Call performPrefixSum(arr, result, 0)
Time 4:       Base case: result[0] = 1
Time 5:     Return, compute result[1] = result[0] + arr[1] = 1 + 2 = 3
Time 6:   Return, compute result[2] = result[1] + arr[2] = 3 + 3 = 6
Time 7: Return, compute result[3] = result[2] + arr[3] = 6 + 4 = 10

Total: 8 sequential time units (cannot parallelize)
```

### Method 2 Execution (Parallel Potential):

```
Time 0: Start computePrefix(arr, result, 0, 3)
Time 1: Split into:
        ├─ computePrefix(0, 1) [Can run in parallel]
        └─ computePrefix(2, 3) [Can run in parallel]

Time 2: 
  Thread 1: computePrefix(0, 1) splits into:
            ├─ computePrefix(0, 0) → result[0] = 1
            └─ computePrefix(1, 1) → result[1] = 2
            
  Thread 2: computePrefix(2, 3) splits into:
            ├─ computePrefix(2, 2) → result[2] = 3
            └─ computePrefix(3, 3) → result[3] = 4

Time 3:
  Thread 1: Merge (0,1): result[1] = result[0] + result[1] = 1 + 2 = 3
  Thread 2: Merge (2,3): result[3] = result[2] + result[3] = 3 + 4 = 7

Time 4: Merge (0,3): 
        offset = result[1] = 3
        result[2] += 3 → 3 + 3 = 6
        result[3] += 3 → 7 + 3 = 10

Total: 5 time units (WITH parallelization possible!)
```

---

## The Essence of D&C: Independence, Not Physical Splitting

### NOT D&C (Method 1):
```java
solve(n) {
    solve(n-1);  // ← Must finish this FIRST
    use_result_from(n-1);  // ← Then do this
}

Characteristics:
- One call at a time
- Each depends on previous
- Linear chain
- Cannot parallelize
```

### IS D&C (Method 2):
```java
solve(left, right) {
    solve(left, mid);      // ← Can run independently
    solve(mid+1, right);   // ← Can run independently
    combine(left, mid, right);  // ← Merge after both done
}

Characteristics:
- Multiple simultaneous calls
- Subproblems independent
- Tree structure
- Can parallelize
```

---

## The Formal Definition of Divide and Conquer

**Divide and Conquer requires:**

1. ✅ **Divide:** Break problem into 2+ **non-overlapping, independent** subproblems
2. ✅ **Conquer:** Solve subproblems recursively (can be done in parallel)
3. ✅ **Combine:** Merge subproblem solutions

### Method 1:
- ❌ Divides into 1 subproblem only (linear reduction)
- ❌ Subproblems are dependent (sequential)
- ❌ No real "combine" step (just extends chain)

### Method 2:
- ✅ Divides into 2 subproblems
- ✅ Subproblems are independent (can solve simultaneously)
- ✅ Has combine step (adjust right half with left's offset)

---

## Summary

| Aspect | Method 1 | Method 2 |
|--------|----------|----------|
| **Physical array splitting?** | ❌ No | ❌ No |
| **Logical problem division?** | ❌ No (sequential) | ✅ Yes (independent ranges) |
| **Number of subproblems** | 1 | 2 |
| **Subproblem independence** | ❌ Dependent | ✅ Independent |
| **Can parallelize?** | ❌ No | ✅ Yes |
| **Recursion structure** | Linear chain | Binary tree |
| **Is Divide & Conquer?** | ❌ NO | ✅ YES |

---

## Final Answer

**Method 2 IS divide and conquer because:**

1. ✅ It divides the **problem space** (index ranges) into **two independent parts**
2. ✅ The left half `[left, mid]` and right half `[mid+1, right]` can be solved **simultaneously**
3. ✅ It has a **combine step** that merges the results
4. ✅ Forms a **tree structure** of recursive calls

**Method 1 is NOT divide and conquer because:**

1. ❌ It only creates **one subproblem** at a time
2. ❌ Each step **depends on** the previous step (sequential)
3. ❌ Cannot be parallelized
4. ❌ Forms a **linear chain**, not a tree

**The key is not physical array splitting, but logical problem partitioning into independent subproblems!** 🎯
*/

public class PrefixSumViaDC {
    public static void performPrefixSum(int[] arr, int[] result, int current) {
        if (current == 0) {
            result[0] = arr[0];
            return;
        }
        performPrefixSum(arr, result, current - 1);
        result[current] = result[current - 1] + arr[current];        
    }
    public static void computePrefix(int[] arr, int[] result, int left, int right) {
        if (left == right) {
            result[left] = arr[left];
            return;
        }
        int mid = (left + right) / 2;
        // Compute prefix sums for left half
        computePrefix(arr, result, left, mid);
        // Compute prefix sums for right half
        computePrefix(arr, result, mid + 1, right);
        // Adjust right half by adding total sum of left half
        int offset = result[mid];
        for (int i = mid + 1; i <= right; i++) {
            result[i] += offset;
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        int[] result = new int[n];
        performPrefixSum(arr, result, n - 1);
        // computePrefix(arr, result, 0, n - 1);
        System.out.println(Arrays.toString(arr));
        System.out.print("Prefix Sum: ");
        System.out.println(Arrays.toString(result));
        System.out.println();
        int[] arr2 = {5, 10, 15, 20, 25};
        n = arr2.length;
        int[] result2 = new int[n];
        // computePrefix(arr2, result2, 0, n - 1);
        performPrefixSum(arr2, result2, n - 1);
        System.out.println(Arrays.toString(arr2));
        System.out.print("Prefix Sum: ");
        System.out.println(Arrays.toString(result2));
        System.out.println();
        int[] arr3 = {3, 1, 4, 1, 5, 9};
        n = arr3.length;
        int[] result3 = new int[n];
        // computePrefix(arr3, result3, 0, n - 1);
        performPrefixSum(arr3, result3, n - 1);
        System.out.println(Arrays.toString(arr3));
        System.out.print("Prefix Sum: ");
        System.out.println(Arrays.toString(result3));
    }
}
/* 
- Time: O(nlog n) because each level of recursion adjusts half the array.
- Space: O(n) for the result array, plus recursion stack depth O(\log n).

Perfect — let’s trace the **prefix sum of `[1,2,3,4]` using divide and conquer** step by step so your trainees can *see* how recursion unfolds.  

---

### 🔎 Recursion Trace (Tree Style)

**Input:** `[1, 2, 3, 4]`

1. **Call:** `computePrefix(arr, result, 0, 3)`  
   - Split into `[1,2]` (left) and `[3,4]` (right).

---

#### Left Half `[1,2]`
2. **Call:** `computePrefix(arr, result, 0, 1)`  
   - Split into `[1]` and `[2]`.

   - `[1]` → base case → `result[0] = 1`  
   - `[2]` → base case → `result[1] = 2`  
   - Combine: offset = `result[0] = 1` → add to right half → `result[1] = 2 + 1 = 3`  

   ✅ Left half result: `[1, 3]`

---

#### Right Half `[3,4]`
3. **Call:** `computePrefix(arr, result, 2, 3)`  
   - Split into `[3]` and `[4]`.

   - `[3]` → base case → `result[2] = 3`  
   - `[4]` → base case → `result[3] = 4`  
   - Combine: offset = `result[2] = 3` → add to right half → `result[3] = 4 + 3 = 7`  

   ✅ Right half result: `[3, 7]`

---

#### Final Combine
4. Back to root: offset = `result[1] = 3` (sum of left half).  
   - Add offset to right half:  
     - `result[2] = 3 + 3 = 6`  
     - `result[3] = 7 + 3 = 10`  

✅ Final result: `[1, 3, 6, 10]`

---

### 🌳 Visual Recursion Tree

```
computePrefix(0,3) → arr=[1,2,3,4]
├── computePrefix(0,1) → arr=[1,2]
│   ├── [1] → result[0]=1
│   └── [2] → result[1]=2 → +offset(1)=3
│   ✅ Left = [1,3]
└── computePrefix(2,3) → arr=[3,4]
    ├── [3] → result[2]=3
    └── [4] → result[3]=4 → +offset(3)=7
    ✅ Right = [3,7]
Combine: offset=3 → Right becomes [6,10]
✅ Final = [1,3,6,10]
```

---

This trace makes it crystal clear:  
- Each half is solved independently.  
- The **combine step** shifts the right half by the sum of the left half.  
- Prefix sums emerge naturally from recursive offsets.

---

Would you like me to prepare a **diagram image** of this recursion tree (boxes and arrows) so your trainees can visualize it even better?

*/