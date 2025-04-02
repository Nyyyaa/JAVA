package com.hotel.modelo;

public enum TipoHabitacion {
    INDIVIDUAL(50),
    DOBLE(80),
    SUITE(150);
    
    private final int precio;
    
    TipoHabitacion(int precio) {
        this.precio = precio;
    }
    
    public int getPrecio() {
        return precio;
    }
}