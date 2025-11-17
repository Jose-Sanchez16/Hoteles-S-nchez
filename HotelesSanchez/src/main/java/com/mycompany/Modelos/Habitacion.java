package com.mycompany.Modelos;

public class Habitacion {

    private int habitacionId;
    private String numero;
    private int piso;
    private String estado; // Disponible, Ocupada, Mantenimiento, Limpieza
    private String caracteristicas;
    private int tarifaId;

    // Constructores
    public Habitacion() {
        this.estado = "Disponible";
    }

    public Habitacion(String numero, int piso, String caracteristicas, int tarifaId) {
        this();
        this.numero = numero;
        this.piso = piso;
        this.caracteristicas = caracteristicas;
        this.tarifaId = tarifaId;
    }

    // Getters y Setters
    public int getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(int habitacionId) {
        this.habitacionId = habitacionId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getTarifaId() {
        return tarifaId;
    }

    public void setTarifaId(int tarifaId) {
        this.tarifaId = tarifaId;
    }

    @Override
    public String toString() {
        return "Habitacion{"
                + "habitacionId=" + habitacionId
                + ", numero='" + numero + '\''
                + ", piso=" + piso
                + ", estado='" + estado + '\''
                + ", caracteristicas='" + caracteristicas + '\''
                + ", tarifaId=" + tarifaId
                + '}';
    }
}
