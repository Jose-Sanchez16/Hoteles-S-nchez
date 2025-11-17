package com.mycompany.Modelos;

import java.time.LocalDate;

public class Tarifa {

    private int tarifaId;
    private String temporada; // Alta, Baja, Media
    private double precioNoche;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String descripcion;

    // Constructores
    public Tarifa() {
    }

    public Tarifa(String temporada, double precioNoche, LocalDate fechaInicio,
            LocalDate fechaFin, String descripcion) {
        this.temporada = temporada;
        this.precioNoche = precioNoche;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getTarifaId() {
        return tarifaId;
    }

    public void setTarifaId(int tarifaId) {
        this.tarifaId = tarifaId;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tarifa{"
                + "tarifaId=" + tarifaId
                + ", temporada='" + temporada + '\''
                + ", precioNoche=" + precioNoche
                + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin
                + ", descripcion='" + descripcion + '\''
                + '}';
    }
}
