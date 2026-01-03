package slidingwindow.variable;

import java.util.*;

public class VariableWindowLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> window = new HashSet<>();
        int start = 0, maxLen = 0;

        for (int end = 0; end < n; end++) {
            char c = s.charAt(end);

            // shrink window until duplicate is removed
            while (window.contains(c)) {
                window.remove(s.charAt(start));
                start++;
            }

            window.add(c);
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3 ("abc")
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // 1 ("b")
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // 3 ("wke")
        System.out.println(lengthOfLongestSubstring(""));         // 0
    }
}

/*
Given a string, find the length of the longest substring without repeating characters

Stepwise Trace (Your Style)
Take s = "abcabcbb".
- Expand window:
- Add a → window = {a}, length = 1, maxLen = 1
- Add b → window = {a,b}, length = 2, maxLen = 2
- Add c → window = {a,b,c}, length = 3, maxLen = 3
- Next char = a (duplicate):
- Shrink: remove a at start → window = {b,c}, start=1
- Add new a → window = {b,c,a}, length = 3, maxLen = 3
- Next char = b (duplicate):
- Shrink: remove b at start → window = {c,a}, start=2
- Add new b → window = {c,a,b}, length = 3, maxLen = 3
- Next char = c (duplicate):
- Shrink: remove c at start → window = {a,b}, start=3
- Add new c → window = {a,b,c}, length = 3, maxLen = 3
- Next char = b (duplicate):
- Shrink: remove a → window = {b,c}, start=4
- Shrink: remove b → window = {c}, start=5
- Add new b → window = {c,b}, length = 2, maxLen = 3
- Next char = b (duplicate again):
- Shrink: remove c → window = {b}, start=6
- Shrink: remove b → window = {}, start=7
- Add new b → window = {b}, length = 1, maxLen = 3
Final Answer = 3
Complexity
- Time: O(n) — each character enters and leaves the window once.
- Space: O(min(n,charset)) — at most one entry per character.
*/