package PST.greedy;

public class JumpGameII {
    // unreachable handled
    public static int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // If currentEnd cannot move forward, break early
                if (currentEnd <= i) {
                    return -1; // unreachable
                }
            }
        }

        // If farthest never reaches the last index
        return farthest >= nums.length - 1 ? jumps : -1;
    }
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
        System.out.println(jump(new int[]{2,3,0,1,4}));
        System.out.println(jump(new int[]{3,2,1,0,4}));
    }
}
/* 
Perfect, let’s do the **step‑by‑step trace** for this `JumpGameII` implementation in the same style as before.  
We’ll walk through **all three test cases** from `main` so you see how `jumps`, `currentEnd`, and `farthest` evolve.

---

## Test Case 1: `nums = [2,3,1,1,4]`

- **Initial:** `jumps=0, currentEnd=0, farthest=0`

**Loop (i from 0 to 3):**

- i=0 → farthest = max(0, 0+2)=2  
  - i==currentEnd (0==0) → jumps=1, currentEnd=2  
- i=1 → farthest = max(2, 1+3)=4  
- i=2 → farthest = max(4, 2+1)=4  
  - i==currentEnd (2==2) → jumps=2, currentEnd=4  
- i=3 → farthest = max(4, 3+1)=4  

**End:** farthest=4 ≥ last index=4 → return `jumps=2`

✅ Output: `2`

---

## Test Case 2: `nums = [2,3,0,1,4]`

- **Initial:** `jumps=0, currentEnd=0, farthest=0`

**Loop (i from 0 to 3):**

- i=0 → farthest = max(0, 0+2)=2  
  - i==currentEnd → jumps=1, currentEnd=2  
- i=1 → farthest = max(2, 1+3)=4  
- i=2 → farthest = max(4, 2+0)=4  
  - i==currentEnd (2==2) → jumps=2, currentEnd=4  
- i=3 → farthest = max(4, 3+1)=4  

**End:** farthest=4 ≥ last index=4 → return `jumps=2`

✅ Output: `2`

---

## Test Case 3: `nums = [3,2,1,0,4]`

- **Initial:** `jumps=0, currentEnd=0, farthest=0`

**Loop (i from 0 to 3):**

- i=0 → farthest = max(0, 0+3)=3  
  - i==currentEnd → jumps=1, currentEnd=3  
- i=1 → farthest = max(3, 1+2)=3  
- i=2 → farthest = max(3, 2+1)=3  
- i=3 → farthest = max(3, 3+0)=3  
  - i==currentEnd (3==3) → jumps=2, currentEnd=3  
  - But check: currentEnd <= i → 3 <= 3 → true → return `-1` (unreachable)

**End:** returns `-1` because we’re stuck at index 3 and cannot move forward to reach index 4.

✅ Output: `-1`

---

## Why this works

- **Greedy expansion:** `farthest` always tracks the maximum reachable index so far.  
- **Jump boundary:** When `i == currentEnd`, we’ve exhausted the current jump’s range, so we increment `jumps` and extend the boundary.  
- **Unreachable check:** If `currentEnd` doesn’t move forward (≤ i), we break early with `-1`.  
- **Final check:** If `farthest` never reaches the last index, return `-1`.

---

### Final Outputs from `main`

```
2
2
-1
```

Would you like me to also **draw a timeline diagram** (like ranges of indices per jump) for each test case? That often helps trainees visualize how the greedy expansion works.

*/