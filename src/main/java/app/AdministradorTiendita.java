package app;


import java.awt.Toolkit;
import javax.swing.JFrame;
import views.CobrarPanel;
import views.LoginFrame;

/**
 *
 * Clase principal de la aplicación
 *
 * @author Raul
 */
public class AdministradorTiendita {

    public static void main(String[] args) {
        
        
        java.awt.EventQueue.invokeLater(() -> {     
            
            JFrame f = new JFrame();
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 500);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.add(new CobrarPanel());

        f.setVisible(true);
            
            //new CobrarPanel().setVisible(true);
            //new InventarioFrame(new AutenticacionController()).setVisible(true)
        });

        /*java.awt.EventQueue.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 500);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            f.add(new CobrarPanel()); 
            
            f.setVisible(true);
        });*/
    }
}
