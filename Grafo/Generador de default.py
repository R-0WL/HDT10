import random

# Lista de 50 ciudades importantes de América
ciudades = [
    "BuenosAires", "SaoPaulo", "Lima", "Bogota", "Caracas", "Quito", "LaPaz", "Montevideo", "Asuncion", "Santiago",
    "MexicoCity", "Guadalajara", "Monterrey", "Tijuana", "Havana", "PanamaCity", "SanJose", "Managua", "Tegucigalpa", "SanSalvador",
    "Kingston", "PortauPrince", "SantoDomingo", "Belmopan", "GuatemalaCity", "Ottawa", "Toronto", "Vancouver", "Montreal", "QuebecCity",
    "NewYork", "LosAngeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "SanAntonio", "SanDiego", "Dallas", "SanJoseCA",
    "Denver", "Seattle", "Boston", "Atlanta", "Miami", "Detroit", "Minneapolis", "LasVegas", "Portland", "Charlotte"
]

# Generar 100 pares únicos de ciudades
pares_generados = set()
resultados = []

while len(resultados) < 100:
    c1, c2 = random.sample(ciudades, 2)
    if (c1, c2) not in pares_generados and (c2, c1) not in pares_generados:
        tiempoNormal = random.randint(5, 30)
        tiempoLluvia = tiempoNormal + random.randint(5, 15)
        tiempoNieve = tiempoNormal + random.randint(10, 25)
        tiempoTormenta = tiempoNormal + random.randint(20, 50)
        resultados.append(f"{c1} {c2} {tiempoNormal} {tiempoLluvia} {tiempoNieve} {tiempoTormenta}")
        pares_generados.add((c1, c2))

# Imprimir resultados
for linea in resultados:
    print(linea)
