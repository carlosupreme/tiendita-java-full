package app;

import views.LoginFrame;

/**
 * 
 * Clase principal de la aplicación
 * 
 * @author Raul
 */
public class AdministradorTiendita {

    public static void main(String[] args) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
