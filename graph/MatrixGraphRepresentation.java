package graph;

// adjacency matrix representation of weighted, unweighted graph

public class MatrixGraphRepresentation {
    private int[][] adjMatrixWeighted;  // adjacency matrix for weighted
    private int[][] adjMatrixUnweighted;  // adjacency matrix for unweighted
    
    private int numVertices;    // number of vertices

    // Constructor
    public MatrixGraphRepresentation(int numVertices) {
        this.numVertices = numVertices;
        adjMatrixWeighted = new int[numVertices][numVertices];
        adjMatrixUnweighted = new int[numVertices][numVertices];
    }

    // Add edge (for unweighted graph, use 1)
    public void addEdgeUnweighted(int i, int j) {
        adjMatrixUnweighted[i][j] = 1;
        adjMatrixUnweighted[j][i] = 1; // for undirected graph
    }

    // Remove edge
    public void removeEdge(int i, int j) {
        adjMatrixUnweighted[i][j] = 0;
        adjMatrixUnweighted[j][i] = 0;
    }

    // Add edge (for weighted graph, use respective weight)
    public void addEdgeWeighted(int i, int j, int w) {
        adjMatrixWeighted[i][j] = w;
        adjMatrixWeighted[j][i] = w; // for undirected graph
    }

    // Print the adjacency matrix
    public void printGraph(String type) {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if(type.equalsIgnoreCase("weighted")){
                    System.out.print(adjMatrixWeighted[i][j] + " ");
                }else{
                    System.out.print(adjMatrixUnweighted[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        MatrixGraphRepresentation g = new MatrixGraphRepresentation(4);

        g.addEdgeUnweighted(0, 1);
        g.addEdgeUnweighted(0, 2);
        g.addEdgeUnweighted(1, 2);
        g.addEdgeUnweighted(2, 3);

        System.out.println("Adjacency Matrix of Unweighted ");
        g.printGraph("unweighted");

        g.addEdgeWeighted(0, 1, 5);  // edge 0–1 with weight 5
        g.addEdgeWeighted(0, 2, 3);  // edge 0–2 with weight 3
        g.addEdgeWeighted(1, 2, 2);  // edge 1–2 with weight 2
        g.addEdgeWeighted(2, 3, 7);  // edge 2–3 with weight 7

        System.out.println("Adjacency Matrix of Weighted ");
        g.printGraph("weighted");
    }
}
/* 
Absolutely 👍 — let’s decode the **0’s and 1’s in the adjacency matrix output** step by step.

---

## 🧩 How to Read the Matrix
- Each **row** represents a vertex (starting from 0).
- Each **column** represents a vertex.
- The value at `matrix[i][j]` tells you whether there is an edge between vertex `i` and vertex `j`.

### Rules:
- `0` → No edge between those two vertices.
- `1` → There is an edge between those two vertices.

Since the example graph is **undirected**, the matrix is **symmetric** (meaning `matrix[i][j] = matrix[j][i]`).

---

## 📊 Example Graph
We added edges:
- (0–1), (0–2), (1–2), (2–3)

So the adjacency matrix printed was:

```
0 1 1 0
1 0 1 0
1 1 0 1
0 0 1 0
```

---

## 🔍 Explanation Row by Row

- **Row 0 → Vertex 0**
  - `0 1 1 0`
  - Vertex 0 has edges to vertex 1 and vertex 2 (positions 1 and 2 are `1`).
  - No edge to vertex 0 itself (diagonal is `0`) and no edge to vertex 3.

- **Row 1 → Vertex 1**
  - `1 0 1 0`
  - Vertex 1 connects to vertex 0 and vertex 2.
  - No edge to itself or vertex 3.

- **Row 2 → Vertex 2**
  - `1 1 0 1`
  - Vertex 2 connects to vertex 0, vertex 1, and vertex 3.
  - No edge to itself.

- **Row 3 → Vertex 3**
  - `0 0 1 0`
  - Vertex 3 connects only to vertex 2.

---

## 🖼️ Visualizing the Graph
If you draw it:
- 0 ↔ 1  
- 0 ↔ 2  
- 1 ↔ 2  
- 2 ↔ 3  

That’s exactly what the matrix encodes with the 1’s.

---

👉 So the **0’s mean “no connection”** and the **1’s mean “connection exists”**.  
Would you like me to also show you how this changes if we make it a **weighted graph** (where instead of 1’s, we store the actual edge weights)?

*/