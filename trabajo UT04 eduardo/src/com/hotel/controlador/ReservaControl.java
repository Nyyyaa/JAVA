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

    public Reserva crearReserva(String clienteId, int numHabitacion, LocalDate checkIn, LocalDate checkOut) {
        Cliente cliente = clientes.stream()
                .filter(c -> c.getId().equals(clienteId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    
        // Debug: Ver qué habitaciones hay disponibles antes de buscar
        System.out.println("Habitaciones disponibles antes de la reserva:");
        habitaciones.forEach(h -> System.out.println("Hab: " + h.getNumero() + " Estado: " + h.getEstado()));
    
        Habitacion habitacion = habitaciones.stream()
                .filter(h -> h.getNumero() == numHabitacion && h.getEstado() == EstadoHabitacion.DISPONIBLE)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Habitación no disponible"));
    
        Reserva reserva = new Reserva(habitacion, cliente, checkIn, checkOut);
        reservas.add(reserva);
        cliente.agregarReserva(reserva);
        return reserva;
    }
}