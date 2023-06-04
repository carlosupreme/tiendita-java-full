package views;

import javax.swing.*;

/**
 *
 * @author carlos
 */
public class MessageHandler {

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
        JOptionPane.showMessageDialog(null,
                message,
                title != null ? title : "Éxito",
                JOptionPane.INFORMATION_MESSAGE,
                null
        );

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
