package com.mycompany.Modelos;

import java.time.LocalDate;

public class Factura {

    private int facturaId;
    private LocalDate fechaEmision;
    private double subtotal;
    private double impuestos;
    private double total;
    private String estado; // Pagada, Pendiente, Anulada
    private int reservaId;

    // Constructores
    public Factura() {
        this.fechaEmision = LocalDate.now();
        this.estado = "Pendiente";
        this.impuestos = 0.18; // 18% de impuestos por defecto
    }

    public Factura(double subtotal, int reservaId) {
        this();
        this.subtotal = subtotal;
        this.reservaId = reservaId;
        calcularTotales();
    }

    // Getters y Setters
    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
        calcularTotales();
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
        calcularTotales();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    // Método para calcular totales
    private void calcularTotales() {
        this.total = this.subtotal + (this.subtotal * this.impuestos);
    }

    @Override
    public String toString() {
        return "Factura{"
                + "facturaId=" + facturaId
                + ", fechaEmision=" + fechaEmision
                + ", subtotal=" + subtotal
                + ", impuestos=" + impuestos
                + ", total=" + total
                + ", estado='" + estado + '\''
                + ", reservaId=" + reservaId
                + '}';
    }
}
