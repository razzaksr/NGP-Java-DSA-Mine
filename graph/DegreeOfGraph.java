package graph;

import java.util.ArrayList;
import java.util.List;

public class DegreeOfGraph {
    private List<List<Integer>> adjListUndirected;
    private List<List<Integer>> adjListDirected;
    private int numVertices;

    public DegreeOfGraph(int numVertices) {
        adjListUndirected = new ArrayList<>();
        adjListDirected = new ArrayList<>();
        this.numVertices = numVertices;
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

    // Find degree of each vertex
    public void printDegrees(String type) {
        if(type.equalsIgnoreCase("directed")){
            int[] inDegree = new int[numVertices];
            int[] outDegree = new int[numVertices];
            for (int src = 0; src < numVertices; src++) {
                for (int dest : adjListDirected.get(src)) {
                    outDegree[src]++;   // outgoing edge
                    inDegree[dest]++;   // incoming edge
                }
            }
            for (int i = 0; i < numVertices; i++) {
                System.out.println("Vertex " + i + ": In-degree = " + inDegree[i] +
                                ", Out-degree = " + outDegree[i]);
            }
        }
        else{
            for (int i = 0; i < adjListUndirected.size(); i++) {
                int degree = adjListUndirected.get(i).size();
                System.out.println("Degree of vertex " + i + " = " + degree);
            }
        }
    }
    public static void main(String[] args) {
        DegreeOfGraph degree = new DegreeOfGraph(4);
        degree.addEdgeUndirected(0, 1);
        degree.addEdgeUndirected(0, 2);
        degree.addEdgeUndirected(1, 2);
        degree.addEdgeUndirected(2, 3);

        degree.printDegrees("undirected");

        degree.addEdgeDirected(0, 1);
        degree.addEdgeDirected(0, 2);
        degree.addEdgeDirected(1, 2);
        degree.addEdgeDirected(2, 3);

        degree.printDegrees("directed");
    }
}
