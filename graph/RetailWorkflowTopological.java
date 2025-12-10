package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RetailWorkflowTopological {
    private Map<Integer, List<Integer>> adjList;
    private int V; // number of stages

    RetailWorkflowTopological(int v) {
        V = v;
        adjList = new HashMap<>();
    }

    void addDependency(int from, int to) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.get(from).add(to);
    }
        boolean isCyclicUtil(int v, boolean[] visited, boolean[] recStack) {
        if (recStack[v]) return true;
        if (visited[v]) return false;

        visited[v] = true;
        recStack[v] = true;

        for (int neighbor : adjList.getOrDefault(v, Collections.emptyList())) {
            if (isCyclicUtil(neighbor, visited, recStack))
                return true;
        }

        recStack[v] = false;
        return false;
    }

    boolean isCyclic() {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (isCyclicUtil(i, visited, recStack))
                return true;
        }
        return false;
    }
    void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int neighbor : adjList.getOrDefault(v, Collections.emptyList())) {
            if (!visited[neighbor])
                topologicalSortUtil(neighbor, visited, stack);
        }
        stack.push(v);
    }

    void topologicalSort() {
        if (isCyclic()) {
            System.out.println("Workflow invalid – cycle detected");
            return;
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        System.out.print("Valid workflow order: ");
        while (!stack.isEmpty())
            System.out.print(stageName(stack.pop()) + " ");
    }

    String stageName(int stage) {
        switch(stage) {
            case 0: return "Payment";
            case 1: return "Packaging";
            case 2: return "Shipping";
            case 3: return "Delivery";
            default: return "Unknown";
        }
    }
    public static void main(String[] args) {
        RetailWorkflowTopological workflow = new RetailWorkflowTopological(4);

        workflow.addDependency(0, 1); // Payment → Packaging
        workflow.addDependency(1, 2); // Packaging → Shipping
        workflow.addDependency(2, 3); // Shipping → Delivery
        // Uncomment below to create a cycle
        // workflow.addDependency(3, 1); // Delivery → Packaging

        workflow.topologicalSort();

    }
}
