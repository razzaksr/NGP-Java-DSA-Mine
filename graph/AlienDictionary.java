package graph;

import java.util.*;

public class AlienDictionary {
    
    // Function to find order of characters
    public String findOrder(String[] words, int K) {
        // Step 1: Build graph
        Map<Character, List<Character>> graph = new HashMap<>();
        int[] inDegree = new int[K]; // track incoming edges
        
        // Initialize graph with all characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
            }
        }
        
        // Step 2: Compare adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    inDegree[c2 - 'a']++;
                    break; // only first differing character matters
                }
            }
        }
        
        // Step 3: Topological Sort (Kahn’s Algorithm)
        Queue<Character> queue = new LinkedList<>();
        for (char c : graph.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                queue.add(c);
            }
        }
        
        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            order.append(curr);
            
            for (char neighbor : graph.get(curr)) {
                inDegree[neighbor - 'a']--;
                if (inDegree[neighbor - 'a'] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return order.toString();
    }
    
    // Driver
    public static void main(String[] args) {
        AlienDictionary solver = new AlienDictionary();
        
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        int K = 4; // number of distinct characters
        
        String result = solver.findOrder(words, K);
        System.out.println("Alien Dictionary Order: " + result);
        // Expected Output: "bdac" (b → d → a → c)
    }
}