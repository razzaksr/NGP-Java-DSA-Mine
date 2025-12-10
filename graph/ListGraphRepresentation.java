package graph;

import java.util.ArrayList;
import java.util.List;

// Adjacency list representation of direct, undirect graphs

public class ListGraphRepresentation {
    private List<List<Integer>> adjListUndirected;
    private List<List<Integer>> adjListDirected;

    // Constructor
    public ListGraphRepresentation(int numVertices) {
        adjListUndirected = new ArrayList<>();
        adjListDirected = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjListUndirected.add(new ArrayList<>());
            adjListDirected.add(new ArrayList<>());
        }
    }

    // Add edge (both directions for undirected)
    public void addEdgeUndirected(int src, int dest) {
        adjListUndirected.get(src).add(dest);
        adjListUndirected.get(dest).add(src);
    }
    // Add edge (only one direction for directed)
    public void addEdgeDirected(int src, int dest) {
        adjListDirected.get(src).add(dest);
    }

    // Print adjacency list
    public void printGraph(String type) {
        if(type.equalsIgnoreCase("undirected")){
            for (int i = 0; i < adjListUndirected.size(); i++) {
                System.out.print(i + " -> ");
                for (int neighbor : adjListUndirected.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
        else{
            for (int i = 0; i < adjListDirected.size(); i++) {
                System.out.print(i + " -> ");
                for (int neighbor : adjListDirected.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
    }
    public static void main(String[] args) {
        ListGraphRepresentation g = new ListGraphRepresentation(4);

        g.addEdgeUndirected(0, 1);
        g.addEdgeUndirected(0, 2);
        g.addEdgeUndirected(1, 2);
        g.addEdgeUndirected(2, 3);

        System.out.println("Undirected Graph (Adjacency List):");
        g.printGraph("undirected");

        g.addEdgeDirected(0, 1);
        g.addEdgeDirected(0, 2);
        g.addEdgeDirected(1, 2);
        g.addEdgeDirected(2, 3);
        System.out.println("Directed Graph (Adjacency List):");
        g.printGraph("directed");
    }
}
