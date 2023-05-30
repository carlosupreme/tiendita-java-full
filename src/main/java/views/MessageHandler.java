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
                "Datos erróneos",
                JOptionPane.ERROR_MESSAGE,
                null
        );
        System.err.println(message);
    }

    public static void showSuccessMessage(String message) {
        ImageIcon icon = new ImageIcon("cheque.png");
        JOptionPane.showMessageDialog(null,
                message,
                "Agregado correctamente",
                JOptionPane.PLAIN_MESSAGE,
                icon
        );
        System.err.println(message);
    }

    public static void showConfirmMessage(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Confirmación",
                JOptionPane.INFORMATION_MESSAGE,
                null
        );
        System.err.println(message);
    }
}
