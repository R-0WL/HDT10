//Clase para crear y representar un grafo bidireccional
import java.util.ArrayList;
import java.util.List;

public class Graph {
    // Atributos
    private List<Vertex> vertices; // Lista de nodos del grafo
    private List<Edge> edges; // Lista de aristas del grafo

    // Constructor
    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    // Método para agregar un nodo
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    // Método para agregar una arista
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    // Método para obtener la lista de nodos
    public List<Vertex> getVertices() {
        return vertices;
    }

    // Método para obtener la lista de aristas
    public List<Edge> getEdges() {
        return edges;
    }
    
}