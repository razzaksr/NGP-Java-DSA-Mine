package graph;

import java.util.*;

public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1: Build graph with lexical order
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).add(to);
        }
        System.out.println(graph);
        // Step 2: Result list
        LinkedList<String> itinerary = new LinkedList<>();

        // Step 3: DFS traversal
        dfs("JFK", graph, itinerary);

        return itinerary;
    }

    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> nextAirports = graph.get(airport);
        while (nextAirports != null && !nextAirports.isEmpty()) {
            String next = nextAirports.poll(); // smallest lexical choice
            dfs(next, graph, itinerary);
        }
        itinerary.addFirst(airport); // post-order insertion
        System.out.println("Every DFS Iter "+itinerary);
    }

    // Driver
    public static void main(String[] args) {
        ReconstructItinerary solver = new ReconstructItinerary();

        // List<List<String>> tickets1 = Arrays.asList(
        //     Arrays.asList("SALEM","COVAI"),
        //     Arrays.asList("TRICHY","SALEM"),
        //     Arrays.asList("MADURAI","BLR"),
        //     Arrays.asList("COVAI","MADURAI")
        // );
        // System.out.println(solver.findItinerary(tickets1)); 
        // Output: [JFK, MUC, LHR, SFO, SJC]

        List<List<String>> tickets2 = Arrays.asList(
            Arrays.asList("JFK","SFO"),
            Arrays.asList("JFK","ATL"),
            Arrays.asList("SFO","ATL"),
            Arrays.asList("ATL","JFK"),
            Arrays.asList("ATL","SFO")
        );
        System.out.println(solver.findItinerary(tickets2)); 
        // Output: [JFK, ATL, JFK, SFO, ATL, SFO]
    }
}
/* 
- Input Example
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
- Graph Construction

Build adjacency list with PriorityQueue for lexical order:
JFK → [ATL, SFO]
SFO → [ATL]
ATL → [JFK, SFO]

Traversal Logic
- Start at "JFK".
- Always pick the smallest lexical destination from the queue.
- Use DFS (post-order) to ensure all tickets are consumed.
- Append airports to result after visiting all edges (reverse order at the end).
Trace Example
- Start: JFK
- JFK → ATL (smallest)
- ATL → JFK
- JFK → SFO
- SFO → ATL
- ATL → SFO
- End traversal, reverse result.
Final itinerary:
["JFK","ATL","JFK","SFO","ATL","SFO"]

*/