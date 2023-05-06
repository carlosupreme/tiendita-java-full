package controllers;

import app.Sesion;

public class AutenticacionController {
    
    public boolean login(String username, String password) {
        
        return true;
        
    }
    
    public void logout() {
        
        Sesion.instance().setUsuario(null);
        
    }
    
}
