//Clase para crear y representar un grafo bidireccional

// Librerías necesarias
import java.util.ArrayList;
import java.util.List;

public class Graph { 
    private List<Vertex> vertices; // Lista de nodos del grafo
    private List<Edge> edges; // Lista de aristas del grafo

    public Graph() { 
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getShortestPath(String source, String destination) { 
        return Matrix.getShortestPath(vertices, edges, source, destination);
    }

    public String getCentro() {
        return Matrix.getCentro(vertices, edges);
    }
    public void printEdges() {
    for (Edge e : edges) {
        System.out.println(e.getSource().getName() + " ↔ " + e.getDestination().getName()
            + " | Tiempo normal: " + e.getWeight(0));
    }
}

}

