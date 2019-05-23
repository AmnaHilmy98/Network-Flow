/**
 * Student ID  - 2017466
 * Name  - Amna Hilmy
 */

package NetworkFlowMax;

//defines edges of the graph
public class Graph {
    private final int vertices;
    private int[][] adjacency_matrix;

    //create a graph with source and destination nodes
    public Graph(int v) {
        vertices = v;
        adjacency_matrix = new int[vertices + 1][vertices + 1];
    }

    //create an edge between from and to nodes with a marker
    public void setEdge(int from, int to, int marker) {
        adjacency_matrix[from][to] = marker;
    }

    //return the marker of edge with from and to nodes
    public int getEdge(int from, int to) {
        return adjacency_matrix[from][to];
    }
}