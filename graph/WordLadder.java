package graph;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1; // beginWord counts as level 1

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println("queue "+queue);
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                System.out.println("Polled "+word);
                if (word.equals(endWord)) return level;

                // Generate neighbors by changing one letter
                char[] chars = word.toCharArray();
                System.out.println("Before neighbors\n"+Arrays.toString(chars));
                for (int pos = 0; pos < chars.length; pos++) {
                    char original = chars[pos];
                    System.out.println("taken original "+original);
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[pos] = c;
                        String newWord = new String(chars);
                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            System.out.println("new match "+newWord+" available in dict");
                            visited.add(newWord);
                            queue.add(newWord);
                            System.out.println(visited+"\n"+queue);
                        }
                    }
                    chars[pos] = original; // restore
                    System.out.println("Restoring "+Arrays.toString(chars));
                }
            }
            level++;
            System.out.println("Level incremented "+level);
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder solver = new WordLadder();
        System.out.println(solver.ladderLength("hit", "cog",
            Arrays.asList("hot","dot","dog","lot","log","cog"))); 
        // Output: 5

        // System.out.println(solver.ladderLength("hit", "cog",
        //     Arrays.asList("hot","dot","dog","lot","log"))); 
        // Output: 0
    }
}

/* 
Stepwise Explanation (Your Style)
Example 1
beginWord = "hit"
endWord   = "cog"
wordList  = ["hot","dot","dog","lot","log","cog"]


- Graph Construction (Implicit)
- We don’t build all edges upfront (too costly).
- Instead, for each word, generate all possible one-letter variations and check if they’re in the dictionary.
- Example: "hit" → *it, h*t, hi*
- Matches "hot".

Another tracking:
queue [hit]
Polled hit
Before neighbors
[h, i, t]
taken original h
Restoring [h, i, t]
taken original i
new match hot available in dict
[hit, hot]
[hot]
Restoring [h, i, t]
taken original t
Restoring [h, i, t]
Level incremented 2
queue [hot]
Polled hot
Before neighbors
[h, o, t]
taken original h
new match dot available in dict
[hit, dot, hot]
[dot]
new match lot available in dict
[lot, hit, dot, hot]
[dot, lot]
Restoring [h, o, t]
taken original o
Restoring [h, o, t]
taken original t
Restoring [h, o, t]
Level incremented 3
queue [dot, lot]
Polled dot
Before neighbors
[d, o, t]
taken original d
Restoring [d, o, t]
taken original o
Restoring [d, o, t]
taken original t
new match dog available in dict
[lot, hit, dot, hot, dog]
[lot, dog]
Restoring [d, o, t]
Polled lot
Before neighbors
[l, o, t]
taken original l
Restoring [l, o, t]
taken original o
Restoring [l, o, t]
taken original t
new match log available in dict
[lot, hit, log, dot, hot, dog]
[dog, log]
Restoring [l, o, t]
Level incremented 4
queue [dog, log]
Polled dog
Before neighbors
[d, o, g]
taken original d
new match cog available in dict
[lot, hit, log, dot, cog, hot, dog]
[log, cog]
Restoring [d, o, g]
taken original o
Restoring [d, o, g]
taken original g
Restoring [d, o, g]
Polled log
Before neighbors
[l, o, g]
taken original l
Restoring [l, o, g]
taken original o
Restoring [l, o, g]
taken original g
Restoring [l, o, g]
Level incremented 5
queue [cog]
Polled cog
5

BFS Traversal
Queue starts with ("hit", level=1).
- Visit "hit" → neighbors: "hot".
- Queue: ("hot", level=2).
- Visit "hot" → neighbors: "dot", "lot".
- Queue: ("dot",2+1=3), ("lot",3).
- Visit "dot" → neighbor "dog".
- Queue: ("dog",4).
- Visit "dog" → neighbor "cog".
- Found endWord at level 5.
- Answer
Shortest transformation length = 5

Example 2beginWord = "hit"
endWord   = "cog"
wordList  = ["hot","dot","dog","lot","log"]
- "cog" not in dictionary → immediately return 0.

*/