package com.mycompany.Modelos;

import java.time.LocalDate;

public class Reserva {

    private int reservaId;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String estado; // Confirmada, Pendiente, Cancelada, Completada
    private double totalReserva;
    private int cantidadAdultos;
    private int cantidadNinos;
    private int huespedId;
    private int habitacionId;

    // Constructores
    public Reserva() {
        this.estado = "Pendiente";
        this.fechaEntrada = LocalDate.now();
        this.fechaSalida = LocalDate.now().plusDays(1);
    }

    public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, double totalReserva,
            int cantidadAdultos, int cantidadNinos, int huespedId, int habitacionId) {
        this();
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.totalReserva = totalReserva;
        this.cantidadAdultos = cantidadAdultos;
        this.cantidadNinos = cantidadNinos;
        this.huespedId = huespedId;
        this.habitacionId = habitacionId;
    }

    // Getters y Setters
    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(double totalReserva) {
        this.totalReserva = totalReserva;
    }

    public int getCantidadAdultos() {
        return cantidadAdultos;
    }

    public void setCantidadAdultos(int cantidadAdultos) {
        this.cantidadAdultos = cantidadAdultos;
    }

    public int getCantidadNinos() {
        return cantidadNinos;
    }

    public void setCantidadNinos(int cantidadNinos) {
        this.cantidadNinos = cantidadNinos;
    }

    public int getHuespedId() {
        return huespedId;
    }

    public void setHuespedId(int huespedId) {
        this.huespedId = huespedId;
    }

    public int getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(int habitacionId) {
        this.habitacionId = habitacionId;
    }

    // Método para calcular días de estadía
    public int getDiasEstadia() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
    }

    @Override
    public String toString() {
        return "Reserva{"
                + "reservaId=" + reservaId
                + ", fechaEntrada=" + fechaEntrada
                + ", fechaSalida=" + fechaSalida
                + ", estado='" + estado + '\''
                + ", totalReserva=" + totalReserva
                + ", cantidadAdultos=" + cantidadAdultos
                + ", cantidadNinos=" + cantidadNinos
                + ", huespedId=" + huespedId
                + ", habitacionId=" + habitacionId
                + '}';
    }
}
