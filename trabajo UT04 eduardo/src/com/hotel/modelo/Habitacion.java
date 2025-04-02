package com.hotel.modelo;

public class Habitacion {
    private int numero;
    private TipoHabitacion tipo;
    private EstadoHabitacion estado;
    private String descripcion;
    
    public Habitacion(int numero, TipoHabitacion tipo, String descripcion) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = EstadoHabitacion.DISPONIBLE;
        this.descripcion = descripcion;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public TipoHabitacion getTipo() {
        return tipo;
    }
    
    public EstadoHabitacion getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }
    
    public int getPrecioPorNoche() {
        return tipo.getPrecio();
    }
}
