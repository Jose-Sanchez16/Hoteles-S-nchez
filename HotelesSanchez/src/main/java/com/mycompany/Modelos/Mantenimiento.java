package com.mycompany.Modelos;

import java.time.LocalDate;

public class Mantenimiento {

    private int mantenimientoId;
    private LocalDate fecha;
    private String descripcion;
    private String estado; // Pendiente, En Progreso, Completado, Cancelado
    private int habitacionId;
    private int empleadoId;

    public Mantenimiento() {
        this.fecha = LocalDate.now();
        this.estado = "Pendiente";
    }

    public Mantenimiento(String descripcion, int habitacionId, int empleadoId) {
        this.descripcion = descripcion;
        this.habitacionId = habitacionId;
        this.empleadoId = empleadoId;
    }

    public int getMantenimientoId() {
        return mantenimientoId;
    }

    public void setMantenimientoId(int mantenimientoId) {
        this.mantenimientoId = mantenimientoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(int habitacionId) {
        this.habitacionId = habitacionId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

}
