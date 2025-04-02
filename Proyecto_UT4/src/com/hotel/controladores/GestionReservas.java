package controladores;

import modelos.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionReservas {
    private List<Reserva> reservas = new ArrayList<>();
    private List<Habitacion> habitaciones;

    public GestionReservas(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Reserva crearReserva(String idReserva, int numeroHabitacion, LocalDate checkIn, LocalDate checkOut, String nombreCliente) {
        Habitacion habitacion = buscarHabitacionPorNumero(numeroHabitacion);

        // Validar check-in y check-out por separado
        if (!validarCheckIn(checkIn)) {
            throw new IllegalArgumentException("La fecha de check-in no es válida.");
        }
        if (!validarCheckOut(checkIn, checkOut)) {
            throw new IllegalArgumentException("La fecha de check-out no es válida.");
        }

        if (habitacion.getEstado() != EstadoHabitacion.DISPONIBLE) {
            throw new IllegalArgumentException("La habitación no está disponible para reservar.");
        }

        Cliente cliente = new Cliente(idReserva + "-Cliente", nombreCliente);
        Reserva reserva = new Reserva(idReserva, habitacion, cliente, checkIn, checkOut);
        reservas.add(reserva);
        return reserva;
    }

    // Método para validar la fecha de check-in
    private boolean validarCheckIn(LocalDate checkIn) {
        // Check-in debe ser igual o posterior a la fecha actual
        return !checkIn.isBefore(LocalDate.now());
    }

    // Método para validar la fecha de check-out
    private boolean validarCheckOut(LocalDate checkIn, LocalDate checkOut) {
        // Check-out debe ser posterior a check-in y no superar 90 días
        return !checkOut.isBefore(checkIn) && checkOut.isBefore(checkIn.plusDays(90));
    }

    public void cancelarReserva(String idReserva) {
        Reserva reserva = buscarReservaPorId(idReserva);

        if (reserva == null) {
            throw new IllegalArgumentException("Reserva no encontrada.");
        }

        if (LocalDate.now().isAfter(reserva.getCheckIn())) {
            throw new IllegalStateException("No se puede cancelar una reserva que ya ha comenzado.");
        }

        reserva.cancelarReserva();
        reservas.remove(reserva);
    }

    public List<Reserva> obtenerListaReservas() {
        return reservas;
    }

    public Habitacion buscarHabitacionPorNumero(int numeroHabitacion) {
        return habitaciones.stream()
                .filter(h -> h.getNumero() == numeroHabitacion)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Habitación no encontrada."));
    }

    public Reserva buscarReservaPorId(String idReserva) {
        return reservas.stream()
                .filter(r -> r.getIdReserva().equals(idReserva))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada."));
    }

    public void mostrarReservas() {
        reservas.forEach(reserva -> System.out.println(reserva.mostrarInformacion()));
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }
}