package modelos;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nombre;
    private List<Reserva> historialReservas;
    private List<Reserva> reservasActivas;

    // Constructor
    public Cliente(String id, String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre no válido. Por favor, escriba el nombre.");
        }
        this.id = id;
        this.nombre = nombre;
        this.historialReservas = new ArrayList<>();
        this.reservasActivas = new ArrayList<>();
    }

    // Gestionar reservas
    public void agregarReserva(Reserva reserva) {
        if (reservasActivas.size() >= 3) {
            throw new IllegalStateException("El cliente ya tiene el máximo de 3 reservas activas.");
        }
        if (reservasActivas.contains(reserva)) {
            throw new IllegalArgumentException("La reserva ya está activa.");
        }
        reservasActivas.add(reserva);
    }

    public void cancelarReserva(Reserva reserva) {
        if (reservasActivas.contains(reserva)) {
            reservasActivas.remove(reserva);
            historialReservas.add(reserva);
        } else {
            throw new IllegalArgumentException("La reserva no existe.");
        }
    }

    public List<Reserva> buscarReservasActivas() {
        return new ArrayList<>(reservasActivas);
    }

    public List<Reserva> listarHistorialReservas() {
        return new ArrayList<>(historialReservas);
    }

    public Reserva buscarReservaHistorial(String idReserva) {
        return historialReservas.stream()
                .filter(r -> r.getIdReserva().equals(idReserva))
                .findFirst()
                .orElse(null);
    }

    // Resumen del cliente
    public String mostrarResumen() {
        return "Cliente: " + nombre + "\n" +
               "Reservas Activas: " + reservasActivas.size() + "\n" +
               "Historial de Reservas: " + historialReservas.size();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Reserva> getReservasActivas() {
        return reservasActivas;
    }

    public List<Reserva> getHistorialReservas() {
        return historialReservas;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre no válido. Por favor, escriba el nombre.");
        }
        this.nombre = nombre;
    }
}