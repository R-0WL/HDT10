
//Clase para leer el archivo de texto "logistica.txt" y crear el grafo.
// El grafo se crea por defecto en clima normal, pero se puede modificar posteriormente en el main.java
// El grafo se crea con los nodos y aristas del archivo de texto, donde cada línea representa una conexión entre dos ciudades y su peso (tiempo de conexión).

// handlerTXT.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class handlerTXT { // Clase para leer el archivo de texto "logistica.txt" o "default.txt" y crear el grafo
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Graph graph;
    private int climaIndex;

    public handlerTXT() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        graph = new Graph();
        climaIndex = 0;
    }

    public void setClimaIndex(int index) {
        this.climaIndex = index;
    }

    public void readTXT(String fileName) {
        vertices.clear();
        edges.clear();
        graph = new Graph();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                String source = tokens[0];
                String destination = tokens[1];
                int weight = Integer.parseInt(tokens[2 + climaIndex]);
                addVertex(source);
                addVertex(destination);
                addEdge(source, destination, weight);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addVertex(String name) {
        for (Vertex v : vertices) {
            if (v.getName().equals(name)) return;
        }
        Vertex vertex = new Vertex(name);
        vertices.add(vertex);
        graph.addVertex(vertex);
    }

    public void addEdge(String source, String destination, int weight) {
        Vertex sourceVertex = getVertex(source);
        Vertex destinationVertex = getVertex(destination);
        Edge edge = new Edge(sourceVertex, destinationVertex, weight);
        edges.add(edge);
        graph.addEdge(edge);
        sourceVertex.addEdge(edge);
        destinationVertex.addEdge(edge);
    }

    public Vertex getVertex(String name) {
        for (Vertex vertex : vertices) {
            if (vertex.getName().equals(name)) {
                return vertex;
            }
        }
        return null;
    }

    public Graph getGraph() {
        return graph;
    }
}
