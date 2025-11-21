package com.mycompany.Vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CancelacionView extends JFrame {
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    private JButton btnCancelar, btnBuscar, btnActualizar;
    private JTextField txtBusqueda;
    private JComboBox<String> cmbFiltroEstado;
    
    public CancelacionView() {
        setTitle("Cancelación de Reservas - Hotel Sánchez");
        setSize(1300, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(1100, 600));
        initComponents();
    }
    
    private void initComponents() {
        // Panel superior - Búsqueda y Filtros
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Búsqueda y Filtros"));
        panelSuperior.setBackground(new Color(240, 240, 240));
        
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        panelSuperior.add(lblBuscar);
        
        txtBusqueda = new JTextField(20);
        txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 12));
        panelSuperior.add(txtBusqueda);
        
        JLabel lblFiltro = new JLabel("Filtrar por estado:");
        lblFiltro.setFont(new Font("Arial", Font.BOLD, 12));
        panelSuperior.add(lblFiltro);
        
        cmbFiltroEstado = new JComboBox<>(new String[]{"Todas", "Confirmada", "Activa", "Pendiente", "Cancelada", "Completada"});
        cmbFiltroEstado.setFont(new Font("Arial", Font.PLAIN, 12));
        panelSuperior.add(cmbFiltroEstado);
        
        btnBuscar = new JButton("🔍 Buscar");
        btnBuscar.setBackground(new Color(70, 130, 180));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        panelSuperior.add(btnBuscar);
        
        btnActualizar = new JButton("🔄 Actualizar");
        btnActualizar.setBackground(new Color(34, 139, 34));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnActualizar.setFocusPainted(false);
        panelSuperior.add(btnActualizar);
        
        // Tabla de reservas para cancelación
        String[] columnas = {"ID", "Huésped", "DNI", "Habitación", "Fecha Entrada", "Fecha Salida", "Estado", "Total", "Penalización"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        
        tablaReservas = new JTable(modeloTabla);
        tablaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaReservas.getTableHeader().setReorderingAllowed(false);
        tablaReservas.setFont(new Font("Arial", Font.PLAIN, 11));
        tablaReservas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Personalizar tabla
        tablaReservas.setRowHeight(25);
        tablaReservas.getColumnModel().getColumn(0).setPreferredWidth(60);   // ID
        tablaReservas.getColumnModel().getColumn(1).setPreferredWidth(180);  // Huésped
        tablaReservas.getColumnModel().getColumn(2).setPreferredWidth(120);  // DNI
        tablaReservas.getColumnModel().getColumn(3).setPreferredWidth(90);   // Habitación
        tablaReservas.getColumnModel().getColumn(4).setPreferredWidth(110);  // Entrada
        tablaReservas.getColumnModel().getColumn(5).setPreferredWidth(110);  // Salida
        tablaReservas.getColumnModel().getColumn(6).setPreferredWidth(100);  // Estado
        tablaReservas.getColumnModel().getColumn(7).setPreferredWidth(90);   // Total
        tablaReservas.getColumnModel().getColumn(8).setPreferredWidth(100);  // Penalización
        
        // Renderer para colorear filas según el estado
        tablaReservas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    String estado = table.getValueAt(row, 6).toString();
                    switch (estado) {
                        case "Cancelada":
                            c.setBackground(new Color(255, 200, 200)); // Rojo claro
                            break;
                        case "Confirmada":
                        case "Activa":
                            c.setBackground(new Color(200, 255, 200)); // Verde claro
                            break;
                        case "Pendiente":
                            c.setBackground(new Color(255, 255, 200)); // Amarillo claro
                            break;
                        case "Completada":
                            c.setBackground(new Color(200, 200, 255)); // Azul claro
                            break;
                        default:
                            c.setBackground(Color.WHITE);
                    }
                }
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Reservas para Cancelación"));
        scrollPane.setPreferredSize(new Dimension(1250, 450));
        
        // Panel inferior - Botones de acción
        JPanel panelInferior = new JPanel(new BorderLayout(15, 15));
        panelInferior.setBackground(new Color(240, 240, 240));
        
        // Panel de información
        JPanel panelInfo = new JPanel(new FlowLayout());
        panelInfo.setBackground(new Color(255, 240, 240));
        JLabel lblInfo = new JLabel("LAS CANCELACIONES PUEDEN GENERAR CARGOS POR PENALIZACIÓN SEGÚN LA POLÍTICA DEL HOTEL");
        lblInfo.setForeground(Color.RED);
        lblInfo.setFont(new Font("Arial", Font.BOLD, 12));
        panelInfo.add(lblInfo);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        panelBotones.setBackground(new Color(240, 240, 240));
        
        btnCancelar = new JButton("Cancelar Reserva Seleccionada");
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setPreferredSize(new Dimension(280, 45));
        panelBotones.add(btnCancelar);
        
        panelInferior.add(panelInfo, BorderLayout.NORTH);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);
        
        // Layout principal
        setLayout(new BorderLayout(15, 15));
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    // ========== MÉTODOS PARA EL CONTROLADOR ==========
    
    public void setCancelarListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }
    
    public void setBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }
    
    public void setActualizarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }
    
    public int getFilaSeleccionada() {
        return tablaReservas.getSelectedRow();
    }
    
    public String getReservaIdSeleccionada() {
        int fila = getFilaSeleccionada();
        if (fila >= 0) {
            return modeloTabla.getValueAt(fila, 0).toString();
        }
        return null;
    }
    
    public String getHuespedSeleccionado() {
        int fila = getFilaSeleccionada();
        if (fila >= 0) {
            return modeloTabla.getValueAt(fila, 1).toString();
        }
        return null;
    }
    
    public void agregarReservaATabla(Object[] fila) {
        modeloTabla.addRow(fila);
    }
    
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
    
    public String getTextoBusqueda() {
        return txtBusqueda.getText();
    }
    
    public String getFiltroEstado() {
        return cmbFiltroEstado.getSelectedItem().toString();
    }
    
    public boolean mostrarConfirmacionCancelacion(String reservaInfo) {
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea cancelar la reserva?\n\n" + reservaInfo +
            "\n\nEsta acción puede generar cargos por penalización según la política de cancelación del hotel." +
            "\n\nLa cancelación se registrará en la base de datos.",
            "Confirmar Cancelación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        return respuesta == JOptionPane.YES_OPTION;
    }
    
    public void mostrarDialogoPenalizacion(double penalizacion) {
        String mensaje;
        if (penalizacion > 0) {
            mensaje = "Cancelación exitosa.\n\n" +
                     "💰 Cargo por penalización: $" + String.format("%.2f", penalizacion) +
                     "\n\nEl huésped será notificado del cargo correspondiente." +
                     "\n\nLos cambios se han guardado en la base de datos.";
        } else {
            mensaje = "Cancelación exitosa.\n\n" +
                     "No se aplicaron cargos por penalización." +
                     "\n\nLos cambios se han guardado en la base de datos.";
        }
        
        JOptionPane.showMessageDialog(this,
            mensaje,
            "Reserva Cancelada - Base de Datos Actualizada",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        switch (tipo) {
            case 1: // ERROR
                JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
                break;
            case 2: // INFO
                JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3: // WARNING
                JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
        }
    }
}
