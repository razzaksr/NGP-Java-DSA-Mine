package queue;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        // true → maintain access order (LRU)
        // loadFactor>> Resize happens when entries exceed = 75, Capacity = 100, Load factor = 0.75
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    // Automatically removes the LRU entry
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

// Test Class
public class LRUCacheByLinkedHashMap {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        System.out.println(cache);

        cache.get(1); // 1 becomes MRU
        System.out.println(cache);

        cache.put(4, 40); // removes LRU (key 2)
        System.out.println(cache);

        cache.get(3); // 3 becomes MRU
        System.out.println(cache);
    }
}