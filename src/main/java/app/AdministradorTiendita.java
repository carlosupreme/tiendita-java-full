package app;

import db.PreparedStatementMapper;
import models.Proveedor;
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
            new LoginFrame().setVisible(true);
        }); 
        
        /*Proveedor p = new Proveedor(0, "Nombre", "Direccion", "Email", "Teléfono");
        
        PreparedStatementMapper<Proveedor> pSt = 
                new PreparedStatementMapper<>("proveedores");
        
        System.out.println(pSt.getSqlString(p));
        
    }
}
