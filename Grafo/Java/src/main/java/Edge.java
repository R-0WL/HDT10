//Clase para crear y representar las conexiones del grafo

import java.util.ArrayList;
import java.util.List;

public class Edge {
    // Atributos
    private Vertex source; // Origen de la conexión
    private Vertex destination; // Destino de la conexión
    private List<Integer> weights; // Lista de pesos de las conexiones

    // Constructor
    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source; 
        this.destination = destination;
        this.weights = new ArrayList<>();
        this.weights.add(weight); // Añadir peso inicial (clima actual)
    }

    // Método para agregar un peso a la conexión
    public void addWeight(int weight) {
        weights.add(weight);
    }

    // Método para obtener el origen de la conexión
    public Vertex getSource() {
        return source;
    }

    // Método para obtener el destino de la conexión
    public Vertex getDestination() {
        return destination;
    }

    // Método para obtener el peso de la conexión
    public int getWeight(int index) {
        return weights.get(index);
    }

    // Método para obtener el número de pesos de la conexión
    public int getNumWeights() {
        return weights.size();
    }
}
