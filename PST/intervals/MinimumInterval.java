package PST.intervals;

import java.util.*;

public class MinimumInterval {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // Sort intervals by start
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Sort queries but keep original index
        int n = queries.length;
        int[][] queryWithIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            queryWithIndex[i][0] = queries[i];
            queryWithIndex[i][1] = i;
        }
        Arrays.sort(queryWithIndex, (a, b) -> a[0] - b[0]);
        System.out.println(Arrays.toString(queryWithIndex));
        // Min-heap: [size, end]
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int[] result = new int[n];
        Arrays.fill(result, -1);

        int i = 0; // pointer for intervals
        for (int[] q : queryWithIndex) {
            int query = q[0];
            int idx = q[1];

            // Add intervals that start <= query
            while (i < intervals.length && intervals[i][0] <= query) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                int size = right - left + 1;
                heap.offer(new int[]{size, right});
                i++;
            }

            // Remove intervals that end < query
            while (!heap.isEmpty() && heap.peek()[1] < query) {
                heap.poll();
            }

            // If heap not empty, smallest interval is at top
            if (!heap.isEmpty()) {
                result[idx] = heap.peek()[0];
            }
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        MinimumInterval solver = new MinimumInterval();
        int[][] intervals1 = {{1,4},{2,4},{3,6},{4,4}};
        int[] queries1 = {2,3,4,5};
        System.out.println(Arrays.toString(solver.minInterval(intervals1, queries1)));
        // Output: [3, 3, 1, 4]

        int[][] intervals2 = {{2,3},{2,5},{1,8},{20,25}};
        int[] queries2 = {2,19,5,22};
        System.out.println(Arrays.toString(solver.minInterval(intervals2, queries2)));
        // Output: [2, -1, 4, 6]
    }
}
/* 
Great question! Let me explain why **2 is NOT in the interval [1,4]** but IS in [2,4].

## Understanding the Problem

The question asks: **For each query value, what is the SMALLEST interval that contains it?**

For **Query = 2**, we need to find which intervals contain the value 2:

---

## Intervals that contain 2:

Let's check each interval:

### Interval [1,4]:
- Start: 1, End: 4
- Contains: 1, 2, 3, 4
- **Does it contain 2?** ✅ YES
- **Size:** 4 - 1 + 1 = **4**

### Interval [2,4]:
- Start: 2, End: 4
- Contains: 2, 3, 4
- **Does it contain 2?** ✅ YES
- **Size:** 4 - 2 + 1 = **3**

### Interval [3,6]:
- Start: 3, End: 6
- Contains: 3, 4, 5, 6
- **Does it contain 2?** ❌ NO (2 < 3)

### Interval [4,4]:
- Start: 4, End: 4
- Contains: 4
- **Does it contain 2?** ❌ NO (2 < 4)

---

## The Answer: Why [2,4] and not [1,4]?

**Both [1,4] and [2,4] contain the value 2**, but we need the **SMALLEST** interval:

| Interval | Contains 2? | Size | Winner? |
|----------|-------------|------|---------|
| **[1,4]** | ✅ Yes | **4** | ❌ Larger |
| **[2,4]** | ✅ Yes | **3** | ✅ **SMALLEST!** |
| [3,6] | ❌ No | 4 | - |
| [4,4] | ❌ No | 1 | - |

**Answer: [2,4] with size 3** because it's the smallest interval containing 2.

---

## Visual Representation

```
Number line:
    1   2   3   4   5   6
    └───────────┘         [1,4] - size 4 ✓ contains 2
        └───────┘         [2,4] - size 3 ✓ contains 2 (SMALLER!)
            └───────────┘ [3,6] - size 4 ✗ doesn't contain 2
                └─┘       [4,4] - size 1 ✗ doesn't contain 2

Query = 2 is at position 2
         ↑
```

The interval [2,4] is **tighter** around the value 2, making it smaller than [1,4].

---

## All Queries Explained

### Query = 2:
- Intervals containing 2: **[1,4]** (size 4), **[2,4]** (size 3)
- Smallest: **[2,4]** → Answer: **3**

### Query = 3:
- Intervals containing 3: **[1,4]** (size 4), **[2,4]** (size 3), **[3,6]** (size 4)
- Smallest: **[2,4]** → Answer: **3**

### Query = 4:
- Intervals containing 4: **[1,4]** (size 4), **[2,4]** (size 3), **[3,6]** (size 4), **[4,4]** (size 1)
- Smallest: **[4,4]** → Answer: **1**

### Query = 5:
- Intervals containing 5: **[3,6]** (size 4)
- Only one interval: **[3,6]** → Answer: **4**

---

## Key Concept

An interval `[left, right]` contains a value `x` if:
```
left ≤ x ≤ right
```

For value **2**:
- **[1,4]**: 1 ≤ 2 ≤ 4 ✅ (size = 4)
- **[2,4]**: 2 ≤ 2 ≤ 4 ✅ (size = 3) ← **SMALLER!**

Both contain 2, but [2,4] is smaller, so it wins!

---

## Why This Matters

The problem specifically asks for the **minimum size** interval. If multiple intervals contain the query value, we must choose the one with the smallest length.

Think of it like finding the tightest fitting box that still contains the value. ✅

Does this clarify why [2,4] is chosen over [1,4] for query = 2?

*/