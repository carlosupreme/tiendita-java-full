package app;

import javax.swing.UIManager;
import views.LoginFrame;

/**
 *
 * Clase principal de la aplicación
 *
 * @author Raul
 */
public class AdministradorTiendita {

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        java.awt.EventQueue.invokeLater(() -> {

            new LoginFrame().setVisible(true);
        });
    }
}
