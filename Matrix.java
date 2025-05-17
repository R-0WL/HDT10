//Clase para crear la matriz de adyacencia del grafo y realizar el algoritmo de Floyd para calcular la ruta más corta y el centro del grafo.
public class Matrix {
    private int[][] matrix; // Matriz de adyacencia
    private int numVertices; // Número de nodos del grafo
    private int numEdges; // Número de aristas del grafo

    // Constructor
    public Matrix(int numVertices, int numEdges) {
        this.numVertices = numVertices;
        this.numEdges = numEdges;
        matrix = new int[numVertices][numVertices];
    }
    // Método para agregar una arista a la matriz
    public void addEdge(int source, int destination, int weight) {
        matrix[source][destination] = weight;
        matrix[destination][source] = weight; // Grafo no dirigido
    }

    // Método para obtener la matriz de adyacencia
    public int[][] getMatrix() {
        return matrix;
    }

    // Método para obtener el número de nodos del grafo
    public int getNumVertices() {
        return numVertices;
    }

    // Método para obtener el número de aristas del grafo
    public int getNumEdges() {
        return numEdges;
    }
}   
