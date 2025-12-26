package dc;

import java.util.*;

public class MaxCharSplitHalves {
    public static char findMaxChar(String s) {
        int n = s.length();
        int mid = (n + 1) / 2; // first half gets extra if odd
        String firstHalf = s.substring(0, mid);
        String secondHalf = s.substring(mid);

        char maxFirst = 'a';
        for (char c : firstHalf.toCharArray()) {
            if (c > maxFirst) maxFirst = c;
        }

        char maxSecond = 'a';
        for (char c : secondHalf.toCharArray()) {
            if (c > maxSecond) maxSecond = c;
        }

        return (maxFirst > maxSecond) ? maxFirst : maxSecond;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        System.out.println(findMaxChar(s));
        sc.close();
    }
}