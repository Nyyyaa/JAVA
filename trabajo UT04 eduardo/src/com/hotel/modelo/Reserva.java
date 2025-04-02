package com.hotel.modelo;

import java.time.LocalDate;
import java.util.*;

public class Reserva {
    private String id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double precioTotal;
    
    public Reserva(Habitacion habitacion, Cliente cliente, LocalDate checkIn, LocalDate checkOut) {
        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("El check-out no puede ser antes del check-in.");
        }
        if (checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden hacer reservas en fechas pasadas.");
        }
        
        this.id = UUID.randomUUID().toString();
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.precioTotal = calcularPrecioTotal();
        this.habitacion.setEstado(EstadoHabitacion.RESERVADA);
    }
    
    public boolean esActiva() {
        return LocalDate.now().isBefore(checkOut);
    }
    
    private double calcularPrecioTotal() {
        return habitacion.getPrecioPorNoche() * (checkOut.toEpochDay() - checkIn.toEpochDay());
    }
    
    public void cancelarReserva() {
        if (LocalDate.now().isAfter(checkIn)) {
            throw new IllegalArgumentException("No se puede cancelar una reserva en curso o finalizada.");
        }
        habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
    }
    
    public String getId() {
        return id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public Habitacion getHabitacion() {
        return habitacion;
    }
    
    public LocalDate getCheckIn() {
        return checkIn;
    }
    
    public LocalDate getCheckOut() {
        return checkOut;
    }
    
    public double getPrecioTotal() {
        return precioTotal;
    }
}
