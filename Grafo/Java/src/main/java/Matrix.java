//Clase para crear la matriz de adyacencia del grafo y realizar el algoritmo de Floyd para calcular la ruta más corta y el centro del grafo.

// Librerías necesarias
import java.util.List;

public class Matrix { 
    // Método para calcular la matriz de distancias entre todos los nodos usando el algoritmo de Floyd-Warshall
    public static int[][] floydWarshall(List<Vertex> vertices, List<Edge> edges) { 
        int size = vertices.size(); // número de nodos del grafo
        int[][] dist = new int[size][size]; // matriz de distancias

        // Inicialización
        for (int i = 0; i < size; i++) { // matriz de distancias
            for (int j = 0; j < size; j++) { 
                dist[i][j] = (i == j) ? 0 : Integer.MAX_VALUE / 2; // evitar overflow
            }
        }

        // Construir matriz con conexiones actuales (clima activo)
        for (Edge edge : edges) { // recorrer las aristas del grafo
            int i = vertices.indexOf(edge.getSource()); // origen
            int j = vertices.indexOf(edge.getDestination()); // destino
            int w = edge.getWeight(0); // peso para clima actual
            dist[i][j] = w; 
            dist[j][i] = w;
        }

        // Algoritmo Floyd-Warshall
        for (int k = 0; k < size; k++) { 
            for (int i = 0; i < size; i++) { 
                for (int j = 0; j < size; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) { // si la distancia entre i y j es mayor que la distancia entre i y k más la distancia entre k y j
                        dist[i][j] = dist[i][k] + dist[k][j]; // actualizar la distancia entre i y j
                    }
                }
            }
        }

        return dist;
    }

    public static int getShortestPath(List<Vertex> vertices, List<Edge> edges, String source, String destination) { // Método para obtener la distancia más corta entre dos nodos
        int[][] dist = floydWarshall(vertices, edges); // Obtener la matriz de distancias
        int i = getIndex(vertices, source); // Obtener el índice del nodo origen
        int j = getIndex(vertices, destination); // Obtener el índice del nodo destino
        return (i != -1 && j != -1) ? dist[i][j] : -1; // Devolver la distancia entre i y j o -1 si no existe
    }

    public static String getCentro(List<Vertex> vertices, List<Edge> edges) { // Método para obtener el centro del grafo
        int[][] dist = floydWarshall(vertices, edges); // Obtener la matriz de distancias
        int minSum = Integer.MAX_VALUE; // Inicializar la distancia mínima con el valor máximo
        String centro = null; // Inicializar el nodo mínimo con una cadena vacía

        for (int i = 0; i < vertices.size(); i++) { // Recorrer la matriz de distancias
            int max = 0; // Inicializar la distancia máxima con 0
            for (int j = 0; j < vertices.size(); j++) { // Recorrer la matriz de distancias
                max = Math.max(max, dist[i][j]); // Actualizar la distancia máxima si es mayor
            }
            if (max < minSum) { // Si la distancia máxima es menor que la distancia mínima
                minSum = max; // Actualizar la distancia mínima
                centro = vertices.get(i).getName(); // Actualizar el nodo mínimo
            }
        }
        return centro; // Retornar el centro del grafo
    }

    private static int getIndex(List<Vertex> vertices, String name) { // Método para obtener el índice del nodo con el nombre dado
        for (int i = 0; i < vertices.size(); i++) { // Recorrer la lista de nodos
            if (vertices.get(i).getName().equals(name)) return i; // Si el nombre coincide, devolver el índice        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getName().equals(name)) return i;
        }
        return -1; // ¿Cuándo devuelve -1? Si no se encuentra un nodo con ese nombre
    }
}
