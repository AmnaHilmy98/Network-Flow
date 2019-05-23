/**
 * Student ID  - 2017466
 * Name  - Amna Hilmy
 */

package NetworkFlowMax;

import java.util.ArrayList;

public class NetworkFlowTester {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Graph graph_adj;
        int[][] cap_graph;
        int source;
        int sink;
        int maxFlow;

        int v, e, count = 1, to = 0, from = 0;

        v = 6;
        e = v * v - (3 * v - 3);
        source = 1;
        sink = v;

        graph_adj = new Graph(v);

//        System.out.println("The edges are : [from] \t [to]");
        while (count <= e) {

            //repeat node generation if the src and dest nodes are the same
            do {
                from = ((int) (Math.random() * (v))) + 1;
                to = ((int) (Math.random() * (v))) + 1;
            } while (to == from || to == source || from == sink);

            //checks of the edge is already present in the graph generated
            if (graph_adj.getEdge(from, to) != 1) {

                //adds an edge and marks the presence of that edge with value 1
                graph_adj.setEdge(from, to, 1);
//                System.out.println("Edge " + count + " : [from] " + from + " [to] " + to);
                count++;
            }
        }

        System.out.println("The adjacency matrix of the generated weighted graph is: ");
        System.out.print("  ");

        System.out.print("from/to");
        for (int i = 1; i <= v; i++) {

            //printing the column heads
            System.out.print("\t" + i);
        }
        System.out.println();

        cap_graph = new int[v + 1][v + 1];


        ArrayList<String> arrayList = new ArrayList<>();

        int capacity = 0;
        for (int i = 1; i <= v; i++) {

            //printing the row heads
            System.out.print("\t" + i + "\t \t");

            for (int j = 1; j <= v; j++) {

                //check if edge is present
                if (graph_adj.getEdge(i, j) == 1) {

                    //generates a random capacity in the range of 5 to 20
                    capacity = ((int) (Math.random() * (20 - 5))) + 5;
                    System.out.print(capacity + "\t");
//                    System.out.println();
                    arrayList.add("[from] " + i + " [to] " + j + "[capacity] " + capacity);
                    //assigns the capacity to the edge in the matrix
                    cap_graph[i][j] = capacity;

                } else {

                    //edge not present hence capacity is zero
                    capacity = 0;
                    System.out.print(capacity + "\t");
                    cap_graph[i][j] = capacity;
                }
            }
            System.out.println();
        }

//        System.out.println(arrayList.toString());

        FordFulkerson fordFulkerson = new FordFulkerson(v);
        maxFlow = fordFulkerson.fordFulkerson(cap_graph, source, sink);

        System.out.println("The Maximum flow of the generated weighted graph is " + maxFlow);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }
}
