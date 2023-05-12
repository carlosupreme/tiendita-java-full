package app;

import java.awt.Toolkit;
import javax.swing.JFrame;
import views.CobrarPanel;

/**
 * 
 * Clase principal de la aplicación
 *
 * @author Raul
 */
public class AdministradorTiendita {

    public static void main(String[] args) {
        /*java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });*/ 
        
        java.awt.EventQueue.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 500);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            f.add(new CobrarPanel()); 
            
            f.setVisible(true);
        }); 
        
        
        /*Proveedor p = new Proveedor(0, "Nombre", "Direccion", "Email", "Teléfono");
        
        PreparedStatementMapper<Proveedor> pSt = 
                new PreparedStatementMapper<>("proveedores");
        
        System.out.println(pSt.getSqlString(p));*/
        
    }
}
