package slidingwindow.fixed;

import java.util.Arrays;

public class ReverseArrayInKWindow {

    public static void reverseInWindow(int[] arr, int k) {
        int n = arr.length;

        for (int start = 0; start < n; start += k) {
            int left = start;
            int right = Math.min(start + k - 1, n - 1);

            while (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;

        reverseInWindow(arr, k);
        System.out.println(Arrays.toString(arr));
    }
}

/* 
Given an array and a window size K, reverse each subarray (window) of size K as you slide from left to right.

If the remaining elements are less than K, reverse them as well (common interview version).

This is sometimes called “Reverse array in groups of size K”.





Sliding Window Logic Explained

Move the window in steps of k

For each window:

Reverse elements in-place

Handle last window using:

Math.min(start + k - 1, n - 1)


Step-by-Step Reasoning
1️⃣ Outer for loop

Runs approximately n / k times (window jumps by k).

2️⃣ Inner while loop

For each window, it runs at most k / 2 times (reversing the window).

🔹 Total Operations Calculation
Total work =
(n / k) × (k / 2)
= n / 2


👉 Constants are ignored in Big-O notation

Time Complexity = O(n)

🔹 Why It Is NOT O(n²)
O(n²) happens when:

Inner loop runs n times for each outer loop iteration

But here:

Each element is swapped only once

Inner loop does NOT restart from 0 to n every time

The work is distributed, not repeated

🔹 Intuition (Very Important for Interviews)

Ask this question:
“How many times does a single array element participate in operations?”

✔ Answer: At most once
✔ Therefore: O(n)

🔹 One-Line Interview Answer 💡

“Although the loop is nested, the total number of operations across all windows is linear, so the time complexity is O(n), not O(n²).”

If you want, I can also:

Compare this with a true O(n²) nested loop

Give a dry run table

Show how to identify linear vs quadratic loops quickly

*/