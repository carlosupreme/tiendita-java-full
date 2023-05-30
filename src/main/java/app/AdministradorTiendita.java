package app;

import views.inventario.InventarioFrame;

/**
 *
 * Clase principal de la aplicación
 *
 * @author Raul
 */
public class AdministradorTiendita {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new InventarioFrame().setVisible(true);
        });
    }
}
