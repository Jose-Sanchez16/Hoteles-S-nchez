package com.mycompany.Modelos;

public class ReservaServicio {

    private int reservaId;
    private int servicioId;
    private int cantidad;
    private double precioUnitario;

    // Constructores
    public ReservaServicio() {
        this.cantidad = 1;
    }

    public ReservaServicio(int reservaId, int servicioId, int cantidad, double precioUnitario) {
        this.reservaId = reservaId;
        this.servicioId = servicioId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Método para calcular subtotal del servicio
    public double getSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return "ReservaServicio{"
                + "reservaId=" + reservaId
                + ", servicioId=" + servicioId
                + ", cantidad=" + cantidad
                + ", precioUnitario=" + precioUnitario
                + ", subtotal=" + getSubtotal()
                + '}';
    }
}
