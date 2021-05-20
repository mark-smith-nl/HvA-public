package hva.ads.practicum.week11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SUGraphTest {

    SUGraph<Vertex<String>, String> graph;

    @BeforeEach
    public void setupp() {
        graph = new SUGraph<>();
    }

    @Test
    public void addVertex() {
        assertTrue(graph.addVertex(new Vertex<>("A", "Character A")));
        assertTrue(graph.addVertex(new Vertex<>("A", "Character A"), new Vertex<>("B", "Character B")));
        assertFalse(graph.addVertex(new Vertex<>("A", "Character A"), new Vertex<>("B", "Character B")));
    }

    @Test
    public void getNumVertices() {
        graph.addVertex(new Vertex<>("A", "Character A"));
        graph.addVertex(new Vertex<>("A", "Character A"), new Vertex<>("B", "Character B"));
        assertEquals(2, graph.getNumVertices());
    }

    @Test
    public void getVertex() {
        Vertex<String> stringVertex = new Vertex<>("A", "Character A");
        graph.addVertex(stringVertex);
        assertEquals(stringVertex, graph.getVertex("A"));
    }

    @Test
    public void getVertex_nonExistingIdentifier() {
        Vertex<String> stringVertex = new Vertex<>("A", "Character A");
        graph.addVertex(stringVertex);
        assertNull(graph.getVertex("B"));
    }

    @Test
    public void addEdge() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"));

        assertTrue(graph.addEdge(new Vertex("A"), new Vertex("B"), new Vertex("C")));
        assertTrue(graph.addEdge(new Vertex("B"), new Vertex("A"), new Vertex("C"), new Vertex("D"), new Vertex("E")));
        assertTrue(graph.addEdge(new Vertex("C"), new Vertex("A"), new Vertex("B"), new Vertex("D"), new Vertex("E")));
        assertTrue(graph.addEdge(new Vertex("D"), new Vertex("B"), new Vertex("C"), new Vertex("E")));
    }

    @Test
    public void addEdge_usingIdentifiers() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"));

        assertTrue(graph.addEdge("A", "B", "C"));
        assertTrue(graph.addEdge("B", "A", "C", "D", "E"));
        assertTrue(graph.addEdge("C", "A", "B", "D", "E"));
        assertTrue(graph.addEdge("D", "B", "C", "E"));
    }

    @Test
    public void addEdge_usingIdentifiers_nonExistingIdentifier() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"));

        assertTrue(graph.addEdge("A", "B", "C"));
        assertTrue(graph.addEdge("B", "A", "C", "D", "E"));
        assertTrue(graph.addEdge("C", "A", "B", "D", "E"));
        assertTrue(graph.addEdge("D", "B", "C", "E"));
        assertFalse(graph.addEdge("Z", "B", "C", "E"));
    }

    @Test
    public void addEdge_usingIdentifiers_loop() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"));

        assertTrue(graph.addEdge("A", "B", "C"));
        assertTrue(graph.addEdge("B", "A", "C", "D", "E"));
        assertTrue(graph.addEdge("C", "A", "B", "D", "E"));
        assertTrue(graph.addEdge("D", "B", "C", "E"));
        assertFalse(graph.addEdge("A", "A"));
    }

    @Test
    public void getNumEdges() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"));

        graph.addEdge("A", "B", "C");
        graph.addEdge("A", "B", "C"); // Chech duplicate insertion of edges.
        graph.addEdge("B", "A", "C", "D", "E");
        graph.addEdge("C", "A", "B", "D", "E");
        graph.addEdge("D", "B", "C", "E");
        assertEquals(8, graph.getNumEdges());
    }

    @Test
    public void asString() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"));

        graph.addEdge("A", "B", "C");
        graph.addEdge("B", "A", "C", "D", "E");
        graph.addEdge("C", "A", "B", "D", "E");
        graph.addEdge("D", "B", "C", "E");

        assertEquals("SUGraph with 5 vertices and 8 neighbours connections", graph.toString());
    }

    @Test
    public void getNeighbours() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"));

        graph.addEdge("A", "B", "C");
        graph.addEdge("A", "B", "C"); // Chech duplicate insertion of edges.
        graph.addEdge("B", "A", "C", "D", "E");
        graph.addEdge("C", "A", "B", "D", "E");
        graph.addEdge("D", "B", "C", "E");

        Collection<Vertex<String>> neighBours = graph.getNeighbours("A");
        assertEquals(2, neighBours.size());
        assertTrue(neighBours.contains(graph.getVertex("B")));
        assertTrue(neighBours.contains(graph.getVertex("C")));

        neighBours = graph.getNeighbours("B");
        assertEquals(4, neighBours.size());
        assertTrue(neighBours.contains(graph.getVertex("A")));
        assertTrue(neighBours.contains(graph.getVertex("C")));
        assertTrue(neighBours.contains(graph.getVertex("D")));
        assertTrue(neighBours.contains(graph.getVertex("E")));

        neighBours = graph.getNeighbours("C");
        assertEquals(4, neighBours.size());
        assertTrue(neighBours.contains(graph.getVertex("A")));
        assertTrue(neighBours.contains(graph.getVertex("B")));
        assertTrue(neighBours.contains(graph.getVertex("D")));
        assertTrue(neighBours.contains(graph.getVertex("E")));

        neighBours = graph.getNeighbours("D");
        assertEquals(3, neighBours.size());
        assertTrue(neighBours.contains(graph.getVertex("B")));
        assertTrue(neighBours.contains(graph.getVertex("C")));
        assertTrue(neighBours.contains(graph.getVertex("E")));

        neighBours = graph.getNeighbours("E");
        assertEquals(3, neighBours.size());
        assertTrue(neighBours.contains(graph.getVertex("B")));
        assertTrue(neighBours.contains(graph.getVertex("C")));
        assertTrue(neighBours.contains(graph.getVertex("D")));
    }

    @Test
    public void getAdjacencyReport() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"));

        graph.addEdge("A", "B", "C");
        graph.addEdge("B", "A", "C", "D", "E");
        graph.addEdge("C", "A", "B", "D", "E");
        graph.addEdge("D", "B", "C", "E");

        assertEquals("A:[B, C]\n" +
                        "B:[A, C, D, E]\n" +
                        "C:[A, B, D, E]\n" +
                        "D:[B, C, E]\n" +
                        "E:[B, C, D]",
                graph.getAdjacencyReport());
    }

    @Test
    public void depthFirstSearch() {
        graph.addVertex(new Vertex<>("A", "Character A"),
                new Vertex<>("B", "Character B"),
                new Vertex<>("C", "Character C"),
                new Vertex<>("D", "Character D"),
                new Vertex<>("E", "Character E"),
                new Vertex<>("Z", "Character Z"));

        graph.addEdge("A", "B", "C");
        graph.addEdge("B", "A", "C", "D", "E");
        graph.addEdge("C", "A", "B", "D", "E");
        graph.addEdge("D", "B", "C", "E");
        graph.addEdge("Z", "E");


        Queue<Vertex<String>> vertices = graph.depthFirstSearch("A", "Z");
        System.out.println();
    }
}

