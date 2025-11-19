package com.mycompany.Controladores;

import com.mycompany.Modelos.Habitacion;
import com.mycompany.Modelos.HabitacionCBD;
import com.mycompany.Modelos.Huesped;
import com.mycompany.Modelos.HuespedCBD;
import com.mycompany.Modelos.Reserva;
import com.mycompany.Modelos.ReservaCBD;
import com.mycompany.Vistas.CancelacionView;
import java.util.List;

public class CancelacionController {
    private CancelacionView cancelacionView;
    private ReservaCBD reservaCBD;
    private HuespedCBD huespedCBD;

    private HabitacionCBD habitacionCBD;
    
    public CancelacionController(CancelacionView cancelacionView) {
        this.cancelacionView = cancelacionView;
        this.reservaCBD = new ReservaCBD();
        this.huespedCBD = new HuespedCBD();
        this.habitacionCBD = new HabitacionCBD();
        initController();
        cargarReservasParaCancelacion();
    }
    
    private void initController() {
        cancelacionView.setCancelarListener(e -> cancelarReserva());
        cancelacionView.setBuscarListener(e -> buscarReservas());
        cancelacionView.setActualizarListener(e -> cargarReservasParaCancelacion());
    }
    
    private void cargarReservasParaCancelacion() {
        cancelacionView.limpiarTabla();
        List<Reserva> reservas = reservaCBD.findAll();
        
        for (Reserva reserva : reservas) {
            Huesped huesped = huespedCBD.findById(reserva.getHuespedId());
            Habitacion habitacion = habitacionCBD.findById(reserva.getHabitacionId());
            
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
                    String.format("$%.2f", reserva.getTotalReserva()),
                    String.format("$%.2f", penalizacion)
                };
                cancelacionView.agregarReservaATabla(fila);
            }
        }
    }
    
    private void buscarReservas() {
        String textoBusqueda = cancelacionView.getTextoBusqueda().toLowerCase();
        String filtroEstado = cancelacionView.getFiltroEstado();
        cancelacionView.limpiarTabla();
        List<Reserva> reservas = reservaCBD.findAll();
        
        for (Reserva reserva : reservas) {
            Huesped huesped = huespedCBD.findById(reserva.getHuespedId());
            Habitacion habitacion = habitacionCBD.findById(reserva.getHabitacionId());
            
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
                        String.format("$%.2f", reserva.getTotalReserva()),
                        String.format("$%.2f", penalizacion)
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
            // Buscar y cancelar la reserva en la base de datos
            if (reservaCBD.updateEstado(reservaId, "Cancelada")) {
                double penalizacion = calcularPenalizacion(reservaCBD.findById(reservaId));
                cancelacionView.mostrarDialogoPenalizacion(penalizacion);
                cargarReservasParaCancelacion();
                
                JOptionPane.showMessageDialog(cancelacionView,
                    "Reserva #" + reservaId + " cancelada exitosamente",
                    "Cancelación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(cancelacionView,
                    "Error al cancelar la reserva",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private double calcularPenalizacion(Reserva reserva) {
        if (reserva == null || "Cancelada".equals(reserva.getEstado())) {
            return 0.0;
        }
        
        java.time.LocalDate hoy = java.time.LocalDate.now();
        long diasAntelacion = java.time.temporal.ChronoUnit.DAYS.between(hoy, reserva.getFechaEntrada());
        
        // Política de penalización
        if (diasAntelacion < 2) {
            return reserva.getTotalReserva() * 0.5; // 50%
        } else if (diasAntelacion < 7) {
            return reserva.getTotalReserva() * 0.2; // 20%
        } else {
            return 0.0; // Sin penalización
        }
    }
}