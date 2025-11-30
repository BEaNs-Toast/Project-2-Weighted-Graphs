import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main{
    public static void main(String[] args) {
        Map<Integer, List<Graph.Edge>> graph = new HashMap<>();
        graph = Graph.generateGraph(10, 50); //n = amount of vertices, m = amount of edges
        Graph.printGraph(graph);
        for (int i = 0; i < 10; i++){
            Graph.DijkstraPair result = Graph.Dijkstraway(graph, i);
            System.out.println("Starting from node " + i + ":\n_______________");
            for(int j = 0; j < 10; j++){
                if(j != i){
                    System.out.print("Node: " + j);
                    int distance = result.cost.get(j);
                    if (distance == Integer.MAX_VALUE){
                        System.out.println("\n Distance: INF\n");
                    }
                    else{
                        List<Integer> path = new java.util.ArrayList<>(); 
                        int node = j;
                        while (node != i) {
                            path.add(node);
                            node = result.prev.get(node);
                        }
                        path.add(node);
                        System.out.print("\n Path: ");
                        for(int k = path.size()-1; k >= 0; k--){
                            System.out.print(path.get(k));
                            if (k != 0){
                                System.out.print("->");
                            }
                        }
                        System.out.println("\n Distance from " + i + " = " + distance);
                    }
                }
            }
        }
        
    }
}