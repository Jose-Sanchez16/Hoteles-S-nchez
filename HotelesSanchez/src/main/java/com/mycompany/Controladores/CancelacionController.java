package com.mycompany.Controladores;

import com.mycompany.Modelos.Habitacion;
import com.mycompany.Modelos.Huesped;
import com.mycompany.Modelos.Reserva;
import com.mycompany.Vistas.CancelacionView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CancelacionController {
    private CancelacionView cancelacionView;
    private List<Reserva> reservas;
    private List<Huesped> huespedes;
    private List<Habitacion> habitaciones;
    
    public CancelacionController(CancelacionView cancelacionView) {
        this.cancelacionView = cancelacionView;
        this.reservas = new ArrayList<>();
        this.huespedes = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
        initController();
        cargarDatosEjemplo();
        cargarReservasParaCancelacion();
    }
    
    private void initController() {
        cancelacionView.setCancelarListener(e -> cancelarReserva());
        cancelacionView.setBuscarListener(e -> buscarReservas());
        cancelacionView.setActualizarListener(e -> cargarReservasParaCancelacion());
    }
    
    private void cargarDatosEjemplo() {
        // Cargar huéspedes de ejemplo
        Huesped h1 = new Huesped("12345678", "Juan", "Pérez", "juan@email.com", "555-1234", "México", "Regular");
        Huesped h2 = new Huesped("87654321", "María", "García", "maria@email.com", "555-5678", "España", "VIP");
        Huesped h3 = new Huesped("11223344", "Carlos", "López", "carlos@email.com", "555-9012", "Argentina", "Regular");
        Huesped h4 = new Huesped("44332211", "Ana", "Martínez", "ana@email.com", "555-3456", "Colombia", "Regular");
        
        h1.setHuespedId(1);
        h2.setHuespedId(2);
        h3.setHuespedId(3);
        h4.setHuespedId(4);
        
        huespedes.add(h1);
        huespedes.add(h2);
        huespedes.add(h3);
        huespedes.add(h4);
        
        // Cargar habitaciones de ejemplo
        Habitacion hab1 = new Habitacion("101", 1, "Habitación Standard", 1);
        Habitacion hab2 = new Habitacion("102", 1, "Habitación Standard", 1);
        Habitacion hab3 = new Habitacion("201", 2, "Suite", 2);
        Habitacion hab4 = new Habitacion("301", 3, "Habitación Deluxe", 3);
        
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
        Reserva r3 = new Reserva(LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 5), 450.0, 1, 0, 3, 2);
        Reserva r4 = new Reserva(LocalDate.of(2024, 1, 25), LocalDate.of(2024, 1, 30), 550.0, 2, 0, 4, 4);
        
        r1.setReservaId(1);
        r2.setReservaId(2);
        r3.setReservaId(3);
        r4.setReservaId(4);
        
        r1.setEstado("Confirmada");
        r2.setEstado("Pendiente");
        r3.setEstado("Confirmada");
        r4.setEstado("Cancelada");
        
        reservas.add(r1);
        reservas.add(r2);
        reservas.add(r3);
        reservas.add(r4);
    }
    
    private void cargarReservasParaCancelacion() {
        cancelacionView.limpiarTabla();
        
        for (Reserva reserva : reservas) {
            Huesped huesped = buscarHuespedPorId(reserva.getHuespedId());
            Habitacion habitacion = buscarHabitacionPorId(reserva.getHabitacionId());
            
            if (huesped != null && habitacion != null) {
                double penalizacion = calcularPenalizacion(reserva);
                
                Object[] fila = {
                    reserva.getReservaId(),
                    huesped.getNombre() + " " + huesped.getApellido(),
                    huesped.getDniPasaporte(),
                    habitacion.getNumero(),
                    reserva.getFechaEntrada().toString(),
                    reserva.getFechaSalida().toString(),
                    reserva.getEstado(),
                    "$" + reserva.getTotalReserva(),
                    "$" + penalizacion
                };
                cancelacionView.agregarReservaATabla(fila);
            }
        }
    }
    
    private void buscarReservas() {
        String textoBusqueda = cancelacionView.getTextoBusqueda().toLowerCase();
        String filtroEstado = cancelacionView.getFiltroEstado();
        cancelacionView.limpiarTabla();
        
        for (Reserva reserva : reservas) {
            Huesped huesped = buscarHuespedPorId(reserva.getHuespedId());
            Habitacion habitacion = buscarHabitacionPorId(reserva.getHabitacionId());
            
            if (huesped != null && habitacion != null) {
                String nombreCompleto = (huesped.getNombre() + " " + huesped.getApellido()).toLowerCase();
                String dni = huesped.getDniPasaporte().toLowerCase();
                
                boolean coincideBusqueda = nombreCompleto.contains(textoBusqueda) || 
                                         dni.contains(textoBusqueda) || 
                                         textoBusqueda.isEmpty();
                
                boolean coincideEstado = filtroEstado.equals("Todas") || 
                                       reserva.getEstado().equals(filtroEstado);
                
                if (coincideBusqueda && coincideEstado) {
                    double penalizacion = calcularPenalizacion(reserva);
                    
                    Object[] fila = {
                        reserva.getReservaId(),
                        huesped.getNombre() + " " + huesped.getApellido(),
                        huesped.getDniPasaporte(),
                        habitacion.getNumero(),
                        reserva.getFechaEntrada().toString(),
                        reserva.getFechaSalida().toString(),
                        reserva.getEstado(),
                        "$" + reserva.getTotalReserva(),
                        "$" + penalizacion
                    };
                    cancelacionView.agregarReservaATabla(fila);
                }
            }
        }
    }
    
    private void cancelarReserva() {
        int filaSeleccionada = cancelacionView.getFilaSeleccionada();
        
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(cancelacionView,
                "Por favor, seleccione una reserva para cancelar",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String reservaIdStr = cancelacionView.getReservaIdSeleccionada();
        String huesped = cancelacionView.getHuespedSeleccionado();
        
        if (reservaIdStr == null || huesped == null) {
            JOptionPane.showMessageDialog(cancelacionView,
                "Error al obtener datos de la reserva seleccionada",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int reservaId = Integer.parseInt(reservaIdStr);
        String reservaInfo = "Reserva #" + reservaId + " - " + huesped;
        
        // Mostrar confirmación
        boolean confirmar = cancelacionView.mostrarConfirmacionCancelacion(reservaInfo);
        
        if (confirmar) {
            // Buscar y cancelar la reserva
            Reserva reserva = buscarReservaPorId(reservaId);
            if (reserva != null && !reserva.getEstado().equals("Cancelada")) {
                reserva.setEstado("Cancelada");
                double penalizacion = calcularPenalizacion(reserva);
                
                cancelacionView.mostrarDialogoPenalizacion(penalizacion);
                
                // Actualizar la tabla
                cargarReservasParaCancelacion();
                
                JOptionPane.showMessageDialog(cancelacionView,
                    "Reserva #" + reservaId + " cancelada exitosamente",
                    "Cancelación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(cancelacionView,
                    "La reserva ya está cancelada o no existe",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private double calcularPenalizacion(Reserva reserva) {
        if (reserva.getEstado().equals("Cancelada")) {
            return 0.0; // Ya cancelada, no hay penalización adicional
        }
        
        LocalDate hoy = LocalDate.now();
        long diasAntelacion = java.time.temporal.ChronoUnit.DAYS.between(hoy, reserva.getFechaEntrada());
        
        // Política de penalización:
        if (diasAntelacion < 2) {
            return reserva.getTotalReserva() * 0.5; // 50% si cancela con menos de 2 días
        } else if (diasAntelacion < 7) {
            return reserva.getTotalReserva() * 0.2; // 20% si cancela con menos de 7 días
        } else {
            return 0.0; // Sin penalización si cancela con más de 7 días
        }
    }
    
    private Reserva buscarReservaPorId(int id) {
        for (Reserva r : reservas) {
            if (r.getReservaId() == id) {
                return r;
            }
        }
        return null;
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