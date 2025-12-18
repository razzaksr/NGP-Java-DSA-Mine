package PST.greedy;

public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;   // Net gas after completing the circuit
        int currTank = 0;    // Current gas in tank while traversing
        int startIndex = 0;  // Candidate starting station

        for (int i = 0; i < gas.length; i++) {
            totalTank += gas[i] - cost[i];
            currTank += gas[i] - cost[i];

            // If at any point current tank goes negative,
            // we cannot start from 'startIndex'
            if (currTank < 0) {
                startIndex = i + 1; // Next station becomes candidate
                currTank = 0;       // Reset current tank
            }
        }

        // If total gas is less than total cost, impossible to complete circuit
        return totalTank >= 0 ? startIndex : -1;
    }   
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        System.out.println(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }
}
/*
Alright, let’s break down the **Gas Station** problem step by step in the same style as before.  
We’ll trace both test cases from `main` so you see how `totalTank`, `currTank`, and `startIndex` evolve.

---

## Test Case 1:  
`gas = [1,2,3,4,5]`  
`cost = [3,4,5,1,2]`

- **Initial:** `totalTank=0, currTank=0, startIndex=0`

**Loop through stations:**

- i=0 → gain = 1−3 = −2  
  - totalTank = −2, currTank = −2  
  - currTank < 0 → reset: startIndex=1, currTank=0  

- i=1 → gain = 2−4 = −2  
  - totalTank = −4, currTank = −2  
  - currTank < 0 → reset: startIndex=2, currTank=0  

- i=2 → gain = 3−5 = −2  
  - totalTank = −6, currTank = −2  
  - currTank < 0 → reset: startIndex=3, currTank=0  

- i=3 → gain = 4−1 = +3  
  - totalTank = −3, currTank = +3  

- i=4 → gain = 5−2 = +3  
  - totalTank = 0, currTank = +6  

**End:** totalTank = 0 ≥ 0 → return startIndex=3  

✅ Output: `3`  
Meaning: If you start at station 3 (gas=4, cost=1), you can complete the circuit.

---

## Test Case 2:  
`gas = [2,3,4]`  
`cost = [3,4,3]`

- **Initial:** `totalTank=0, currTank=0, startIndex=0`

**Loop through stations:**

- i=0 → gain = 2−3 = −1  
  - totalTank = −1, currTank = −1  
  - currTank < 0 → reset: startIndex=1, currTank=0  

- i=1 → gain = 3−4 = −1  
  - totalTank = −2, currTank = −1  
  - currTank < 0 → reset: startIndex=2, currTank=0  

- i=2 → gain = 4−3 = +1  
  - totalTank = −1, currTank = +1  

**End:** totalTank = −1 < 0 → return −1  

✅ Output: `-1`  
Meaning: No station can serve as a valid starting point to complete the circuit.

---

## Why this works

- **totalTank:** Tracks overall feasibility. If total gas < total cost, impossible to finish.  
- **currTank:** Tracks feasibility of current candidate start. If it dips below 0, that start fails.  
- **startIndex:** Updated to the next station whenever the current candidate fails.  
- **Final check:** Only if totalTank ≥ 0 do we return the candidate startIndex.

---

### Final Outputs from `main`

```
3
-1
```

Would you like me to also **draw a circular path trace** (showing how the car moves station by station with fuel levels) for the first test case? That often helps visualize why starting at station 3 works.
 
*/