package com.mycompany.Modelos;

public class Servicio {

    private int servicioId;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoriaServicio; // Restaurante, Spa, Lavandería, Transporte, etc.

    // Constructores
    public Servicio() {
    }

    public Servicio(String nombre, String descripcion, double precio, String categoriaServicio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoriaServicio = categoriaServicio;
    }

    // Getters y Setters
    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoriaServicio() {
        return categoriaServicio;
    }

    public void setCategoriaServicio(String categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @Override
    public String toString() {
        return "Servicio{"
                + "servicioId=" + servicioId
                + ", nombre='" + nombre + '\''
                + ", descripcion='" + descripcion + '\''
                + ", precio=" + precio
                + ", categoriaServicio='" + categoriaServicio + '\''
                + '}';
    }
}
