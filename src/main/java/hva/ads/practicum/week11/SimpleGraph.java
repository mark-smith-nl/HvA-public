package hva.ads.practicum.week11;

import java.util.Collection;
import java.util.Queue;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public interface SimpleGraph<V extends Identifiable<ID>, ID extends Comparable<ID>> {

    // vertices can be any class that can be identified by a value of any type ID
    // the Identifiable interface requires implementation of a getId() method
    // adds the vertices if not already present; returns whether at least one new vertex was added

    boolean addVertex(V... vertex);

    // retrieves the vertex specified by identifier id
     V getVertex(ID id);

     // adds the edges if not already present; returns whether at least one new edge was added
    boolean addEdge(V vertex, V... neighbours);

    boolean addEdge(ID vertexId, ID... neighbourIds);

    // returns all vertices in the graph
    Collection<V> getVertices();

    // returns all neighbours connected to the given vertex
    Collection<V> getNeighbours(V vertex);

    Collection<V> getNeighbours(ID vertexId);

    // returns the total number of vertices in the graph
    int getNumVertices();

    // returns the total number of edges in the graph
    int getNumEdges();

    // produces a formatted string representation of the complete graph
    String getAdjacencyReport();

    // calculates whether the graph is connected
    boolean isConnected();

    // calculates a path from start to target by depth-first-search;
    // returns null if no path can be found
    Queue<V> depthFirstSearch(V start, V target);

    Queue<V> depthFirstSearch(ID startId, ID targetId);

    // calculates a path from start to target by breadth-first-search;
    // returns null if no path can be found
    Queue<V> breadthFirstSearch(V start, V target);

    Queue<V> breadthFirstSearch(ID startId, ID targetId);
}
