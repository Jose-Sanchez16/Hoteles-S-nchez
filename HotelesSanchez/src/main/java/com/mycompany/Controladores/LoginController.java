package com.mycompany.Controladores;

import com.mycompany.Vistas.CancelacionView;
import com.mycompany.Vistas.LoginView;
import com.mycompany.Vistas.ReservasView;

public class LoginController {

    private LoginView loginView;
    
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initController();
    }
    
    private void initController() {
        loginView.setLoginListener(e -> validarLogin());
    }
    
    private void validarLogin() {
        String usuario = loginView.getUsuario();
        String password = loginView.getPassword();
        
        // Validación de credenciales
        if (usuario.equals("admin") && password.equals("admin")) {
            loginView.mostrarExito("Bienvenido Administrador!");
            loginView.dispose();
            
            // Abrir interfaz de Cancelación de Reservas
            CancelacionView cancelacionView = new CancelacionView();
            new CancelacionController(cancelacionView);
            cancelacionView.setVisible(true);
            
        } else if (usuario.equals("recepcion") && password.equals("recepcion")) {
            loginView.mostrarExito("Bienvenido Recepcionista!");
            loginView.dispose();
            
            // Abrir interfaz de Gestión de Reservas
            ReservasView reservasView = new ReservasView();
            new ReservasController(reservasView);
            reservasView.setVisible(true);
            
        } else {
            loginView.mostrarError("Usuario o contraseña incorrectos");
        }
    }
}