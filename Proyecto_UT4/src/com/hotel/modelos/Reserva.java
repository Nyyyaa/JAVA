package modelos;

import java.time.LocalDate;

public class Reserva {
    private String idReserva;
    private Habitacion habitacion;
    private Cliente cliente; 
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double precioTotal;

    // Constructor
    public Reserva(String idReserva, Habitacion habitacion, Cliente cliente, LocalDate checkIn, LocalDate checkOut) {
        // Valida las fechas de la reserva
        if (!validarFechas(checkIn, checkOut)) {
            throw new IllegalArgumentException("Las fechas de la reserva no son válidas.");
        }
        // Verifica si la habitación está disponible
        if (!validarHabitacionDisponible(habitacion)) {
            throw new IllegalArgumentException("La habitación no está disponible para reservar.");
        }

        // Inicialización de los atributos
        this.idReserva = idReserva;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.precioTotal = calcularPrecioTotal();

        // Cambia el estado de la habitación a "Reservada"
        habitacion.setEstado(EstadoHabitacion.RESERVADA);
    }

    // Validación de fechas corregida
    private boolean validarFechas(LocalDate checkIn, LocalDate checkOut) {
        return !checkOut.isBefore(checkIn) && // Check-out no puede ser antes del Check-in
               !checkIn.isBefore(LocalDate.now()) && // Check-in no puede ser antes de hoy
               checkOut.isBefore(checkIn.plusDays(90)); // Duración máxima de la estancia es de 90 días
    }

    // Verifica si la habitación está disponible
    private boolean validarHabitacionDisponible(Habitacion habitacion) {
        return habitacion.getEstado() == EstadoHabitacion.DISPONIBLE;
    }

    // Calcula el precio total de la reserva
    private double calcularPrecioTotal() {
        // Calcula el número de días de la reserva y multiplicar por el precio por noche
        long dias = checkOut.toEpochDay() - checkIn.toEpochDay();
        return dias * habitacion.getPrecioPorNoche();
    }

    // Método para cancelar la reserva
    public void cancelarReserva() {
        // Cambiar el estado de la habitación a "Disponible"
        habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
    }

    // Muestra información de la reserva
    public String mostrarInformacion() {
        return "Reserva:" +
               " ID='" + idReserva + '\'' +
               ", Habitación=" + habitacion.getNumero() +
               ", Cliente=" + (cliente != null ? cliente.getNombre() : "Sin cliente asociado") +
               ", Check-In=" + checkIn +
               ", Check-Out=" + checkOut +
               ", Precio Total=" + precioTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public String getIdReserva() {
        return idReserva;
    }
}