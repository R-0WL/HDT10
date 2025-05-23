import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void testFloydSimple() {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");

        Edge ab = new Edge(a, b, 2);
        Edge bc = new Edge(b, c, 3);
        ab.addWeight(5);
        bc.addWeight(4);

        List<Vertex> vertices = Arrays.asList(a, b, c);
        List<Edge> edges = Arrays.asList(ab, bc);

        int[][] dist = Matrix.floydWarshall(vertices, edges);

        assertEquals(2, dist[0][1]); // A->B
        assertEquals(5, dist[0][2]); // A->C (via B)
    }

    @Test
    public void testCentro() {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");

        Edge ab = new Edge(a, b, 2);
        Edge bc = new Edge(b, c, 3);
        ab.addWeight(0);
        bc.addWeight(0);

        List<Vertex> vertices = Arrays.asList(a, b, c);
        List<Edge> edges = Arrays.asList(ab, bc);

        String centro = Matrix.getCentro(vertices, edges);
        assertEquals("B", centro);
    }
}
