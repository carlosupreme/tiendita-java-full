package views;

import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class ErrorHandler {

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Datos erróneos",
                JOptionPane.ERROR_MESSAGE,
                null
        );
        System.err.println(message);
    }
}
