package controllers;

import app.Sesion;
import models.Usuario;

/**
 * 
 * Controlador de autenticación: proporciona un método para iniciar sesión
 * y otro para cerrar la sesión
 *
 * @author Raul
 */
public class AutenticacionController {
    
    public boolean login(String username, String password) {
        
        // Autenticación básica sin acceder a una base de datos
        
        if("usuario".equals(username) && "contraseña".equals(password)) {
            
            Sesion.instance().setUsuario(
                    new Usuario("Usuario", "Carlos", "Alberto"));
            
            return true;
            
        }
        
        return false;
        
    }
    
    public void logout() {
        
        Sesion.instance().setUsuario(null);
        
    }
    
}
