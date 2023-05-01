package models;

/**
 *
 * Contiene información personal del usuario de la sesión global
 * 
 * @author Raul
 */
public class Usuario {

    private String username;
    private String nombre;
    private String apellidos;

    public Usuario(String username, String nombre, String apellidos) {
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

}
