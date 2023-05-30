package app;

import views.proveedor.ProveedorFrame;

/**
 *
 * Clase principal de la aplicación
 *
 * @author Raul
 */
public class AdministradorTiendita {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new ProveedorFrame().setVisible(true);
        });
    }
}
