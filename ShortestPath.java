import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * The Java program for Dijkstra's single source shortest path algorithm. The
 * program is for adjacency matrix representation of the graph.
 *
 * @author Vachagan Hovhannisyan
 * @version 1.0
 */

public class ShortestPath {
    static final int V = 9;

    /**
     * This method is used to calculate min distance.
     * 
     * @param dist   This is the first paramter to minDistance method
     * @param sptSet This is the second parameter to minDistance method
     * @return int This returns min index of vertex.
     */
    public int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    /**
     * This method is to print calculated distance array.
     * 
     * @param dist This is containing array of shortest paths from source to other
     *             vertexes.
     */
    public void printDistances(int dist[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    /**
     * This method is implements Dijkstra's algorithm.
     * 
     * Single source shortest path algorithm for a graph represented using adjacency
     * matrix representation.
     * 
     * @param graph This is the adjacency matrix representation.
     * @param src   This is the entry point (source).
     */
    public void calculateMinDistance(int graph[][], int src) {
        int dist[] = new int[V];
        Boolean sptSet[] = new Boolean[V];

        /**
         * Initialize all distances as INFINITE and stpSet[] as false
         */
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        /**
         * Distance of source vertex from itself is always 0
         */
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        printDistances(dist);
    }

    /**
     * This is the main method.
     * 
     * @param args Unused.
     * @return Nothing.
     * @exception IOException On input error.
     * @see IOException
     */
    public static void main(String[] args) {
        /**
         * Graph example represented using adjacency matrix.
         */
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        ShortestPath obj = new ShortestPath();
        obj.calculateMinDistance(graph, 2);
    }
}
