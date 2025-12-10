package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * We pop elements from the priority queue and process them.

Iteration 1: Process City 0 (cost = 0, stops = 0)
ini
Copy
Edit
pq = []
current = (0, 0, 0)  // cost=0, city=0, stops=0
We check neighbors of 0:
0 → 1 (cost 100)
New cost = 100, stops = 1
Push (100, 1, 1) into pq
ini
Copy
Edit
pq = [(100, 1, 1)]
✅ minCost Map Updated: {1 → 100}

Iteration 2: Process City 1 (cost = 100, stops = 1)
ini
Copy
Edit
pq = []
current = (100, 1, 1)  // cost=100, city=1, stops=1
We check neighbors of 1:
1 → 2 (cost 100)
New cost = 200, stops = 2
Push (200, 2, 2) into pq
1 → 3 (cost 600)
New cost = 700, stops = 2
Push (700, 3, 2) into pq
ini
Copy
Edit
pq = [(200, 2, 2), (700, 3, 2)]
✅ minCost Map Updated: {1 → 100, 2 → 200, 3 → 700}

Iteration 3: Process City 2 (cost = 200, stops = 2)
ini
Copy
Edit
pq = [(700, 3, 2)]
current = (200, 2, 2)  // cost=200, city=2, stops=2
We check neighbors of 2, but we already exceeded k stops (we can use at most 1 stop).
Ignore this node and continue.
ini
Copy
Edit
pq = [(700, 3, 2)]
Iteration 4: Process City 3 (cost = 700, stops = 2)
ini
Copy
Edit
pq = []
current = (700, 3, 2)  // cost=700, city=3, stops=2
We reached destination (3).
Return cost = 700 ✅
Final Output
java
Copy
Edit
Output: 700

 */

public class CheapestFlight {
    public static int minCostSrcToDest(int[][] arr, int src, int dest, int between){
        // create table to represent graph
        Map<Integer, List<int[]>> graph = new Hashtable<>();
        for(int[] each:arr){
            graph.computeIfAbsent(each[0], key->new ArrayList<>()).add(new int[]{each[1],each[2]});
            // graph.put(each[0], graph.getOrDefault(each[0], Arrays.asList(each[1],each[2])).addAll(Arrays.asList(each[1],each[2])));
        }
        // PQ to sort by cost
        PriorityQueue<int[]> pQueue = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        pQueue.offer(new int[]{0,src,0});// represent {cost,city,stops}
        Map<Integer, Integer> minCost = new HashMap<>();
        while(!pQueue.isEmpty()){
            int[] polled = pQueue.poll();
            System.out.println(Arrays.toString(polled));
            int cost = polled[0], city = polled[1], stop = polled[2];
            if(city==dest) return cost;
            if(stop>between)continue;
            if(!graph.containsKey(city))continue;
            for(int[] next:graph.get(city)){
                int nextCity = next[0], price = next[1];
                int newCost = cost + price;

                // If a cheaper way to get to nextCity is found, push to queue
                if (!minCost.containsKey(nextCity) || minCost.get(nextCity) > newCost) {
                    minCost.put(nextCity, newCost);
                    pQueue.offer(new int[]{newCost, nextCity, stop + 1});
                    System.out.println("Pushing to queue"+ newCost+" "+nextCity+" "+(stop + 1));
                }
            }

        }
        System.out.println(graph);
        return -1;
    }
    public static void main(String[] args) {
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        System.out.println(minCostSrcToDest(flights, 0, 3, 1));
    }
}