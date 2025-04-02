package com.hotel.vista;

import java.time.LocalDate;
import java.util.*;

import com.hotel.controlador.ReservaControl;
import com.hotel.modelo.Cliente;
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
            System.out.println("3. Salir");
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
                    System.out.print("Ingrese el número de habitación: ");
                    int numHabitacion = sc.nextInt();
                    System.out.print("Ingrese la fecha de check-in (DD-MM-YYYY): ");
                    LocalDate checkIn = LocalDate.parse(sc.next());
                    System.out.print("Ingrese la fecha de check-out (DD-MM-YYYY): ");
                    LocalDate checkOut = LocalDate.parse(sc.next());
                    Reserva reserva = controlador.crearReserva(clienteId, numHabitacion, checkIn, checkOut);
                    System.out.println("Reserva creada con éxito. ID: " + reserva);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);
    }
}
