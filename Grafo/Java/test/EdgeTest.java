import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {

    @Test
    public void testAddAndGetWeight() {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Edge edge = new Edge(a, b, 10);
        edge.addWeight(15);
        edge.addWeight(20);

        assertEquals(10, edge.getWeight(0));
        assertEquals(15, edge.getWeight(1));
        assertEquals(20, edge.getWeight(2));
    }

    @Test
    public void testWeightOutOfBounds() {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Edge edge = new Edge(a, b, 10);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            edge.getWeight(3);
        });
    }
}
