package app;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Insets;
import javax.swing.UIManager;
import views.LoginFrame;

/**
 *
 * Clase principal de la aplicación
 *
 * @author Raul
 */
public class AdministradorTiendita {

    public static void main(String[] args) throws Exception {

        if (SystemInfo.isMacOS) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Tiendita");
            System.setProperty("apple.awt.application.appearance", "NSAppearanceNameAqua");
        }

        FlatMacLightLaf.setup();

        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.arc", 800);
        UIManager.put("Component.arc", 800);
        UIManager.put("ProgressBar.arc", 999);
        UIManager.put("TextComponent.arc", 800);
        UIManager.put("OptionPane.yesButtonText", "Sí");
        UIManager.put("OptionPane.okButtonText", "Aceptar");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.titleText", "Mensaje");
        UIManager.put("OptionPane.messageDialogTitle", "Mensaje");

        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
