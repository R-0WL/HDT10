import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GraphTest {

    @Test
    public void testAddVerticesAndEdges() {
        Graph graph = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");

        graph.addVertex(a);
        graph.addVertex(b);

        Edge e = new Edge(a, b, 5);
        e.addWeight(7); // lluvia
        graph.addEdge(e);
        a.addEdge(e);
        b.addEdge(e);

        assertEquals(2, graph.getVertices().size());
        assertEquals(1, graph.getEdges().size());
    }

    @Test
    public void testRutaInexistente() {
        Graph graph = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        graph.addVertex(a);
        graph.addVertex(b);

        // Sin conexión entre A y B
        int distancia = graph.getShortestPath("A", "B");
        assertTrue(distancia >= Integer.MAX_VALUE / 2);
    }

    @Test
    public void testNodoNoExiste() {
        Graph graph = new Graph();
        Vertex a = new Vertex("A");
        graph.addVertex(a);

        int distancia = graph.getShortestPath("A", "Z"); // Z no existe
        assertEquals(-1, distancia);
    }
}
