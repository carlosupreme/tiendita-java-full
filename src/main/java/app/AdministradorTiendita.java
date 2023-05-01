package app;

import controllers.AutenticacionController;
import views.inventario.InventarioFrame;

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
            new InventarioFrame(new AutenticacionController()).setVisible(true);
        });
    }
}
