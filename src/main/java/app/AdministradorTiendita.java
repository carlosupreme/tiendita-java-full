package app;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import javax.swing.*;
import views.*;

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
        });

        /*java.awt.EventQueue.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 500);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            f.add(new CobrarPanel()); 
            
            f.setVisible(true);
        });*/

        /*SelectStatementMapper<Proveedor> mapper = new SelectStatementMapper<>("proveedores");

        try {
            String[][] datos = mapper.selectAllAsArray(Proveedor.class, null);
            String[] columnasTabla = {"ID", "Nombre", "Direccion", "Email", "Telefono", "", "", ""};
            
            for(String[] datosA : datos) {
                for(String datoB : datosA) {
                    System.out.print(datoB + ", ");
                }
                System.out.println("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }*/
    }
}
