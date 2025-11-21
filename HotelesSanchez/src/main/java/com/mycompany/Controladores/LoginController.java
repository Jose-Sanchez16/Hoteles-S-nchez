package com.mycompany.Controladores;

import com.mycompany.Modelos.EmpleadoCBD;
import com.mycompany.Vistas.CancelacionView;
import com.mycompany.Vistas.LoginView;
import com.mycompany.Vistas.ReservasView;

public class LoginController {
    private LoginView loginView;
    private EmpleadoCBD empleadoCBD;
    
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.empleadoCBD = new EmpleadoCBD();
        initController();
    }
    
    private void initController() {
        loginView.setLoginListener(e -> validarLogin());
    }
    
    private void validarLogin() {
        String usuario = loginView.getUsuario();
        String password = loginView.getPassword();
        
        // Validar contra la base de datos
        if (empleadoCBD.validarCredenciales(usuario, password)) {
            String rol = empleadoCBD.obtenerRol(usuario);
            loginView.mostrarExito("Bienvenido " + rol + "!");
            loginView.dispose();
            
            if ("Gerente".equals(rol) || "Administrador".equals(rol)) {
                // Abrir interfaz de Cancelación de Reservas
                CancelacionView cancelacionView = new CancelacionView();
                new CancelacionController(cancelacionView);
                cancelacionView.setVisible(true);
            } else {
                // Abrir interfaz de Gestión de Reservas
                ReservasView reservasView = new ReservasView();
                new ReservasController(reservasView);
                reservasView.setVisible(true);
            }
        } else {
            loginView.mostrarError("Usuario o contraseña incorrectos");
        }
    }
}