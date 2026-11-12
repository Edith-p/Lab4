
# Laboratorio 4- Polimorfismo 

**Nombre completo:** Edith Estefany Pérez Trujillo
**Carné:** 26952
**Nombre completo:** Emily Lucila Menchú Menchú 
**Carné:** 20472
## Descripción
Este programa ayuda a simular un sistema de administración de una copa deportiva entre empresas. Permite registrar equipos, crear encuentros de fútbol sala y baloncesto y organizar exhibiciones, aplicando los conceptos de herencia, polimorfismo mediante interfaces y encapsulación.

La copa almacena los equipos y una lista polimórfica de eventos, que incluye encuentros y exhibiciones mediante la interfaz Programable. Los resultados de ambos deportes suman en una sola tabla de posiciones. Las exhibiciones aparecen en el calendario, pero no tienen equipos, marcador ni puntos de tabla, por lo que no afecta a los puntajes de los encuentros. 

La extensibilidad permite agregar otro deporte creando una subclase de Encuentro con sus reglas de validación y puntuación, e incorporando la opción para crearlo en el controlador. La tabla de posiciones y el calendario mantienen su funcionamiento.

## Funcionalidades
Registrar equipos con códigos únicos, crear encuentros y exhibiciones, programar y reprogramar eventos, registrar resultados, mostrar la tabla de posiciones y consultar el calendario. El menú permanece disponible hasta que el usuario decide salir.

En fútbol sala se permiten empates y se asignan 3 puntos por victoria, 1 por empate y 0 por derrota. En baloncesto no se permiten empates y se asignan 2 puntos por victoria y 1 por derrota. La tabla ordena los equipos por puntos de mayor a menor y, si empatan, por nombre en orden alfabético.

El calendario muestra los eventos con fecha asignada, ordenados por fecha, hora y sede. Se validan conflictos de sede y de equipos, horas fuera del rango de 0 a 23, marcadores negativos y resultados sobre encuentros sin programar o finalizados. Las entradas incorrectas se manejan sin terminar el programa. Para la interacción con el usuario se utiliza Scanner.

##Clases implementadas
Encuentro es la clase abstracta de la que heredan FutbolSala y Baloncesto. Equipo conserva los datos y puntos de cada participante; Exhibicion representa las clínicas o demostraciones y Copa administra los equipos y eventos.

Siguiendo el patrón MVC, también se utilizan VistaArena, ControladorArena y Principal. Las interfaces Programable y Puntuable separan la programación de eventos del registro de resultados. Comparable permite ordenar equipos y eventos, e Iterable permite recorrer el calendario. El enum EstadoEvento define SIN_PROGRAMAR, PROGRAMADO y FINALIZADO.


## Cómo ejecutar
```bash
javac -d bin src/*.java
java -cp bin Main
```

