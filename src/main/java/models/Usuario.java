package models;

import exceptions.ValidationModelException;

/**
 *
 * Contiene información personal del usuario de la sesión global
 *
 * @author Raul
 */
public class Usuario {

    private int id;
    private String username;
    private String nombre;
    private String password;
    private Rol rol;

    public int getId() {
        return id;
    }

    public void setId(int id) throws ValidationModelException {
        if (id < 0) {
            throw new ValidationModelException("El id debe ser unsigned");
        }
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws ValidationModelException {
        if (username == null || !username.matches("^[a-zA-Z0-9_]{4,}$")) {
            throw new ValidationModelException("El username deber ser sin caracteres especiales minimo 4 caracteres");
        }
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ValidationModelException {
        if (nombre == null || !nombre.matches("^(?!\\s*$)(?!.*[^a-zñáéíóúA-ZÑÁÉÍÓÚ0-9 \\s]).{2,}$")) {
            throw new ValidationModelException("el nombre no debe contener caracteres especiales  ");
        }
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws ValidationModelException {
        if (password == null || password.isEmpty() || password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
            throw new ValidationModelException("La contraseña debe ser de al menos 8 caracteres");
        }
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
