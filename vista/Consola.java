package vista;

import controladores.GestionReservas;
import modelos.Habitacion;
import modelos.Reserva;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Consola {
    private GestionReservas gestionReservas;
    public Consola(List<Habitacion> habitaciones) {
        this.gestionReservas = new GestionReservas(habitaciones);
    }

    // Menú
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            // Mostrar las opciones del menú
            System.out.println("\n--- Menú ---");
            System.out.println("1. Crear reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar reservas");
            System.out.println("4. Mostrar habitaciones");
            System.out.println("5. Mostrar datos del cliente por reserva");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            int opcion;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1 -> crearReserva(scanner);
                case 2 -> cancelarReserva(scanner);
                case 3 -> mostrarReservas();
                case 4 -> mostrarHabitaciones();
                case 5 -> mostrarDatosDelCliente(scanner);
                case 6 -> {
                    System.out.println("Saliendo del sistema...");
                    continuar = false;
                }
                default -> System.out.println("Opción inválida. Por favor, elige nuevamente.");
            }
        }

        scanner.close(); 
    }

    // Crea una nueva reserva
    private void crearReserva(Scanner scanner) {
        System.out.print("Ingresa el ID de la reserva: ");
        String idReserva = scanner.nextLine();

        System.out.print("Ingresa el número de la habitación: ");
        int numeroHabitacion;
        try {
            numeroHabitacion = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("El número de habitación debe ser un número entero. Intenta nuevamente.");
            scanner.nextLine();
            return;
        }

        // Solicita las fechas con validación
        LocalDate checkIn = pedirFecha(scanner, "Ingresa la fecha de check-in (YYYY-MM-DD): ");
        LocalDate checkOut = pedirFecha(scanner, "Ingresa la fecha de check-out (YYYY-MM-DD): ");

        System.out.print("Ingresa el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();

        try {
            // Crea la reserva con los datos proporcionados
            var reserva = gestionReservas.crearReserva(idReserva, numeroHabitacion, checkIn, checkOut, nombreCliente);
            System.out.println("Reserva creada exitosamente:");
            System.out.println(reserva.mostrarInformacion());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear la reserva: " + e.getMessage());
        }
    }

    // Cancela una reserva existente
    private void cancelarReserva(Scanner scanner) {
        System.out.print("Ingresa el ID de la reserva a cancelar: ");
        String idReserva = scanner.nextLine();

        try {
            gestionReservas.cancelarReserva(idReserva);
            System.out.println("Reserva cancelada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al cancelar la reserva: " + e.getMessage());
        }
    }

    // Muestra todas las reservas registradas
    private void mostrarReservas() {
        if (gestionReservas.obtenerListaReservas().isEmpty()) {
            System.out.println("No hay reservas registradas actualmente.");
        } else {
            System.out.println("Listado de reservas:");
            gestionReservas.mostrarReservas();
        }
    }

    // Muestra todas las habitaciones registradas
    private void mostrarHabitaciones() {
        List<Habitacion> habitaciones = gestionReservas.getHabitaciones();

        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas actualmente.");
        } else {
            System.out.println("\nListado de habitaciones:");
            for (Habitacion habitacion : habitaciones) {
                System.out.println(habitacion.mostrarInformacion());
            }
        }
    }

    // Muestra los datos del cliente asociado a una reserva
    private void mostrarDatosDelCliente(Scanner scanner) {
        System.out.print("Ingresa el ID de la reserva: ");
        String idReserva = scanner.nextLine();

        try {
            // Busca la reserva e imprime los datos del cliente
            Reserva reserva = gestionReservas.buscarReservaPorId(idReserva);
            if (reserva != null && reserva.getCliente() != null) {
                System.out.println("Datos del cliente asociado:");
                System.out.println("Nombre: " + reserva.getCliente().getNombre());
                System.out.println("ID Cliente: " + reserva.getCliente().getId());
            } else {
                System.out.println("No hay cliente asociado a esta reserva.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Solicita fechas y las valida
    private LocalDate pedirFecha(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Por favor, usa el formato YYYY-MM-DD.");
            }
        }
    }
}