
// Clase para crear y representar los nodos del grafo
import java.util.ArrayList;
import java.util.List;

public class Vertex {
    // Atributos
    private String name; // Nombre del nodo
    private List<Edge> edges; // Lista de aristas que salen del nodo

    // Constructor
    public Vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    // Método para agregar una arista
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    // Método para obtener el nombre del nodo
    public String getName() {
        return name;
        
    }

    // Método para obtener la lista de aristas
    public List<Edge> getEdges() {
        return edges;
    }
}