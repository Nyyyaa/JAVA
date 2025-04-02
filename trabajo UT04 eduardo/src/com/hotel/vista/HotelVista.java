package com.hotel.vista;

import java.time.LocalDate;
import java.util.*;

import com.hotel.controlador.ReservaControl;
import com.hotel.modelo.Cliente;
import com.hotel.modelo.EstadoHabitacion;
import com.hotel.modelo.Habitacion;
import com.hotel.modelo.Reserva;


public class HotelVista {
    private ReservaControl controlador;
    private Scanner sc;
    
    public HotelVista(ReservaControl controlador) {
        this.controlador = controlador;
        this.sc = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion; 
        do {
            System.out.println("\nMenú del Hotel:");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Crear reserva");
            System.out.println("3. Ver habitaciones disponibles");
            System.out.println("4. Ver información de los clientes");
            System.out.println("5. Ver información de una reserva");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombre = sc.nextLine();
                    Cliente cliente = new Cliente(nombre);
                    controlador.registrarCliente(cliente);
                    System.out.println("Cliente registrado con ID: " + cliente.getId());
                    break;
                case 2:
                    System.out.print("Ingrese el ID del cliente: ");
                    String clienteId = sc.nextLine();
                    Cliente clienteReserva = controlador.buscarClientePorId(clienteId);
                    if (clienteReserva == null) {
                        System.out.println("Cliente no encontrado.");
                        break;
                    }
                    
                    System.out.print("Ingrese el número de habitación: ");
                    int numHabitacion = sc.nextInt();
                    Habitacion habitacion = controlador.buscarHabitacionPorNumero(numHabitacion);
                    if (habitacion == null || habitacion.getEstado() != EstadoHabitacion.DISPONIBLE) {
                        System.out.println("La habitación no está disponible.");
                        break;
                    }
                    
                    System.out.print("Ingrese la fecha de check-in (YYYY-MM-DD): ");
                    LocalDate checkIn = LocalDate.parse(sc.next());
                    System.out.print("Ingrese la fecha de check-out (YYYY-MM-DD): ");
                    LocalDate checkOut = LocalDate.parse(sc.next());
                    
                    Reserva reserva = controlador.crearReserva(clienteReserva, habitacion, checkIn, checkOut);
                    System.out.println("Reserva creada con éxito. ID: " + reserva.getId());
                    break;
                case 3:
                    controlador.listarHabitacionesDisponibles();
                    break;
                case 4:
                    controlador.listarClientes();
                    break;
                case 5:
                    System.out.print("Ingrese el ID de la reserva: ");
                    String reservaId = sc.nextLine();
                    controlador.verReserva(reservaId);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }
}
