En este proyecto hemos creado un sistema de reserva de habitaciones de un hotel. En ese hotel esta dividido en tres plantas y lo hemos planteado de la siguiente manera: 
-Primera planta: habitaciones individuales, habitaciones del 101 al 105, precio a 50€.
-Segunda planta: habitaciones dobles, habitaciones del 201 al 205, precio a 80€.
-Tercera planta: habitaciones suite, habitaciones del 301 al 305, precio a 150€.

A la hora de crear una reserva, dependiendo de la cantidad de días que se hospede el usuario, le dará el precio total por noche.

Hemos creado un menu con 6 opciones diferentes:
1.Crear reserva - Nos pedira que ingresemos el numero de reserva, el numero de la habitacion, la fecha de check-in, la fecha de check-out y el nombre del cliente.
2.Cancelar reserva - Nos pide el ID de la reserva creada, una vez puesto se nos cancelara.
3.Mostrar reserva - Muestra la informacion de la reseva (ID, habitacion, cliente, check-in, check-out y el precio total por noche).
4.Mostrar habitaciones - Muestra el tipo de habitacion, el estado de disponibilidad y el precio por noche de cada una de las habitaciones. 
5.Mostrar datos por reserva - Muestra el nombre y el ID del cliente.
6.Salir - Sale del programa.

Lo hemos dividido de esta manera: 
hotel
|
|---App
|    |-Main.java
|
|---Controladores
|    |-GestionHabitacion.java
|    |-GestionReservas.java
|
|---Modelos
|    |-Cliente.java
|    |-EstadoHabitacion.java
|    |-Habitacion.java
|    |-Reservas.java
|    |-TipoHabitacion.java
|
|---Vista
|    |-Consola.java


