package com.mycompany.Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedCBD {

    private Connection connection;

    public HuespedCBD() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Huesped> findAll() {
        List<Huesped> huespedes = new ArrayList<>();
        String sql = "SELECT * FROM HUESPED";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Huesped huesped = new Huesped();
                huesped.setHuespedId(rs.getInt("huesped_id"));
                huesped.setDniPasaporte(rs.getString("dni_pasaporte"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setApellido(rs.getString("apellido"));
                huesped.setEmail(rs.getString("email"));
                huesped.setTelefono(rs.getString("telefono"));
                huesped.setPais(rs.getString("pais"));
                huesped.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
                huesped.setTipoHuesped(rs.getString("tipo_huesped"));

                huespedes.add(huesped);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener huéspedes: " + e.getMessage());
        }
        return huespedes;
    }

    public Huesped findById(int id) {
        String sql = "SELECT * FROM HUESPED WHERE huesped_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Huesped huesped = new Huesped();
                huesped.setHuespedId(rs.getInt("huesped_id"));
                huesped.setDniPasaporte(rs.getString("dni_pasaporte"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setApellido(rs.getString("apellido"));
                huesped.setEmail(rs.getString("email"));
                huesped.setTelefono(rs.getString("telefono"));
                huesped.setPais(rs.getString("pais"));
                huesped.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
                huesped.setTipoHuesped(rs.getString("tipo_huesped"));
                return huesped;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar huésped: " + e.getMessage());
        }
        return null;
    }

    public boolean create(Huesped huesped) {
        String sql = "INSERT INTO HUESPED (dni_pasaporte, nombre, apellido, email, telefono, pais, fecha_registro, tipo_huesped) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, huesped.getDniPasaporte());
            stmt.setString(2, huesped.getNombre());
            stmt.setString(3, huesped.getApellido());
            stmt.setString(4, huesped.getEmail());
            stmt.setString(5, huesped.getTelefono());
            stmt.setString(6, huesped.getPais());
            stmt.setDate(7, Date.valueOf(huesped.getFechaRegistro()));
            stmt.setString(8, huesped.getTipoHuesped());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        huesped.setHuespedId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear huésped: " + e.getMessage());
        }
        return false;
    }
}
