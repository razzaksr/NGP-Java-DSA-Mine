package PST.greedy;

public class BalancedStringSplit {
    public static int balancedStringSplit(String s) {
        int balance = 0, count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'R') balance++;
            else balance--;
            if (balance == 0) count++;
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(balancedStringSplit("RLRRLLRLRL"));
        System.out.println(balancedStringSplit("RLRRRLLRLL"));
        System.out.println(balancedStringSplit("LLLLRRRR"));
    }
}

/* 
Split a String in Balanced Strings
Stepwise Explanation
- Balanced string means equal number of L and R.
- Traverse the string:
- Maintain a counter: increment for R, decrement for L (or vice versa).
- Whenever counter = 0 → one balanced substring found.
- Count how many times counter = 0 → that’s the maximum number of balanced substrings.
Example Trace (Input: "RLRRLLRLRL")
- R → balance=1
- L → balance=0 → count=1
- R → balance=1
- R → balance=2
- L → balance=1
- L → balance=0 → count=2
- R → balance=1
- L → balance=0 → count=3
- R → balance=1
- L → balance=0 → count=4
- Answer = 4
*/