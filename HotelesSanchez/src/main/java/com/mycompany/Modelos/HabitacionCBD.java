package com.mycompany.Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionCBD {

    private Connection connection;

    public HabitacionCBD() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Habitacion> findAll() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM HABITACION";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setHabitacionId(rs.getInt("habitacion_id"));
                habitacion.setNumero(rs.getString("numero"));
                habitacion.setPiso(rs.getInt("piso"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setCaracteristicas(rs.getString("caracteristicas"));
                habitacion.setTarifaId(rs.getInt("tarifa_id"));

                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener habitaciones: " + e.getMessage());
        }
        return habitaciones;
    }

    public Habitacion findById(int id) {
        String sql = "SELECT * FROM HABITACION WHERE habitacion_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setHabitacionId(rs.getInt("habitacion_id"));
                habitacion.setNumero(rs.getString("numero"));
                habitacion.setPiso(rs.getInt("piso"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setCaracteristicas(rs.getString("caracteristicas"));
                habitacion.setTarifaId(rs.getInt("tarifa_id"));
                return habitacion;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar habitación: " + e.getMessage());
        }
        return null;
    }
}
