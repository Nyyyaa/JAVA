En este proyecto hemos creado un sistema de reserva de habitaciones de un hotel. En ese hotel esta dividido en tres plantas y lo hemos planteado de la siguiente manera: 
-Primera planta: habitaciones individuales, habitaciones del 101 al 105, precio a 50€.
-Segunda planta: habitaciones dobles, habitaciones del 201 al 205, precio a 80€.
-Tercera planta: habitaciones suite, habitaciones del 301 al 305, precio a 150€.

A la hora de crear una reserva, dependiendo de la cantidad de días que se hospede el usuario, le dará el precio total por noche.

Hemos creado un menu con 6 opciones diferentes:
1.Crear reserva - Nos pedira que ingresemos el numero de reserva, el numero de la habitacion, la fecha de check-in, la fecha de check-out y el nombre del cliente.

![Captura de pantalla 2025-04-02 234651](https://github.com/user-attachments/assets/97919426-b13f-45c8-b114-6f339be37e9e)

2.Cancelar reserva - Nos pide el ID de la reserva creada, una vez puesto se nos cancelara.

![Captura de pantalla 2025-04-02 235245](https://github.com/user-attachments/assets/0a78f58d-076f-4133-8973-2e716bcac559)

3.Mostrar reserva - Muestra la informacion de la reseva (ID, habitacion, cliente, check-in, check-out y el precio total por noche).

![Captura de pantalla 2025-04-02 234847](https://github.com/user-attachments/assets/48f98d9f-c8d4-47ce-8192-2158b96e25a9)


4.Mostrar habitaciones - Muestra el tipo de habitacion, el estado de disponibilidad y el precio por noche de cada una de las habitaciones. 

![Captura de pantalla 2025-04-02 235114](https://github.com/user-attachments/assets/810b855a-7394-4b23-b025-bfc4e0ef7e7c)

5.Mostrar datos por reserva - Muestra el nombre y el ID del cliente.

![Captura de pantalla 2025-04-02 235208](https://github.com/user-attachments/assets/d018ecd9-942c-4d24-aa34-204d25d5f9ab)

6.Salir - Sale del programa.

![Captura de pantalla 2025-04-02 235348](https://github.com/user-attachments/assets/22c7387f-3e3e-4d15-bfb2-7dea9578f01d)


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


