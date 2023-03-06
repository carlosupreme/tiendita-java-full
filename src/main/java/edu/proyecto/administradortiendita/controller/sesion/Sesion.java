package edu.proyecto.administradortiendita.controller.sesion;

import edu.proyecto.administradortiendita.model.Usuario;

/**
 *
 * Proporciona la sesión global de la aplicación.
 * 
 * @author Raul
 */
public class Sesion {
    
    private static Sesion sesion;

    private Sesion() {}

    public static Sesion instance() {
        if (sesion == null) {
            sesion = new Sesion();
        }
        return sesion;
    }

    private Usuario usuario;
    
    public Usuario getUsuario() {
        if(usuario == null) {
            throw new RuntimeException("La sesión no contiene un usuario. Inicie sesión");
        }
        return usuario;
    }
    
    public void setUsuario(Usuario u) {
        usuario = u;
    }

}
