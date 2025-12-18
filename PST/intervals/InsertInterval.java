package PST.intervals;

import java.util.*;

public class InsertInterval {
    public static List<int[]> insertNewSlot(List<int[]> old, int[] newSlot) {
        List<int[]> updated = new ArrayList<>();
        int size = old.size();
        int index = 0;

        // Step 1: Add non-overlapping intervals before merging
        while (index < size && old.get(index)[1] < newSlot[0]) {
            updated.add(old.get(index));
            index++;
        }

        // Step 2: Merge overlapping intervals
        while (index < size && old.get(index)[0] <= newSlot[1]) {
            newSlot[0] = Math.min(old.get(index)[0], newSlot[0]);
            newSlot[1] = Math.max(old.get(index)[1], newSlot[1]);
            index++;
        }
        updated.add(newSlot);

        // Step 3: Add remaining intervals
        while (index < size) {
            updated.add(old.get(index));
            index++;
        }

        return updated;
    }

    // Utility method to print intervals
    private static void printIntervals(List<int[]> intervals) {
        for (int[] interval : intervals) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<int[]> old1 = Arrays.asList(new int[]{1,3}, new int[]{6,9});
        int[] new1 = {2,5};
        printIntervals(insertNewSlot(old1, new1));
        // Expected: [[1,5], [6,9]]

        List<int[]> old2 = Arrays.asList(
            new int[]{1,2}, new int[]{3,5}, new int[]{6,7}, new int[]{8,10}, new int[]{12,16}
        );
        int[] new2 = {4,8};
        printIntervals(insertNewSlot(old2, new2));
        // Expected: [[1,2], [3,10], [12,16]]
    }
}
/* 
**Deciding on the test case**

I’m thinking I should provide a step-by-step explanation of the code execution, focusing on one test case like old2/new2, since it looks more complex and fits the request. I'll trace how the variables evolve, without adding citations or a table since they weren’t needed. I'm planning to go through the loops and structure, showing each variable change so the user can follow along clearly. I’ll organize this with bold lead-ins for clarity and keep everything structured appropriately. Alright, let's do this!
### Stepwise trace for the complex test case with overlaps across multiple intervals

I’ll walk through `old2` and `new2` since it exercises all three steps clearly.

- **Input old intervals:** `[[1,2], [3,5], [6,7], [8,10], [12,16]]`
- **New slot to insert:** `[4,8]`

---

### Initial state

- **updated:** `[]`
- **index:** `0`
- **size:** `5`
- **newSlot:** `[4,8]`

---

### Step 1: Add non-overlapping intervals before merging

We add intervals that end strictly before `newSlot[0]` (i.e., before `4`).

- **Check old[0] = [1,2]:**  
  - Condition: `old[0][1] < newSlot[0]` → `2 < 4` → true  
  - **Action:** add `[1,2]`  
  - **updated:** `[[1,2]]`  
  - **index:** `1`

- **Check old[1] = [3,5]:**  
  - Condition: `5 < 4` → false  
  - Stop Step 1.

State after Step 1:
- **updated:** `[[1,2]]`
- **index:** `1`
- **newSlot:** `[4,8]`

---

### Step 2: Merge overlapping intervals

We merge all intervals that start at or before `newSlot[1]` (i.e., `<= 8`), updating `newSlot` as we go.

- **Check old[1] = [3,5]:**  
  - Condition: `old[1][0] <= newSlot[1]` → `3 <= 8` → true  
  - **Merge:**  
    - newSlot[0] = min(3, 4) = 3  
    - newSlot[1] = max(5, 8) = 8  
  - **newSlot:** `[3,8]`  
  - **index:** `2`

- **Check old[2] = [6,7]:**  
  - Condition: `6 <= 8` → true  
  - **Merge:**  
    - newSlot[0] = min(6, 3) = 3  
    - newSlot[1] = max(7, 8) = 8  
  - **newSlot:** `[3,8]`  
  - **index:** `3`

- **Check old[3] = [8,10]:**  
  - Condition: `8 <= 8` → true  
  - Overlaps at boundary (touching is considered overlapping since starts ≤ newSlot end).  
  - **Merge:**  
    - newSlot[0] = min(8, 3) = 3  
    - newSlot[1] = max(10, 8) = 10  
  - **newSlot:** `[3,10]`  
  - **index:** `4`

- **Check old[4] = [12,16]:**  
  - Condition: `12 <= 10` → false  
  - Stop Step 2.

- **Add merged newSlot to updated:**  
  - **updated:** `[[1,2], [3,10]]`

State after Step 2:
- **updated:** `[[1,2], [3,10]]`
- **index:** `4`
- **newSlot:** `[3,10]`

---

### Step 3: Add remaining intervals

Add everything left in `old` starting from `index`.

- **old[4] = [12,16]:**  
  - **Action:** add `[12,16]`  
  - **updated:** `[[1,2], [3,10], [12,16]]`  
  - **index:** `5`

End: `index == size`

---

### Final result

- **Output:** `[[1,2], [3,10], [12,16]]`
- Matches the expected output.

---

### Quick notes on correctness

- **Non-overlapping before merge:** We safely append intervals ending before the new slot starts.  
- **Merge window:** We keep expanding `newSlot` to cover all touching/overlapping intervals until we hit an interval that starts after `newSlot` ends.  
- **Tail append:** Remaining intervals are already beyond the merged range and can be appended directly.

*/