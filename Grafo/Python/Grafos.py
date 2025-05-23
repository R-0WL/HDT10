#Clase para crear y representar el grafo en python

#Importar librerias
import networkx as nx # NetworkX es una librería para crear y manipular grafos

A = True # Variable de control para el bucle principal
def cargar_grafo(nombre_archivo, clima_idx=0): # Función para cargar el grafo desde un archivo
    G = nx.DiGraph()
    with open(nombre_archivo) as f: # Abrir el archivo
        for linea in f: # Iterar sobre cada linea del archivo
            partes = linea.strip().split() # Dividir la linea en partes
            if len(partes) < 6: # Comprobar si la linea tiene 6 partes
                continue # Si no, saltear la linea
            origen, destino = partes[0], partes[1] # Extraer los nombres de las ciudades
            tiempos = list(map(float, partes[2:6])) # Extraer los tiempos de las conexiones
            G.add_edge(origen, destino, weight=tiempos[clima_idx]) # Agregar la conexión al grafo
    return G # Retornar el grafo


def floyd(G): # Función para calcular la distancia más corta entre todas las ciudades
    dist = dict(nx.floyd_warshall(G, weight='weight'))# Usar el algoritmo de Floyd-Warshall para encontrar la distancia más corta entre todos los pares de nodos
    centro = min(G.nodes, key=lambda n: max(dist[n].values()))# y el centro del grafo - el nodo con el menor valor de distancia para todos los nodos.
    return dist, centro # Retornar la distancia y el centro del grafo
#---------------------------------------------------------------
def reconstruir(dist, G, origen, destino): # Función para reconstruir la ruta más corta entre dos ciudades
    if origen not in G or destino not in G: # Comprobar si los nodos son válidos
        return [], float('inf') # Si no, retornar una ruta vacía y una distancia infinita   
    try:
        camino = nx.reconstruct_path(origen, destino, nx.floyd_warshall_predecessor_and_distance(G)[0])
        distancia = dist[origen][destino]
        return camino, distancia
    except:
        return [], float('inf')

def menu():
    print("\nOpciones:")
    print("1. Buscar ruta mas corta entre dos ciudades")
    print("2. Ver centro del grafo")
    print("3. Modificar el grafo (tráfico, conexiones o clima)")
    print("4. Salir")

def modificar_grafo(G):
    print("\nModificaciones disponibles:")
    print("a. Interrumpir tráfico entre dos ciudades (eliminar conexión)")
    print("b. Agregar nueva conexión entre dos ciudades")
    print("c. Cambiar clima para una conexión existente")

    opcion = input("Elige opción (a/b/c): ").strip().lower()

    if opcion == 'a':
        print("Formato para ciudades: CiudadOrigen CiudadDestino")
        a, b = input("Ingresa las ciudades: ").split()
        if G.has_edge(a, b):
            G.remove_edge(a, b)
            print(f"Conexion eliminada entre {a} -> {b}.")
        else:
            print("Esa conexión no existe.")

    elif opcion == 'b':
        print("Formato: CiudadOrigen CiudadDestino TiempoNormal TiempoLluvia TiempoNieve TiempoTormenta")
        datos = input("Ingresa datos: ").split()
        if len(datos) == 6:
            a, b = datos[0], datos[1]
            tiempo = float(datos[2])
            G.add_edge(a, b, weight=tiempo)
            print(f"Conexion creada entre {a} -> {b} con tiempo {tiempo} en clima normal.")
        else:
            print("Error: Debes ingresar 6 valores")

    elif opcion == 'c':
        print("Formato para introducir ciudades: CiudadOrigen CiudadDestino")
        a, b = input("Ingresa ciudades: ").split()
        print("Opciones de clima: normal / lluvia / nieve / tormenta")
        clima_str = input("Clima: ").lower()
        clima = {"normal": 0, "lluvia": 1, "nieve": 2, "tormenta": 3}
        idx = clima.get(clima_str, 0)
        actualizado = False
        with open('logistica.txt') as f:
            for linea in f:
                partes = linea.strip().split()
                if len(partes) >= 6 and partes[0] == a and partes[1] == b:
                    G[a][b]['weight'] = float(partes[2 + idx])
                    actualizado = True
                    print(f"Peso actualizado para el clima '{clima_str}'.")
                    break
        if not actualizado:
            print("No se encontro esa conexión en logistica.txt.")

    else:
        print("Opcion no válida.")

# -------------------- MAIN --------------------

if __name__ == "__main__":
    G = cargar_grafo('logistica.txt')  # clima normal por defecto
    dist, centro = floyd(G)

    while A:
        menu()
        op = input("Elige una de las opciones: ").strip()

        if op == "1":
            print("Formato para ciudades: CiudadOrigen CiudadDestino")
            a, b = input("Ingresa ciudades: ").split()
            camino, distancia = reconstruir(dist, G, a, b)
            if camino:
                print(f"Ruta más corta de {a} a {b}: {' -> '.join(camino)}")
                print(f"Distancia total: {distancia:.2f} horas")
            else:
                print("No hay ruta disponible entre esas ciudades.")

        elif op == "2":
            print(f"Centro del grafo: {centro}")

        elif op == "3":
            modificar_grafo(G)
            dist, centro = floyd(G)  # Recalcular rutas

        elif op == "4":
            print("Saliendo del programa.")
            A = False

        else:
            print("Opción no válida.")
