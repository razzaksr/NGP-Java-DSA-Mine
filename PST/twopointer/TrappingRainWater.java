package PST.twopointer;

public class TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[]{4,2,0,3,2,5}));
    }
    
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int n = height.length;
        int left = 0, right = n - 1, leftMax = 0, rightMax = 0, water = 0;
        // Two pointer approach
        while (left < right) {
            if (height[left] < height[right]) {
                // Process left side
                if (height[left] >= leftMax) leftMax = height[left];
                else water += leftMax - height[left];
                left++;
            } else {
                // Process right side
                if (height[right] >= rightMax) rightMax = height[right];
                else water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }
}
/* 
Great question! Let me explain the logic of water trapping step by step.

## Basic Principle: Water Level at Any Position

**Water can only be trapped at a position if there are taller bars on BOTH sides.**

The amount of water trapped at any position = `min(max_left, max_right) - current_height`

Where:
- `max_left` = tallest bar to the LEFT of current position
- `max_right` = tallest bar to the RIGHT of current position
- `current_height` = height of bar at current position

## Visual Example

Let's use `[0,1,0,2,1,0,1,3,2,1,2,1]`:

```
Height:  0  1  0  2  1  0  1  3  2  1  2  1
Index:   0  1  2  3  4  5  6  7  8  9 10 11

Visual representation:
                    ___
                   |   |
        ___        |   |___    ___
       |   |       |   |   |  |   |
       |   |___    |   |   |  |   |___
       |   |   |___|   |   |__|   |   |___
    ___|   |   |   |   |   |  |   |   |   |
```

Now let's add water (represented by ~):

```
                    ___
                   |   |
        ___~~~~~~__|   |___~~~~___
       |   |~~~~~~~|   |   |~~|   |
       |   |___~~~~|   |   |~~|   |___
       |   |   |___|   |   |__|   |   |___
    ___|   |   |   |   |   |  |   |   |   |
    
Index:   0  1  2  3  4  5  6  7  8  9 10 11
```

## Step-by-Step Water Calculation

Let me trace through each position:

**Index 0 (height=0):**
- max_left = 0 (nothing on left)
- max_right = 3 (tallest on right)
- Water = min(0,3) - 0 = **0** ❌ (no left boundary)

**Index 1 (height=1):**
- max_left = 0
- max_right = 3
- Water = min(0,3) - 1 = **0** ❌ (left max not tall enough)

**Index 2 (height=0):**
- max_left = 1
- max_right = 3
- Water = min(1,3) - 0 = **1** ✓ (trapped between bar 1 and bar 3)

**Index 3 (height=2):**
- max_left = 1
- max_right = 3
- Water = min(1,3) - 2 = **0** ❌ (current bar too tall)

**Index 4 (height=1):**
- max_left = 2
- max_right = 3
- Water = min(2,3) - 1 = **1** ✓ (trapped between bar 2 and bar 3)

**Index 5 (height=0):**
- max_left = 2
- max_right = 3
- Water = min(2,3) - 0 = **2** ✓ (trapped between bar 2 and bar 3)

**Index 6 (height=1):**
- max_left = 2
- max_right = 3
- Water = min(2,3) - 1 = **1** ✓ (trapped between bar 2 and bar 3)

**Index 7 (height=3):**
- max_left = 2
- max_right = 2
- Water = min(2,2) - 3 = **0** ❌ (current bar too tall)

**Index 8 (height=2):**
- max_left = 3
- max_right = 2
- Water = min(3,2) - 2 = **0** ❌ (current bar at water level)

**Index 9 (height=1):**
- max_left = 3
- max_right = 2
- Water = min(3,2) - 1 = **1** ✓ (trapped between bar 3 and bar 2)

**Index 10 (height=2):**
- max_left = 3
- max_right = 1
- Water = min(3,1) - 2 = **0** ❌ (right max not tall enough)

**Index 11 (height=1):**
- max_left = 3
- max_right = 0
- Water = min(3,0) - 1 = **0** ❌ (no right boundary)

**Total Water = 1 + 1 + 2 + 1 + 1 = 6 units** ✓

## Why Two Pointer Approach Works

The two-pointer approach is clever because:

1. **We don't need to know the exact max on the other side**
2. We only need to know which side has the smaller max
3. If `height[left] < height[right]`, we know:
   - Whatever is on the right is AT LEAST `height[right]`
   - So `leftMax` is the limiting factor
   - We can safely calculate water based on `leftMax`

**The key insight:** Water level is determined by the SHORTER wall, not the taller one!

Does this clarify the logic?
*/