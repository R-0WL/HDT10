//Clase para crear y representar las conexiones del grafo
import java.util.ArrayList;
import java.util.List;

public class Edge {
    // Atributos
    private int source; // Origen de la conexión
    private int destination; // Destino de la conexión
    private List<Integer> weights; // Lista de pesos de las conexiones

    // Constructor
    public Edge(int source, int destination) {
        this.source = source;
        this.destination = destination;
        this.weights = new ArrayList<>();
    }

    // Método para agregar un peso a la conexión
    public void addWeight(int weight) {
        weights.add(weight);
    }

    // Método para obtener el origen de la conexión
    public int getSource() {
        return source;
    }

    // Método para obtener el destino de la conexión
    public int getDestination() {
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
