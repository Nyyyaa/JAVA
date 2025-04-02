package app;

import controladores.GestionHabitacion;
import modelos.Habitacion;
import vista.Consola;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicializa habitaciones
            List<Habitacion> habitaciones = inicializarHabitaciones();

            // Crea e iniciar la consola
            Consola consola = new Consola(habitaciones);
            consola.iniciar();
        } catch (Exception e) {
            System.out.println("Error fatal al iniciar el sistema: " + e.getMessage());
        }
    }

    private static List<Habitacion> inicializarHabitaciones() {
        GestionHabitacion gestionHabitacion = new GestionHabitacion();
        return gestionHabitacion.getHabitaciones();
    }
    
}