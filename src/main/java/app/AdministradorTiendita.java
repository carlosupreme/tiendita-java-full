package app;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Insets;
import javax.swing.UIManager;
import user.infrastructure.LoginFrame;

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
        UIManager.put("Button.arc", 30);
        UIManager.put("Component.arc", 30);
        UIManager.put("TextComponent.arc", 30);
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
