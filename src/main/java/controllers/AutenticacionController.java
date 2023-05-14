package controllers;

import db.ConexionDB;
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
    
    ConexionDB conexion = ConexionDB.getInstance();
    
    public boolean login(String username, String password) {
        
        // Autenticación básica sin acceder a una base de datos
        
        Sesion.instance().setUsuario(
                new Usuario(1, "username", "nombre", "email", "password", "telefono", "rol"));
        
        return true;
        
    }
    
    public void logout() {
        
        Sesion.instance().setUsuario(null);
        
    }
    
}
