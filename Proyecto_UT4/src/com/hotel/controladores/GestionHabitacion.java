package controladores;

import modelos.*;
import java.util.ArrayList;
import java.util.List;

public class GestionHabitacion {
    private List<Habitacion> habitaciones;

    // Constructor que inicializa la lista de habitaciones
    public GestionHabitacion() {
        this.habitaciones = new ArrayList<>();
        inicializarHabitaciones();
    }

    // Método para inicializar habitaciones
    private void inicializarHabitaciones() {
        inicializarHabitacionesPorRango(101, 105, TipoHabitacion.INDIVIDUAL);
        inicializarHabitacionesPorRango(201, 205, TipoHabitacion.DOBLE);
        inicializarHabitacionesPorRango(301, 305, TipoHabitacion.SUITE);
    }

    // Inicializa habitaciones en un rango sin necesidad de descripción
    private void inicializarHabitacionesPorRango(int inicio, int fin, TipoHabitacion tipo) {
        for (int numero = inicio; numero <= fin; numero++) {
            habitaciones.add(new Habitacion(numero, tipo, tipo.getPrecio(), "Descripción genérica"));
        }
    }

    public Habitacion buscarHabitacionPorNumero(int numero) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        throw new IllegalArgumentException("Habitación no encontrada.");
    }

    // Método para buscar habitaciones por tipo y estado
    public List<Habitacion> buscarHabitacionesPorTipoYEstado(TipoHabitacion tipo, EstadoHabitacion estado) {
        List<Habitacion> resultado = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo() == tipo && habitacion.getEstado() == estado) {
                resultado.add(habitacion);
            }
        }
        return resultado;
    }

    // Mostrar estadísticas de habitaciones
    public void mostrarEstadisticas() {
        int disponibles = 0, reservadas = 0, ocupadas = 0;
        for (Habitacion habitacion : habitaciones) {
            switch (habitacion.getEstado()) {
                case DISPONIBLE -> disponibles++;
                case RESERVADA -> reservadas++;
                case OCUPADA -> ocupadas++;
            }
        }
        System.out.println("Estadísticas de habitaciones:");
        System.out.println(" - Disponibles: " + disponibles);
        System.out.println(" - Reservadas: " + reservadas);
        System.out.println(" - Ocupadas: " + ocupadas);
    }

    // Obtener la lista de habitaciones
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }
}