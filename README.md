# RipAdbaisor - Ranking de Animes

## Descripción del ejercicio

Este proyecto consiste en una aplicación de escritorio desarrollada en Java utilizando la biblioteca **Swing**, cuyo objetivo es permitir la gestión de una lista de animes valorados por el usuario. La aplicación está diseñada para simular un sistema de ranking para la empresa ficticia **RipAdbaisor**, la cual requiere una interfaz gráfica amigable, sin necesidad de utilizar la consola.

La funcionalidad principal implementa un sistema **CRUD** (Crear, Leer, Actualizar y Eliminar), donde cada anime tiene los siguientes atributos:

- Nombre
- Código
- Descripción
- Puntuación (de 1 a 5)

La información se maneja en tiempo de ejecución a través de un `ArrayList`, sin persistencia de datos entre sesiones. El usuario puede interactuar con la aplicación mediante un menú gráfico que permite:

1. Añadir un nuevo anime
2. Editar los datos de un anime existente (buscado por su código)
3. Eliminar un anime de la lista
4. Mostrar todos los animes ordenados por puntuación descendente
5. Salir del programa de forma controlada

Se incluye gestión de errores y validaciones para garantizar que los datos ingresados sean correctos y evitar que la aplicación se cierre inesperadamente. Además, el código está modularizado, utilizando clases auxiliares con métodos estáticos para la validación y solicitud de datos.

Este ejercicio tiene como finalidad aplicar conocimientos de programación orientada a objetos, estructuras dinámicas, manejo de excepciones y creación de interfaces gráficas en Java.
