import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Graph {
    public static class DijkstraPair{
    List<Integer> cost;
    List<Integer> prev;

    public DijkstraPair(List<Integer> a,List<Integer> b){
        this.cost = a;
        this.prev = b;
    }
    }

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
    private static int getWeight(Map<Integer, List<Edge>> graph, int u, int v){
        for (Edge e : graph.get(u)) {
            if (e.to == v) return e.weight;
        }
        return Integer.MAX_VALUE;
    }

    public static void printGraph(Map<Integer, List<Edge>> graph) {
        for (int node : graph.keySet()) {
            System.out.print(node + " -> ");
            System.out.println(graph.get(node));
        }
    }
    public static DijkstraPair Dijkstraway(Map<Integer, List<Edge>> graph, int start){
        int n = graph.size();
        int INF = Integer.MAX_VALUE;
        List<Integer> prev = new ArrayList<>();
        List<Integer> cost = new ArrayList<>();
        List<Boolean> nodeVisited = new ArrayList<>();
        for (int node = 0; node < n; node++){
            if (node == start){
                nodeVisited.add(true);
                cost.add(0); 
            }
            else{
                nodeVisited.add(false);
                if(hasEdge(graph,start,node)){
                    cost.add(getWeight(graph,start,node));
                }
                else{
                    cost.add(INF);
                }
            }
            prev.add(start);
            
        }
        int min = INF;
        int next = -1;
        do { 
            for(int i = 0; i < n; i++){
                if (cost.get(i) < min && !nodeVisited.get(i)){
                    min = cost.get(i);
                    next = i;
                }
            }
            if (next == -1){
                break;
            }
            cost.set(next,min);
            nodeVisited.set(next,true);
            for (int i = 0; i < n; i++){
                int replace = getWeight(graph,next,i) + min;
                if(cost.get(i) > replace && hasEdge(graph,next,i) && !nodeVisited.get(i)){
                    cost.set(i,replace);
                    prev.set(i,next);
                }
            }
            min = INF;
            next = -1;

        } while (nodeVisited.contains(false));
        
        return new DijkstraPair(cost,prev);
    }
}

