package com.hotel.vista;

import com.hotel.controlador.ReservaControl;
import com.hotel.modelo.Habitacion;
import com.hotel.modelo.TipoHabitacion;

public class Main {
    public static void main(String[] args) {
        ReservaControl controlador = new ReservaControl();

        // Agregamos habitaciones
        for (int planta = 1; planta <= 3; planta++) {
            for (int i = 1; i <= 5; i++) {
                int numeroHabitacion = planta * 100 + i;
                TipoHabitacion tipo = (i == 1) ? TipoHabitacion.INDIVIDUAL : (i <= 3) ? TipoHabitacion.DOBLE : TipoHabitacion.SUITE;
                controlador.agregarHabitacion(new Habitacion(numeroHabitacion, tipo, "Descripción genérica"));
            }
        }

        HotelVista vista = new HotelVista(controlador);
        vista.mostrarMenu();
    }
}