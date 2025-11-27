import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main{
    public static void main(String[] args) {
        Map<Integer, List<Graph.Edge>> graph = new HashMap<>();
        graph = Graph.generateGraph(10, 50); //n = amount of vertices, m = amount of edges
        Graph.printGraph(graph);
        
    }
}