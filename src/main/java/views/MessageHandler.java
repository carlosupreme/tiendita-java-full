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
//        ImageIcon icon = new ImageIcon(MessageHandler.class.getResource("/cheque.png"));
        JOptionPane.showMessageDialog(null,
                message,
                "Agregado correctamente",
                JOptionPane.INFORMATION_MESSAGE,
                null
        );
        System.err.println(message);
    }

}
