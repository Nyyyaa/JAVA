package com.hotel.controlador;

import java.time.LocalDate;
import java.util.*;

import com.hotel.modelo.Cliente;
import com.hotel.modelo.Habitacion;
import com.hotel.modelo.Reserva;
import com.hotel.modelo.EstadoHabitacion;

public class ReservaControl {
    private List<Habitacion> habitaciones;
    private List<Cliente> clientes;
    private List<Reserva> reservas;

    public ReservaControl() {
        this.habitaciones = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    
    // Método para buscar un cliente por su ID
    public Cliente buscarClientePorId(String id) {
        return clientes.stream()
                       .filter(c -> c.getId().equals(id))
                       .findFirst()
                       .orElse(null); // Retorna null si no se encuentra el cliente
    }

    // Método para buscar una habitación por su número
    public Habitacion buscarHabitacionPorNumero(int numero) {
        return habitaciones.stream()
                           .filter(h -> h.getNumero() == numero)
                           .findFirst()
                           .orElse(null); // Retorna null si no se encuentra la habitación
    }
    
    // Método para listar las habitaciones disponibles
    public void listarHabitacionesDisponibles() {
        habitaciones.stream()
                    .filter(h -> h.getEstado() == EstadoHabitacion.DISPONIBLE)
                    .forEach(h -> System.out.println("Habitación " + h.getNumero() + " - " + h.getTipo()));
    }
    
    // Método para listar los clientes registrados
    public void listarClientes() {
        clientes.forEach(c -> {
            System.out.println("Cliente: " + c.getNombre() + " (ID: " + c.getId() + ")");
        });
    }
    
    // Método para ver una reserva
    public void verReserva(String reservaId) {
        reservas.stream()
                .filter(r -> r.getId().equals(reservaId))
                .findFirst()
                .ifPresentOrElse(r -> System.out.println("Reserva ID: " + r.getId() + ", Cliente: " + r.getCliente().getNombre() + ", Habitación: " + r.getHabitacion().getNumero()),
                                 () -> System.out.println("Reserva no encontrada"));
    }
    
    // Método para crear una nueva reserva
    public Reserva crearReserva(Cliente cliente, Habitacion habitacion, LocalDate checkIn, LocalDate checkOut) {
        if (habitacion.getEstado() != EstadoHabitacion.DISPONIBLE) {
            throw new IllegalArgumentException("Habitación no disponible");
        }
        Reserva nuevaReserva = new Reserva(habitacion, cliente, checkIn, checkOut);
        cliente.agregarReserva(nuevaReserva);
        reservas.add(nuevaReserva);
        System.out.println("Reserva creada con éxito. ID: " + nuevaReserva.getId());
        return nuevaReserva;
    }
}