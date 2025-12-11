package graph;

import java.util.*;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }
}

public class CloneGraph {
    private Map<Node, Node> visited = new HashMap<>();

    // Clone graph using DFS
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (visited.containsKey(node)) return visited.get(node);

        Node copy = new Node(node.val);
        visited.put(node, copy);

        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(cloneGraph(neighbor));
        }
        return copy;
    }

    // Build graph from adjacency list input
    public static Node buildGraph(List<List<Integer>> adj) {
        if (adj.isEmpty()) return null;
        int n = adj.size();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1); // nodes labeled 1..n
        }
        for (int i = 0; i < n; i++) {
            for (int neighbor : adj.get(i)) {
                nodes[i].neighbors.add(nodes[neighbor - 1]);
            }
        }
        return nodes[0]; // return node 1 as entry point
    }

    // Convert graph back to adjacency list format
    public static List<List<Integer>> graphToAdj(Node node) {
        if (node == null) return new ArrayList<>();
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        map.put(node.val, node);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            for (Node neigh : curr.neighbors) {
                if (!map.containsKey(neigh.val)) {
                    map.put(neigh.val, neigh);
                    q.offer(neigh);
                }
            }
        }

        int n = map.size();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            for (Node neigh : map.get(i).neighbors) {
                list.add(neigh.val);
            }
            adj.add(list);
        }
        return adj;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read adjacency list input
        String line = sc.nextLine().trim();
        if (line.equals("[]")) {
            System.out.println("[]");
            return;
        }

        // Parse input like [[2,4],[1,3],[2,4],[1,3]]
        line = line.substring(1, line.length() - 1); // remove outer brackets
        List<List<Integer>> adj = new ArrayList<>();
        if (!line.isEmpty()) {
            String[] parts = line.split("\\],\\[");
            for (String part : parts) {
                part = part.replace("[", "").replace("]", "");
                List<Integer> list = new ArrayList<>();
                if (!part.isEmpty()) {
                    for (String num : part.split(",")) {
                        list.add(Integer.parseInt(num.trim()));
                    }
                }
                adj.add(list);
            }
        }

        Node original = buildGraph(adj);
        CloneGraph cg = new CloneGraph();
        Node cloned = cg.cloneGraph(original);

        List<List<Integer>> result = graphToAdj(cloned);
        System.out.println(result);
        sc.close();
    }
}