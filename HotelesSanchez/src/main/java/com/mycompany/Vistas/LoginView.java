package com.mycompany.Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    
    public LoginView() {
        setTitle("Login - Hotel Sánchez");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(600, 400));
        initComponents();
    }
    
    private void initComponents() {
        // Panel principal con borde y padding
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Panel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 240, 240));
        JLabel lblTitulo = new JLabel("SISTEMA HOTEL SÁNCHEZ");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 100, 200));
        titlePanel.add(lblTitulo);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 12, 18));
        panelForm.setBackground(new Color(240, 240, 240));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        
        // Etiquetas y campos
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        panelForm.add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setPreferredSize(new Dimension(150, 30));
        panelForm.add(txtUsuario);
        
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        panelForm.add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setPreferredSize(new Dimension(150, 30));
        panelForm.add(txtPassword);
        
        // Espacio vacío y botón
        panelForm.add(new JLabel(""));
        
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBackground(new Color(0, 100, 200));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setPreferredSize(new Dimension(120, 35));
        panelForm.add(btnLogin);
        
        mainPanel.add(panelForm, BorderLayout.CENTER);
        
        // Panel de información de credenciales
        JPanel panelInfo = new JPanel(new GridLayout(4, 1, 8, 8));
        panelInfo.setBackground(new Color(220, 230, 240));
        panelInfo.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150)), 
            "Credenciales de Prueba"
        ));
        
        JLabel lblAdmin1 = new JLabel("   Gerente: psanchez / admin123", SwingConstants.LEFT);
        JLabel lblRecepcion1 = new JLabel("   Recepcionista: lmartinez / password123", SwingConstants.LEFT);
        JLabel lblRecepcion2 = new JLabel("   Recepcionista: sdiaz / recep123", SwingConstants.LEFT);
        JLabel lblEspacio = new JLabel(" "); // Espacio adicional
        
        lblAdmin1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRecepcion1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRecepcion2.setFont(new Font("Arial", Font.PLAIN, 12));
        
        panelInfo.add(lblAdmin1);
        panelInfo.add(lblRecepcion1);
        panelInfo.add(lblRecepcion2);
        panelInfo.add(lblEspacio);
        
        mainPanel.add(panelInfo, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Hacer que el botón sea el predeterminado (se activa con Enter)
        getRootPane().setDefaultButton(btnLogin);
    }
    
    // Getters para el controlador
    public String getUsuario() {
        return txtUsuario.getText().trim();
    }
    
    public String getPassword() {
        return new String(txtPassword.getPassword());
    }
    
    public void setLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Login", JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }
}
