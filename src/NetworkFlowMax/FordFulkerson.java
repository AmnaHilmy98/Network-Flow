/**
 * Student ID  - 2017466
 * Name  - Amna Hilmy
 */

package NetworkFlowMax;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
    private int[] parent;
    private Queue<Integer> queue;
    private int nodes;
    private boolean[] visited;

    public FordFulkerson(int nodes) {
        this.nodes = nodes;
        visited = new boolean[nodes + 1];
        //creating a queue to check path availability
        this.queue = new LinkedList<Integer>();
        parent = new int[nodes + 1];
    }

    //using breadth first search to find an augmenting path
    public boolean bfs(int src, int goalDest, int graph[][]) {
        boolean pathFound = false;
        int dest, checkNode;

        //setting all nodes to unvisited
        for (int node = 1; node <= nodes; node++) {
            parent[node] = -1;
            visited[node] = false;
        }

        queue.add(src);
        parent[src] = -1;
        visited[src] = true;

        while (!queue.isEmpty()) {
            checkNode = queue.remove();
            dest = 1;

            while (dest <= nodes) {
                if (graph[checkNode][dest] > 0 && !visited[dest]) {
                    parent[dest] = checkNode;
                    queue.add(dest);
                    visited[dest] = true;
                }
                dest++;
            }
        }
        if (visited[goalDest]) {
            pathFound = true;
        }
        return pathFound;
    }

    //finding the maximum flow using ford fulkerson algorithm if path is found
    // during breadth first search
    public int fordFulkerson(int graph[][], int source, int destination) {
        int v, w;
        int maxFlow = 0;
        int flow;

        int[][] residual_graph = new int[nodes + 1][nodes + 1];
        for (int src = 1; src <= nodes; src++) {
            for (int dest = 1; dest <= nodes; dest++) {
                residual_graph[src][dest] = graph[src][dest];
            }
        }

        while (bfs(source, destination, residual_graph)) {
            flow = Integer.MAX_VALUE;
            for (w = destination; w != source; w = parent[w]) {
                v = parent[w];

                //finding the bottleneck of the found path
                flow = Math.min(flow, residual_graph[v][w]);
            }
            for (w = destination; w != source; w = parent[w]) {
                v = parent[w];

                //adding the residual flows as required
                residual_graph[v][w] -= flow;
                residual_graph[w][v] += flow;
            }
            maxFlow += flow;
        }
        return maxFlow;
    }
}