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
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
            //new InventarioFrame().setVisible(true);

        });
    }
}
