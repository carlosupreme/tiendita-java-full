package controllers;

import app.Sesion;

/**
 * 
 * Controlador de autenticación: proporciona un método para iniciar sesión
 * y otro para cerrar la sesión
 *
 * @author Raul
 */
public class AutenticacionController {
    
    public boolean login(String username, String password) {
        
        return true;
        
    }
    
    public void logout() {
        
        Sesion.instance().setUsuario(null);
        
    }
    
}
