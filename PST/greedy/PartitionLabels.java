package PST.greedy;

import java.util.*;

public class PartitionLabels {
    public static List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(partitionLabels("eccbbbbdec"));
    }
}

/* 
Partition Labels
Stepwise Explanation
- Record last occurrence of each character in the string.
- Iterate through string:
- Track end = farthest last occurrence seen so far.
- When current index == end, we found a partition.
- Store partition size, reset start.
- Return list of sizes.

Example Trace (Input: "ababcbacadefegdehijhklij")
- Last occurrence map built.
- Traverse:
- Partition 1 → "ababcbaca" → size 9
- Partition 2 → "defegde" → size 7
- Partition 3 → "hijhklij" → size 8
- Answer = [9,7,8]
*/