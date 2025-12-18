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
### Stepwise trace for the more involved test case with mixed coverage and gaps

I’ll trace `intervals2` and `queries2` because it shows adding/removing from the heap, uncovered queries, and late intervals cleanly.

- **Input intervals:** `[[2,3], [2,5], [1,8], [20,25]]`
- **Input queries:** `[2, 19, 5, 22]`

---

### Preprocessing and initial state

- **Sort intervals by start:** already sorted → `[[1,8], [2,3], [2,5], [20,25]]`
- **Attach indices and sort queries:**  
  - With indices: `[(2,0), (19,1), (5,2), (22,3)]`  
  - Sorted by query value: `[(2,0), (5,2), (19,1), (22,3)]`
- **Heap:** empty (min-heap of `[size, end]`)
- **result:** `[-1, -1, -1, -1]`
- **i (interval pointer):** `0`

---

### Process query = 2 (original index 0)

- **Add intervals starting ≤ 2:**
  - intervals[0] = [1,8] → size = 8 (8−1+1), push `[8, 8]`, i=1
  - intervals[1] = [2,3] → size = 2 (3−2+1), push `[2, 3]`, i=2
  - intervals[2] = [2,5] → size = 4 (5−2+1), push `[4, 5]`, i=3
  - intervals[3] = [20,25] → start=20 > 2, stop adding
- **Heap state:** `[[2,3], [8,8], [4,5]]` (ordered by size; top = `[2,3]`)
- **Remove intervals ending < 2:** none (`3,8,5` all ≥ 2)
- **Pick smallest covering interval:** top `[2,3]` → covers 2  
  - **result[0] = 2**
- **State now:**  
  - **heap:** `[[2,3], [8,8], [4,5]]`  
  - **result:** `[2, -1, -1, -1]`  
  - **i:** `3`

---

### Process query = 5 (original index 2)

- **Add intervals starting ≤ 5:**
  - intervals[3] = [20,25] → start=20 > 5, none added
- **Heap state before pruning:** `[[2,3], [8,8], [4,5]]`
- **Remove intervals ending < 5:**
  - Pop `[2,3]` because end=3 < 5
- **Heap after pruning:** `[[4,5], [8,8]]` (top `[4,5]`)
- **Pick smallest covering interval:** top `[4,5]` → covers 5  
  - **result[2] = 4**
- **State now:**  
  - **heap:** `[[4,5], [8,8]]`  
  - **result:** `[2, -1, 4, -1]`  
  - **i:** `3`

---

### Process query = 19 (original index 1)

- **Add intervals starting ≤ 19:**
  - intervals[3] = [20,25] → start=20 > 19, none added
- **Heap state before pruning:** `[[4,5], [8,8]]`
- **Remove intervals ending < 19:**
  - Pop `[4,5]` (end=5 < 19)
  - Pop `[8,8]` (end=8 < 19)
- **Heap becomes empty**
- **No interval covers 19:**  
  - **result[1] = -1**
- **State now:**  
  - **heap:** `[]`  
  - **result:** `[2, -1, 4, -1]`  
  - **i:** `3`

---

### Process query = 22 (original index 3)

- **Add intervals starting ≤ 22:**
  - intervals[3] = [20,25] → size = 6 (25−20+1), push `[6,25]`, i=4
- **Heap state:** `[[6,25]]`
- **Remove intervals ending < 22:** none (25 ≥ 22)
- **Pick smallest covering interval:** top `[6,25]` → covers 22  
  - **result[3] = 6**
- **Final state:**  
  - **heap:** `[[6,25]]`  
  - **result:** `[2, -1, 4, 6]`  
  - **i:** `4`

---

### Final output

- **Result mapped back to original query order:** `[2, -1, 4, 6]`
- Matches the expected output.

---

### Why this works (concise)

- **Sorted intervals and queries:** ensures we only add intervals relevant up to each query.  
- **Min-heap by size:** keeps the shortest currently covering interval at the top.  
- **Pruning by end < query:** removes intervals that cannot cover the current query, preventing false picks.  

If you want, I can also trace `intervals1` and `queries1` to show the behavior when many intervals overlap tightly and multiple queries fall inside the same merged coverage.

*/