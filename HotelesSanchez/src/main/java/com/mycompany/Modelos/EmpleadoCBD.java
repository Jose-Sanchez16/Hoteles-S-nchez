package com.mycompany.Modelos;

import java.sql.*;

public class EmpleadoCBD {

    private Connection connection;

    public EmpleadoCBD() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean validarCredenciales(String usuario, String contrasena) {
        String sql = "SELECT * FROM EMPLEADO WHERE usuario = ? AND contrasena = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Si hay resultados, las credenciales son válidas
        } catch (SQLException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
        }
        return false;
    }

    public String obtenerRol(String usuario) {
        String sql = "SELECT rol FROM EMPLEADO WHERE usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("rol");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener rol: " + e.getMessage());
        }
        return null;
    }
}
