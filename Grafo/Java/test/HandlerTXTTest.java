import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

public class HandlerTXTTest {

    @Test
    public void testReadTXTFile() throws IOException {
        String tempFile = "temp_logistica.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("X Y 10 20 30 40\n");
        writer.close();

        handlerTXT handler = new handlerTXT();
        handler.setClimaIndex(0); // clima normal
        handler.readTXT(tempFile);

        Graph g = handler.getGraph();

        assertEquals(2, g.getVertices().size());
        assertEquals(1, g.getEdges().size());

        new File(tempFile).delete(); // limpiar
    }
}
