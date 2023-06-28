package views;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author carlos
 */
public class MessageHandler {

    private final static int TIME = 2000;

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE,
                null
        );
        System.err.println(message);
    }

    public static void showSuccessMessage(String message, String title) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = pane.createDialog(null, title != null ? title : "Éxito");

        Timer timer = new Timer(TIME, (ActionEvent e) -> {
            dialog.setVisible(false);
            dialog.dispose();
        });

        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
        dialog.dispose();
    }

    public static boolean showConfirmMessage(String message, String title) {
        int option = JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null
        );

        return option == JOptionPane.YES_OPTION;
    }

}
