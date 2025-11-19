package com.mycompany.Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaCBD {

    private Connection connection;

    public ReservaCBD() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Reserva> findAll() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM RESERVA";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setReservaId(rs.getInt("reserva_id"));
                reserva.setFechaEntrada(rs.getDate("fecha_entrada").toLocalDate());
                reserva.setFechaSalida(rs.getDate("fecha_salida").toLocalDate());
                reserva.setEstado(rs.getString("estado"));
                reserva.setTotalReserva(rs.getDouble("total_reserva"));
                reserva.setCantidadAdultos(rs.getInt("cantidad_adultos"));
                reserva.setCantidadNinos(rs.getInt("cantidad_ninos"));
                reserva.setHuespedId(rs.getInt("huesped_id"));
                reserva.setHabitacionId(rs.getInt("habitacion_id"));

                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reservas: " + e.getMessage());
        }
        return reservas;
    }

    public Reserva findById(int id) {
        String sql = "SELECT * FROM RESERVA WHERE reserva_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setReservaId(rs.getInt("reserva_id"));
                reserva.setFechaEntrada(rs.getDate("fecha_entrada").toLocalDate());
                reserva.setFechaSalida(rs.getDate("fecha_salida").toLocalDate());
                reserva.setEstado(rs.getString("estado"));
                reserva.setTotalReserva(rs.getDouble("total_reserva"));
                reserva.setCantidadAdultos(rs.getInt("cantidad_adultos"));
                reserva.setCantidadNinos(rs.getInt("cantidad_ninos"));
                reserva.setHuespedId(rs.getInt("huesped_id"));
                reserva.setHabitacionId(rs.getInt("habitacion_id"));
                return reserva;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar reserva: " + e.getMessage());
        }
        return null;
    }

    public boolean updateEstado(int reservaId, String nuevoEstado) {
        String sql = "UPDATE RESERVA SET estado = ? WHERE reserva_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, reservaId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado de reserva: " + e.getMessage());
        }
        return false;
    }

    public boolean create(Reserva reserva) {
        String sql = "INSERT INTO RESERVA (fecha_entrada, fecha_salida, estado, total_reserva, cantidad_adultos, cantidad_ninos, huesped_id, habitacion_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
            stmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
            stmt.setString(3, reserva.getEstado());
            stmt.setDouble(4, reserva.getTotalReserva());
            stmt.setInt(5, reserva.getCantidadAdultos());
            stmt.setInt(6, reserva.getCantidadNinos());
            stmt.setInt(7, reserva.getHuespedId());
            stmt.setInt(8, reserva.getHabitacionId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reserva.setReservaId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear reserva: " + e.getMessage());
        }
        return false;
    }
}
