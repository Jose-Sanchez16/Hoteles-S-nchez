package com.mycompany.hotelessanchez;

import com.mycompany.Controladores.LoginController;
import com.mycompany.Vistas.LoginView;

public class HotelesSanchez {
    public static void main(String[] args) {
        // Mensaje de inicio
        System.out.println("=========================================");
        System.out.println("    SISTEMA HOTEL SÁNCHEZ - MVC");
        System.out.println("=========================================");
        System.out.println("Iniciando aplicación...");
        System.out.println("Credenciales de prueba:");
        System.out.println("  - Administrador: admin / admin");
        System.out.println("  - Recepción: recepcion / recepcion");
        System.out.println("=========================================");
        
        // Usar invokeLater para seguridad en hilos de Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Crear y mostrar la vista de login
                    LoginView loginView = new LoginView();
                    new LoginController(loginView);
                    loginView.setVisible(true);
                    
                    System.out.println("Aplicación iniciada correctamente");
                } catch (Exception e) {
                    System.err.println(" Error al iniciar la aplicación:");
                    e.printStackTrace();
                }
            }
        });
    }
}
