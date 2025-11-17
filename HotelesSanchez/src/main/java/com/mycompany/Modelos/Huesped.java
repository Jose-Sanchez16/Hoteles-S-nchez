package com.mycompany.Modelos;

import java.time.LocalDate;

public class Huesped {

    private int huespedId;
    private String dniPasaporte;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String pais;
    private LocalDate fechaRegistro;
    private String tipoHuesped;

    // Constructores
    public Huesped() {
        this.fechaRegistro = LocalDate.now();
    }

    public Huesped(String dniPasaporte, String nombre, String apellido, String email,
            String telefono, String pais, String tipoHuesped) {
        this();
        this.dniPasaporte = dniPasaporte;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.pais = pais;
        this.tipoHuesped = tipoHuesped;
    }

    // Getters y Setters
    public int getHuespedId() {
        return huespedId;
    }

    public void setHuespedId(int huespedId) {
        this.huespedId = huespedId;
    }

    public String getDniPasaporte() {
        return dniPasaporte;
    }

    public void setDniPasaporte(String dniPasaporte) {
        this.dniPasaporte = dniPasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoHuesped() {
        return tipoHuesped;
    }

    public void setTipoHuesped(String tipoHuesped) {
        this.tipoHuesped = tipoHuesped;
    }

    @Override
    public String toString() {
        return "Huesped{"
                + "huespedId=" + huespedId
                + ", dniPasaporte='" + dniPasaporte + '\''
                + ", nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", email='" + email + '\''
                + ", telefono='" + telefono + '\''
                + ", pais='" + pais + '\''
                + ", fechaRegistro=" + fechaRegistro
                + ", tipoHuesped='" + tipoHuesped + '\''
                + '}';
    }
}
