package com.mycompany.Modelos;

import java.time.LocalDateTime;

public class CheckinCheck {

    private int checkId;
    private String tipo; // Check-In, Check-Out
    private LocalDateTime fechaHora;
    private int reservaId;
    private int empleadoId;

    // Constructores
    public CheckinCheck() {
        this.fechaHora = LocalDateTime.now();
    }

    public CheckinCheck(String tipo, int reservaId, int empleadoId) {
        this();
        this.tipo = tipo;
        this.reservaId = reservaId;
        this.empleadoId = empleadoId;
    }

    // Getters y Setters
    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    @Override
    public String toString() {
        return "CheckinCheck{"
                + "checkId=" + checkId
                + ", tipo='" + tipo + '\''
                + ", fechaHora=" + fechaHora
                + ", reservaId=" + reservaId
                + ", empleadoId=" + empleadoId
                + '}';
    }
}
