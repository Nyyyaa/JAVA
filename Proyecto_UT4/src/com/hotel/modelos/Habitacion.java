package modelos;

public class Habitacion {
    private int numero;
    private TipoHabitacion tipo;
    private EstadoHabitacion estado;
    private String descripcion;
    private double precioPorNoche;

    public Habitacion(int numero, TipoHabitacion tipo, double precioPorNoche, String descripcion) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.estado = EstadoHabitacion.DISPONIBLE; // Por defecto, la habitación está disponible
        this.descripcion = descripcion;
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    // Setters: 
        public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public void setEstado(EstadoHabitacion estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        this.estado = estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        if (precioPorNoche < 0) {
            throw new IllegalArgumentException("El precio por noche no puede ser negativo.");
        }
        this.precioPorNoche = precioPorNoche;
    }

    // Calcular el precio total de una reserva
    public double calcularPrecioTotal(int numeroNoches) {
        if (numeroNoches <= 0) {
            throw new IllegalArgumentException("El número de noches debe ser mayor a cero.");
        }
        return precioPorNoche * numeroNoches;
    }

    // Mostrar la información de la habitación
    public String mostrarInformacion() {
        return "Habitación " + numero +
               " [" + tipo + "] Estado: " + estado +
               ", Precio por noche: " + precioPorNoche +
               ", Descripción: " + descripcion;
    }
}