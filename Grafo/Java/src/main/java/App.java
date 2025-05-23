//Clase principal para ejecutar el programa. Verifica si el archivo de texto "logistica.txt" existe dentro de resources y lo carga. 
//Si no existe, usa el archivo de texto "default.txt" para crear los grafos.

// Menú
// 1. Buscar ruta más corta entre dos ciudades 
//a) presentar ciudades posibles segun el archivo de texto. 
//b)Insertar nombre de ciudad 1 y nombre de ciudad 2
//c) Imprimir ruta más corta entre las dos ciudades, si existe. si no existe, imprimir mensaje de error "No hay ruta disponible entre esas ciudades".

// 2. Ver centro del grafo
//a) Imprimir el centro del grafo, que es la ciudad más cercana a todas las demás ciudades.
//b) Imprimir la distancia entre el centro del grafo y todas las demás ciudades.

// 3. Modificar el grafo (conexiones o clima)
//a) Insertar cambio 1 - conexiones 2- clima.
//b) Si 1. Conexiones, inidicar si hay tráfico (bloqueo entre ciudades), si no, agregar una nueva conexión entre la ciudad 1 y  ciudad 2 pidiendo la distancia por cada clima. Si 2. clima, indicar clima en la ruta: normal, lluvia, nieve o tormenta.
//C) Guardar el nuevo grafo e imprimir los cambios realizados.

// 4. Ver ciudades disponibles
// 5.Salir

// Librerías necesarias

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        handlerTXT handler = new handlerTXT();

        System.out.println("Ingrese el clima actual (0: normal, 1: lluvia, 2: nieve, 3: tormenta): ");
        int clima = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        String InstructionsFile;// Nombre del archivo de texto
        handler.setClimaIndex(clima);
        // Verificar si el archivo "logistica.txt" existe en la carpeta resources
        String dirLogistica = App.class.getClassLoader().getResource("default.txt").getPath();


        // Verificar si el archivo "logistica.txt" existe en la carpeta resources
        if (dirLogistica != null) {
            InstructionsFile = dirLogistica;
            System.out.println("Usando 'logistica.txt' para cargar el grafo.");
        } else {
            // Si no existe, usar el archivo "default.txt" en el paquete resources
             //obtener la ruta del archivo default;
            //InstructionsFile = "default.txt"; // -> java.io.FileNotFoundException: default.txt (El sistema no puede encontrar el archivo especificado)
            InstructionsFile = App.class.getClassLoader().getResource("default.txt").getPath();
           
            System.out.println("Archivo 'logistica.txt' no encontrado. Usando 'default.txt' en su lugar.");
            System.out.println("Por favor, asegúrese de que el archivo 'logistica.txt' esté en la carpeta resources.");
        }

        handler.readTXT(InstructionsFile);
        Graph graph = handler.getGraph();
        boolean state = true;
        while (state) {
            System.out.println("\n1. Buscar ruta más corta entre dos ciudades");
            System.out.println("\n2. Ver centro del grafo");
            System.out.println("\n3. Modificar el grafo (clima o conexiones)");
            System.out.println("\n4. Ver ciudades disponibles");
            System.out.println("\n5. Salir");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ciudad destino: ");
                    String destino = scanner.nextLine();
                    int distancia = graph.getShortestPath(origen, destino); // Obtener la distancia más corta entre las dos ciudades
                    if (distancia == -1) {
                        System.out.println("Una o ambas ciudades no existen en el grafo cargado.");
                    } else if(distancia >= Integer.MAX_VALUE / 2) {// Verificar si la distancia es válida; Integer.MAX_VALUE / 2 es un valor de referencia para evitar overflow y /2 es para convertir de metros a kilómetros
                        System.out.println("No hay ruta disponible entre esas ciudades.");
                    } else {
                        System.out.println("Ruta más corta: " + distancia + " horas");
                    }
                break;
                case 2:
                    String centro = graph.getCentro();
                    System.out.println("Centro del grafo: " + centro);
                    break;
                case 3:
                    System.out.println("a) Cambiar clima");
                    System.out.println("b) Modificar conexiones");
                    String suboption = scanner.nextLine();
                    if (suboption.equalsIgnoreCase("a")) {
                        System.out.print("Nuevo clima (0: normal, 1: lluvia, 2: nieve, 3: tormenta): ");
                        clima = scanner.nextInt();
                        scanner.nextLine();
                        handler.setClimaIndex(clima);
                        handler.readTXT(InstructionsFile);
                        graph = handler.getGraph();
                        System.out.println("Grafo actualizado con clima seleccionado.");
                    } else if (suboption.equalsIgnoreCase("b")) {
                        System.out.println("1. Eliminar conexión existente");
                        System.out.println("2. Agregar nueva conexión (si existe en el archivo original)");
                        int conOp = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ciudad 1: ");
                        String c1 = scanner.nextLine();
                        System.out.print("Ciudad 2: ");
                        String c2 = scanner.nextLine();
                        if (conOp == 1) {
                            graph.getEdges().removeIf(edge ->
                                (edge.getSource().getName().equals(c1) && edge.getDestination().getName().equals(c2)) ||
                                (edge.getSource().getName().equals(c2) && edge.getDestination().getName().equals(c1))
                            );
                            graph.printEdges();
                            System.out.println("Conexión eliminada si existía.");
                            
                        } else if (conOp == 2) {
                            System.out.print("Tiempo normal: ");
                            int tn = scanner.nextInt();
                            System.out.print("Tiempo lluvia: ");
                            int tl = scanner.nextInt();
                            System.out.print("Tiempo nieve: ");
                            int tni = scanner.nextInt();
                            System.out.print("Tiempo tormenta: ");
                            int tt = scanner.nextInt();
                            scanner.nextLine();
                            Vertex v1 = handler.getVertex(c1);
                            Vertex v2 = handler.getVertex(c2);
                            if (v1 != null && v2 != null) {
                                Edge newEdge = new Edge(v1, v2, tn);
                                newEdge.addWeight(tl);
                                newEdge.addWeight(tni);
                                newEdge.addWeight(tt);
                                graph.addEdge(newEdge);
                                v1.addEdge(newEdge);
                                v2.addEdge(newEdge);
                                graph.printEdges();
                                System.out.println("Conexión agregada exitosamente.");
                            } else {
                                System.out.println("Una o ambas ciudades no existen en el grafo cargado.");
                            }
                        }
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;
                case 4:
                    System.out.println("Ciudades disponibles:");
                    for (Vertex v : graph.getVertices()) {
                        System.out.println(v.getName());
                    }
                    break;
                case 5:
                    System.out.println("Programa finalizado.");
                    state = false;
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        }
    }
//     }
