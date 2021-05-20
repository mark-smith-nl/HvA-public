package hva.ads.practicum.week11;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to the Simple Undirected Graph demo program\n");

        // Build a graph with vertices that are uniquely identified by a String identifier
        SUGraph<Vertex<String>,String> graph = new SUGraph<>();

        graph.addVertex( new Vertex<>("A"),
                new Vertex<>("B", "Dit is vertex B"),
                new Vertex<>("C", "Dit is vertex C 1"),
                new Vertex<>("C", "Dit is vertex C 2"),
                new Vertex<>("C", "Dit is vertex C 3"),
                new Vertex<>("D"),
                new Vertex<>("E"));
        graph.addEdge("A", "B","C");
        graph.addEdge("B", "C","D","E");
        graph.addEdge("C", "D","E");
        graph.addEdge("D", "E");

        System.out.println(graph.getNumVertices());
        System.out.println(graph.getVertex("B"));
        System.out.println(graph.getVertex("C"));
        System.out.println(graph);

        // System.out.println(graph);
        //   Vertex<Character> vertex = new Vertex<>('j');
    }
}
