package com.hotel.modelo;

import java.util.*;

public class Cliente {
    private String id;
    private String nombre;
    private List<Reserva> historialReservas;
    
    public Cliente(String nombre) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.historialReservas = new ArrayList<>();
    }
    
    public String getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public List<Reserva> getHistorialReservas() {
        return historialReservas;
    }
    
    public void agregarReserva(Reserva reserva) {
        if (reservasActivas() >= 3) {
            throw new IllegalArgumentException("El cliente ya tiene 3 reservas activas.");
        }
        historialReservas.add(reserva);
    }
    
    private long reservasActivas() {
        return historialReservas.stream().filter(Reserva::esActiva).count();
    }
}
