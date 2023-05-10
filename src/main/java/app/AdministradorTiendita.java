package app;

import db.PreparedStatementMapper;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        for(Field f : p.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                System.out.println(f.get(p));
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                System.out.println(ex.getMessage());
            }
        } 
        
        System.out.println(pSt.getSqlString(p));*/
        
    }
}
