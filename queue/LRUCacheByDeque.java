package queue;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

class LRUCache {
    private int capacity;
    private Deque<Integer> dq;       // stores keys in LRU order
    private HashMap<Integer, Integer> map; // key → value

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.dq = new LinkedList<>();
        this.map = new HashMap<>();
    }

    // Get value for key
    public int get(int key) {
        if (!map.containsKey(key)) {
            System.out.println("Key " + key + " not found!");
            return -1; // key not present
        }

        // Move this key to front (most recently used)
        dq.remove(key);
        dq.addFirst(key);

        return map.get(key);
    }

    // Put key-value pair
    public void put(int key, int value) {
        // If key already exists → update and move to front
        if (map.containsKey(key)) {
            dq.remove(key);
            dq.addFirst(key);
            map.put(key, value);
            return;
        }

        // Cache is full → remove LRU key
        if (dq.size() == capacity) {
            int lru = dq.removeLast(); // least recently used
            map.remove(lru);
            System.out.println("Evicted LRU key: " + lru);
        }

        // Insert new key
        dq.addFirst(key);
        map.put(key, value);
    }

    // Display LRU Cache state
    public void display() {
        System.out.print("Cache (MRU → LRU): ");
        for (int key : dq) {
            System.out.print("(" + key + ":" + map.get(key) + ") ");
        }
        System.out.println();
    }
}

public class LRUCacheByDeque {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);

        cache.display();

        cache.get(1); // makes 1 most recently used
        cache.display();

        cache.put(4, 40); // should evict key 2
        cache.display();

        cache.get(3); // makes 3 MRU
        cache.display();
    }
}
