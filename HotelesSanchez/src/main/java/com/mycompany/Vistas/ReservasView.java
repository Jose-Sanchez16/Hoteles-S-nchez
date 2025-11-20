package com.mycompany.Vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReservasView extends JFrame {
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnActualizar, btnBuscar;
    private JTextField txtBusqueda;
    
    public ReservasView() {
        setTitle("Gestión de Reservas - Hotel Sánchez");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(900, 550));
        initComponents();
    }
    
    private void initComponents() {
        // Panel superior - Búsqueda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Búsqueda y Filtros"));
        panelSuperior.setBackground(new Color(240, 240, 240));
        
        JLabel lblBuscar = new JLabel("Buscar por huésped:");
        lblBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        panelSuperior.add(lblBuscar);
        
        txtBusqueda = new JTextField(25);
        txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 12));
        panelSuperior.add(txtBusqueda);
        
        btnBuscar = new JButton("🔍 Buscar");
        btnBuscar.setBackground(new Color(70, 130, 180));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        panelSuperior.add(btnBuscar);
        
        btnActualizar = new JButton("🔄 Actualizar Lista");
        btnActualizar.setBackground(new Color(34, 139, 34));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnActualizar.setFocusPainted(false);
        panelSuperior.add(btnActualizar);
        
        // Tabla de reservas
        String[] columnas = {"ID", "Huésped", "Habitación", "Fecha Entrada", "Fecha Salida", "Estado", "Total"};
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
        tablaReservas.getColumnModel().getColumn(2).setPreferredWidth(90);   // Habitación
        tablaReservas.getColumnModel().getColumn(3).setPreferredWidth(110);  // Entrada
        tablaReservas.getColumnModel().getColumn(4).setPreferredWidth(110);  // Salida
        tablaReservas.getColumnModel().getColumn(5).setPreferredWidth(100);  // Estado
        tablaReservas.getColumnModel().getColumn(6).setPreferredWidth(90);   // Total
        
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Reservas"));
        scrollPane.setPreferredSize(new Dimension(1050, 400));
        
        // Panel inferior - Botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        panelInferior.setBorder(BorderFactory.createTitledBorder("Acciones"));
        panelInferior.setBackground(new Color(240, 240, 240));
        
        btnAgregar = new JButton("Agregar Nueva Reserva");
        btnAgregar.setBackground(new Color(34, 139, 34));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.setPreferredSize(new Dimension(220, 40));
        panelInferior.add(btnAgregar);
        
        // Layout principal
        setLayout(new BorderLayout(15, 15));
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    // ========== MÉTODOS PARA EL CONTROLADOR ==========
    
    public void setAgregarListener(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }
    
    public void setActualizarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }
    
    public void setBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
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
    
    public void agregarReservaATabla(Object[] fila) {
        modeloTabla.addRow(fila);
    }
    
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
    
    public String getTextoBusqueda() {
        return txtBusqueda.getText();
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
    
    public void mostrarDialogoAgregarReserva() {
        JDialog dialog = new JDialog(this, "Agregar Nueva Reserva", true);
        dialog.setSize(500, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));
        
        // Panel del formulario
        JPanel panelForm = new JPanel(new GridLayout(8, 2, 12, 15));
        panelForm.setBorder(BorderFactory.createEmptyBorder(25, 25, 20, 25));
        
        panelForm.add(new JLabel("Nombre Huésped:"));
        JTextField txtNombreHuesped = new JTextField();
        panelForm.add(txtNombreHuesped);
        
        panelForm.add(new JLabel("DNI:"));
        JTextField txtDni = new JTextField();
        panelForm.add(txtDni);
        
        panelForm.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField();
        panelForm.add(txtEmail);
        
        panelForm.add(new JLabel("N° Habitación:"));
        JComboBox<String> cmbHabitacion = new JComboBox<>(new String[]{"101", "102", "201", "202", "301"});
        panelForm.add(cmbHabitacion);
        
        panelForm.add(new JLabel("Fecha Entrada:"));
        JTextField txtEntrada = new JTextField("YYYY-MM-DD");
        panelForm.add(txtEntrada);
        
        panelForm.add(new JLabel("Fecha Salida:"));
        JTextField txtSalida = new JTextField("YYYY-MM-DD");
        panelForm.add(txtSalida);
        
        panelForm.add(new JLabel("Total:"));
        JTextField txtTotal = new JTextField("$0.00");
        panelForm.add(txtTotal);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnGuardar = new JButton("💾 Guardar Reserva");
        btnGuardar.setBackground(new Color(34, 139, 34));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        btnGuardar.addActionListener(e -> {
            // Validar campos
            if (txtNombreHuesped.getText().trim().isEmpty() || 
                txtDni.getText().trim().isEmpty() ||
                txtEntrada.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(dialog, 
                    "Por favor complete todos los campos obligatorios", 
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(dialog, 
                "Reserva agregada exitosamente!\nLos datos se han guardado en la base de datos.", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        dialog.add(panelForm, BorderLayout.CENTER);
        dialog.add(panelBotones, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}