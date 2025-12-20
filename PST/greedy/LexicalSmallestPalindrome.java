package PST.greedy;

public class LexicalSmallestPalindrome {
    public static String makeSmallestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            if (arr[i] != arr[j]) {
                char smaller = (arr[i] < arr[j]) ? arr[i] : arr[j];
                arr[i] = arr[j] = smaller;
            }
            i++; j--;
        }
        return new String(arr);
    }
    public static void main(String[] args) {
        System.out.println(makeSmallestPalindrome("egcfe"));
        System.out.println(makeSmallestPalindrome("abcd"));
        System.out.println(makeSmallestPalindrome("seven"));
    }
}
/* 
Lexicographically Smallest Palindrome
Stepwise Explanation
- Use two pointers: left and right.
- While left < right:
- If chars differ → replace with smaller one (lexicographically).
- If same → keep.
- Build palindrome string.

Example Trace (Input: "egcfe")
- Compare e vs e → same.
- Compare g vs f → replace both with f → string becomes "efcfe".
- Done.
- Answer = "efcfe"
*/