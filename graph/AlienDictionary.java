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

/* 
Perfect, let’s **trace the graph structure step by step** so your trainees can *see* how the algorithm evolves. I’ll show both the **adjacency list** and the **in-degree array** after each comparison.

---

### 📚 Input
Words = `{"baa", "abcd", "abca", "cab", "cad"}`  
K = 4 (characters involved: `a, b, c, d`)

---

### Step 1: Initialize Graph
Adjacency List (empty edges, just nodes):
```
a : []
b : []
c : []
d : []
```
In-degree array (all zero):
```
a=0, b=0, c=0, d=0
```

---

### Step 2: Compare Adjacent Words

#### (i) Compare `"baa"` vs `"abcd"`
- First differing char: `b` vs `a` → edge `b → a`
```
Adjacency:
a : []
b : [a]
c : []
d : []
```
In-degree:
```
a=1, b=0, c=0, d=0
```

---

#### (ii) Compare `"abcd"` vs `"abca"`
- First differing char: `d` vs `a` → edge `d → a`
```
Adjacency:
a : []
b : [a]
c : []
d : [a]
```
In-degree:
```
a=2, b=0, c=0, d=0
```

---

#### (iii) Compare `"abca"` vs `"cab"`
- First differing char: `a` vs `c` → edge `a → c`
```
Adjacency:
a : [c]
b : [a]
c : []
d : [a]
```
In-degree:
```
a=2, b=0, c=1, d=0
```

---

#### (iv) Compare `"cab"` vs `"cad"`
- First differing char: `b` vs `d` → edge `b → d`
```
Adjacency:
a : [c]
b : [a, d]
c : []
d : [a]
```
In-degree:
```
a=2, b=0, c=1, d=1
```

---

### Step 3: Topological Sort (Kahn’s Algorithm)

- Start with nodes having in-degree = 0 → `b`  
- Process order:

1. **Take `b`** → result `"b"`  
   - Reduce in-degree of `a` (2→1), `d` (1→0)  
   - Queue now: `d`

2. **Take `d`** → result `"bd"`  
   - Reduce in-degree of `a` (1→0)  
   - Queue now: `a`

3. **Take `a`** → result `"bda"`  
   - Reduce in-degree of `c` (1→0)  
   - Queue now: `c`

4. **Take `c`** → result `"bdac"`

✅ Final Order = `"bdac"`

---

### Visual Recap
Graph edges:  
- `b → a`  
- `d → a`  
- `a → c`  
- `b → d`

Topological order: **b → d → a → c**

---

This stepwise adjacency + in-degree trace makes it crystal clear how the algorithm builds and resolves the alien dictionary order.  

Would you like me to also **draw this as a node-arrow diagram** (visual graph with arrows) so your trainees can see the dependencies more intuitively?

*/