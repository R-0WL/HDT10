#Clase para pruebas unitarias del programa

#Importar librerias
import networkx as nx
from unittest import TestCase # Esta librería proporciona una forma de crear y ejecutar pruebas unitarias en Python
from Grafos import floyd, reconstruir # Importar las funciones de la clase Grafos

class TestGrafo(TestCase): # Crear una clase de prueba unitaria para el programa
    def setup(self):
        # hace iun grafo simple para el test
        self.G = nx.DiGraph()
        self.G.add_edge("A", "B", weight=1)
        self.G.add_edge("B", "C", weight=2)
        self.G.add_edge("A", "C", weight=10)

    def testfloyd(self):
        dist, centro = floyd(self.G)
        self.assertAlmostEqual(dist["A"]["C"], 3)  # A -> B -> C
        self.assertIn(centro, self.G.nodes)

    def testcamino(self):
        dist, _ = floyd(self.G)
        camino, distancia = reconstruir(dist, self.G, "A", "C")
        self.assertEqual(camino, ["A", "B", "C"])
        self.assertEqual(distancia, 3)

    def testquitarcamino(self):
        G2 = nx.DiGraph()
        G2.add_edge("X", "Y", weight=1)
        dist, _ = floyd(G2)
        camino, distancia = reconstruir(dist, G2, "Y", "X")
        self.assertEqual(camino, [])
        self.assertEqual(distancia, float('inf'))
