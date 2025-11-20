package com.mycompany.Controladores;

import com.mycompany.Modelos.Habitacion;
import com.mycompany.Modelos.HabitacionCBD;
import com.mycompany.Modelos.Huesped;
import com.mycompany.Modelos.HuespedCBD;
import com.mycompany.Modelos.Reserva;
import com.mycompany.Modelos.ReservaCBD;
import com.mycompany.Vistas.ReservasView;
import javax.swing.JOptionPane;
import java.util.List;

public class ReservasController {
    private ReservasView reservasView;
    private ReservaCBD reservaCBD;
    private HuespedCBD huespedCBD;
    private HabitacionCBD habitacionCBD;
    
    public ReservasController(ReservasView reservasView) {
        this.reservasView = reservasView;
        this.reservaCBD = new ReservaCBD();
        this.huespedCBD = new HuespedCBD();
        this.habitacionCBD = new HabitacionCBD();
        initController();
        cargarReservas();
    }
    
    private void initController() {
        reservasView.setAgregarListener(e -> agregarReserva());
        reservasView.setActualizarListener(e -> cargarReservas());
        reservasView.setBuscarListener(e -> buscarReservas());
    }
    
    private void cargarReservas() {
        reservasView.limpiarTabla();
        List<Reserva> reservas = reservaCBD.findAll();
        
        for (Reserva reserva : reservas) {
            Huesped huesped = huespedCBD.findById(reserva.getHuespedId());
            Habitacion habitacion = habitacionCBD.findById(reserva.getHabitacionId());
            
            if (huesped != null && habitacion != null) {
                Object[] fila = {
                    reserva.getReservaId(),
                    huesped.getNombre() + " " + huesped.getApellido(),
                    habitacion.getNumero(),
                    reserva.getFechaEntrada().toString(),
                    reserva.getFechaSalida().toString(),
                    reserva.getEstado(),
                    String.format("$%.2f", reserva.getTotalReserva())
                };
                reservasView.agregarReservaATabla(fila);
            }
        }
        
        JOptionPane.showMessageDialog(reservasView, 
            "Lista de reservas actualizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void buscarReservas() {
        String textoBusqueda = reservasView.getTextoBusqueda().toLowerCase();
        reservasView.limpiarTabla();
        List<Reserva> reservas = reservaCBD.findAll();
        
        for (Reserva reserva : reservas) {
            Huesped huesped = huespedCBD.findById(reserva.getHuespedId());
            Habitacion habitacion = habitacionCBD.findById(reserva.getHabitacionId());
            
            if (huesped != null && habitacion != null) {
                String nombreCompleto = (huesped.getNombre() + " " + huesped.getApellido()).toLowerCase();
                
                if (nombreCompleto.contains(textoBusqueda) || textoBusqueda.isEmpty()) {
                    Object[] fila = {
                        reserva.getReservaId(),
                        huesped.getNombre() + " " + huesped.getApellido(),
                        habitacion.getNumero(),
                        reserva.getFechaEntrada().toString(),
                        reserva.getFechaSalida().toString(),
                        reserva.getEstado(),
                        String.format("$%.2f", reserva.getTotalReserva())
                    };
                    reservasView.agregarReservaATabla(fila);
                }
            }
        }
    }
    
    private void agregarReserva() {
        reservasView.mostrarDialogoAgregarReserva();
        // Aquí se implementaría la lógica para guardar en la base de datos
        // usando reservaCBD.create()
    }
}