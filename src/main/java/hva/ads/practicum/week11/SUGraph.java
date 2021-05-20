package hva.ads.practicum.week11;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class SUGraph<V extends Identifiable<ID>, ID extends Comparable<ID>> implements SimpleGraph<V, ID> {

    private final Map<ID, V> vertices;

    private final Map<ID, Set<ID>> neighbours;

    public SUGraph() {
        vertices = new TreeMap<ID, V>((id1, id2) -> id1.compareTo(id2));
        neighbours = new HashMap<>();
    }

    @Override
    public boolean addVertex(V... vertices) {
        boolean added = false;
        for (V vertex : vertices) added = added | addVertex(vertex);
        return added;
    }

    public boolean addVertex(V vertex) {
        if (vertices.containsKey(vertex.getId())) return false;
        vertices.put(vertex.getId(), vertex);
        neighbours.put(vertex.getId(), new HashSet<>());
        return true;
    }

    @Override
    public V getVertex(ID vertexId) {
        return vertices.get(vertexId);
    }

    @Override
    public boolean addEdge(V vertex, V... neighbours) {
        vertex = getVertex(vertex.getId());// Retrieve the vertex with the same identifier
        if (vertex == null) return false;

        // Stream.of(neighbours).map(Identifiable::getId).collect(Collectors.toList()).t;
        boolean added = false;
        Set<ID> vertexNeighbours = this.neighbours.get(vertex.getId());
        for (V neighbour : neighbours) {
            neighbour = getVertex(neighbour.getId()); // Retrieve the neighbour with the same identifier
            if (neighbour != null && vertex != neighbour) {
                Set<ID> neighbourNeighbours = this.neighbours.get(neighbour.getId());
                added = added | vertexNeighbours.add(neighbour.getId()) | neighbourNeighbours.add(vertex.getId()); // We use a single pipe | so all the code is executed.
            }
        }
        return added;
    }

    @Override
    public boolean addEdge(ID vertexId, ID... neighbourIds) {
        V vertex = getVertex(vertexId);
        if (vertex == null) return false;

        V[] neighbours = (V[]) Array.newInstance(vertex.getClass(), neighbourIds.length);
        int index = 0;
        for (ID neighbourId : neighbourIds) neighbours[index++] = getVertex(neighbourId);
        return addEdge(vertex, neighbours);
    }

    @Override
    public Collection<V> getVertices() {
        return vertices.values();
    }

    @Override
    public Collection<V> getNeighbours(V vertex) {
        return getNeighbours(vertex.getId());
    }

    @Override
    public Collection<V> getNeighbours(ID vertexId) {
        return neighbours.containsKey(vertexId) ?
                neighbours.get(vertexId).stream()
                        .map(this::getVertex)
                        .collect(Collectors.toList()) :
                Collections.emptyList();
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public int getNumEdges() {
        // Do not forget to divide by two
        return (int) neighbours.values().stream().mapToLong(Set::size).sum() / 2;
    }

    @Override
    public String getAdjacencyReport() {
        return vertices.keySet().stream().map(this::getAdjacencyReport).collect(Collectors.joining("\n"));
    }

    public String getAdjacencyReport(ID vertexId) {
        V vertex = getVertex(vertexId);
        return vertexId.toString() + ":" + getNeighbours(vertex.getId()).stream().map(n -> n.getId().toString()).collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public Queue<V> depthFirstSearch(V start, V target) {
        return depthFirstSearch(start.getId(), target.getId());
    }

    @Override
    public Queue<V> depthFirstSearch(ID startId, ID targetId) {
        return depthFirstSearch(startId, targetId, new HashSet<ID>());
    }

    private Queue<V> depthFirstSearch(ID startId, ID targetId, Set<ID> visited) {
        visited.add(startId);

        V vertex = getVertex(startId);
        if (startId.equals(targetId)) {
            ArrayDeque<V> path = new ArrayDeque<V>();
            path.add(vertex);
            return path;
        }

        for (ID neighbourId : neighbours.get(startId)) {
            if (!visited.contains(neighbourId)) {
                Queue<V> path = depthFirstSearch(neighbourId, targetId, visited);
                if (path != null) {
                    ((ArrayDeque) path).addFirst(vertex);
                    return path;
                }
            }
        }

        return null;
    }

    @Override
    public Queue<V> breadthFirstSearch(V start, V target) {
        return null;
    }

    @Override
    public Queue<V> breadthFirstSearch(ID startId, ID targetId) {
        return null;
    }

    @Override
    public String toString() {
        return format("SUGraph with %d vertices and %d neighbours connections", getNumVertices(), getNumEdges());
    }
}
