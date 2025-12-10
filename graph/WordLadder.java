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
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return level;

                // Generate neighbors by changing one letter
                char[] chars = word.toCharArray();
                for (int pos = 0; pos < chars.length; pos++) {
                    char original = chars[pos];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[pos] = c;
                        String newWord = new String(chars);
                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            visited.add(newWord);
                            queue.add(newWord);
                        }
                    }
                    chars[pos] = original; // restore
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder solver = new WordLadder();
        System.out.println(solver.ladderLength("hit", "cog",
            Arrays.asList("hot","dot","dog","lot","log","cog"))); 
        // Output: 5

        System.out.println(solver.ladderLength("hit", "cog",
            Arrays.asList("hot","dot","dog","lot","log"))); 
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
- BFS Traversal
- Queue starts with ("hit", level=1).
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