import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Graph {

        public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + to + ", w=" + weight + ")";
        }
    }

    public static Map<Integer, List<Edge>> generateGraph(int n, int m) {
        if (m < n - 1) {
            throw new IllegalArgumentException("Need at least n-1 edges for connected graph!");
        }

        Map<Integer, List<Edge>> graph = new HashMap<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        
        for (int i = 1; i < n; i++) {
            int parent = rand.nextInt(i); // connect to any previous node
            int weight = rand.nextInt(9) + 1; // weights 1–10
            graph.get(parent).add(new Edge(i, weight));
        }

        int edgesAdded = n - 1;

        while (edgesAdded < m) {
            int u = rand.nextInt(n);
            int v = rand.nextInt(n);

            if (u == v) continue; // should not connect to itself

            if (!hasEdge(graph, u, v)) {
                int weight = rand.nextInt(9) + 1;
                graph.get(u).add(new Edge(v, weight));
                edgesAdded++;
            }
        }

        return graph;
    }

    private static boolean hasEdge(Map<Integer, List<Edge>> graph, int u, int v) {
        for (Edge e : graph.get(u)) {
            if (e.to == v) return true;
        }
        return false;
    }

    public static void printGraph(Map<Integer, List<Edge>> graph) {
        for (int node : graph.keySet()) {
            System.out.print(node + " -> ");
            System.out.println(graph.get(node));
        }
    }
}

