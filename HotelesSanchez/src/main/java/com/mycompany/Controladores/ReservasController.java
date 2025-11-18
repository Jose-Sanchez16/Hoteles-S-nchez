package com.mycompany.Controladores;

import com.mycompany.Modelos.Habitacion;
import com.mycompany.Modelos.Huesped;
import com.mycompany.Modelos.Reserva;
import com.mycompany.Vistas.ReservasView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ReservasController {
    private ReservasView reservasView;
    private List<Reserva> reservas;
    private List<Huesped> huespedes;
    private List<Habitacion> habitaciones;
    
    public ReservasController(ReservasView reservasView) {
        this.reservasView = reservasView;
        this.reservas = new ArrayList<>();
        this.huespedes = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
        initController();
        cargarDatosEjemplo();
        cargarReservas();
    }
    
    private void initController() {
        reservasView.setAgregarListener(e -> agregarReserva());
        reservasView.setActualizarListener(e -> cargarReservas());
        reservasView.setBuscarListener(e -> buscarReservas());
    }
    
    private void cargarDatosEjemplo() {
        // Cargar huéspedes de ejemplo
        Huesped h1 = new Huesped("12345678", "Juan", "Pérez", "juan@email.com", "555-1234", "México", "Regular");
        Huesped h2 = new Huesped("87654321", "María", "García", "maria@email.com", "555-5678", "España", "VIP");
        Huesped h3 = new Huesped("11223344", "Carlos", "López", "carlos@email.com", "555-9012", "Argentina", "Regular");
        
        h1.setHuespedId(1);
        h2.setHuespedId(2);
        h3.setHuespedId(3);
        
        huespedes.add(h1);
        huespedes.add(h2);
        huespedes.add(h3);
        
        // Cargar habitaciones de ejemplo
        Habitacion hab1 = new Habitacion("101", 1, "Habitación Standard con cama doble", 1);
        Habitacion hab2 = new Habitacion("102", 1, "Habitación Standard con camas individuales", 1);
        Habitacion hab3 = new Habitacion("201", 2, "Suite con vista al mar", 2);
        Habitacion hab4 = new Habitacion("202", 2, "Habitación Deluxe con jacuzzi", 3);
        
        hab1.setHabitacionId(1);
        hab2.setHabitacionId(2);
        hab3.setHabitacionId(3);
        hab4.setHabitacionId(4);
        
        habitaciones.add(hab1);
        habitaciones.add(hab2);
        habitaciones.add(hab3);
        habitaciones.add(hab4);
        
        // Cargar reservas de ejemplo
        Reserva r1 = new Reserva(LocalDate.of(2024, 1, 15), LocalDate.of(2024, 1, 20), 500.0, 2, 0, 1, 1);
        Reserva r2 = new Reserva(LocalDate.of(2024, 1, 18), LocalDate.of(2024, 1, 22), 600.0, 2, 1, 2, 3);
        Reserva r3 = new Reserva(LocalDate.of(2024, 1, 20), LocalDate.of(2024, 1, 25), 450.0, 1, 0, 3, 2);
        
        r1.setReservaId(1);
        r2.setReservaId(2);
        r3.setReservaId(3);
        
        r1.setEstado("Confirmada");
        r2.setEstado("Pendiente");
        r3.setEstado("Confirmada");
        
        reservas.add(r1);
        reservas.add(r2);
        reservas.add(r3);
    }
    
    private void cargarReservas() {
        reservasView.limpiarTabla();
        
        for (Reserva reserva : reservas) {
            Huesped huesped = buscarHuespedPorId(reserva.getHuespedId());
            Habitacion habitacion = buscarHabitacionPorId(reserva.getHabitacionId());
            
            if (huesped != null && habitacion != null) {
                Object[] fila = {
                    reserva.getReservaId(),
                    huesped.getNombre() + " " + huesped.getApellido(),
                    habitacion.getNumero(),
                    reserva.getFechaEntrada().toString(),
                    reserva.getFechaSalida().toString(),
                    reserva.getEstado(),
                    "$" + reserva.getTotalReserva()
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
        
        for (Reserva reserva : reservas) {
            Huesped huesped = buscarHuespedPorId(reserva.getHuespedId());
            Habitacion habitacion = buscarHabitacionPorId(reserva.getHabitacionId());
            
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
                        "$" + reserva.getTotalReserva()
                    };
                    reservasView.agregarReservaATabla(fila);
                }
            }
        }
    }
    
    private void agregarReserva() {
        // Mostrar diálogo para agregar reserva
        reservasView.mostrarDialogoAgregarReserva();
        
        // Simulación de nueva reserva (en un sistema real, los datos vendrían del diálogo)
        Reserva nuevaReserva = new Reserva(
            LocalDate.now().plusDays(5), 
            LocalDate.now().plusDays(7), 
            550.0, 2, 1, 1, 2
        );
        nuevaReserva.setReservaId(reservas.size() + 1);
        nuevaReserva.setEstado("Pendiente");
        reservas.add(nuevaReserva);
        
        // Actualizar la tabla
        cargarReservas();
        
        JOptionPane.showMessageDialog(reservasView,
            "Reserva agregada exitosamente!", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private Huesped buscarHuespedPorId(int id) {
        for (Huesped h : huespedes) {
            if (h.getHuespedId() == id) {
                return h;
            }
        }
        return null;
    }
    
    private Habitacion buscarHabitacionPorId(int id) {
        for (Habitacion h : habitaciones) {
            if (h.getHabitacionId() == id) {
                return h;
            }
        }
        return null;
    }
}