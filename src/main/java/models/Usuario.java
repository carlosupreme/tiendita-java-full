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

    public Usuario() {
    }

    public Usuario(int id, String username, String nombre, String password) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.password = password;
    }

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
        if (username == null || username.trim().equals("")) {
            throw new ValidationModelException("El username es requerido");
        }

        if (username.trim().length() < 4) {
            throw new ValidationModelException("El username deber tener minimo 4 caracteres");
        }

        if (!username.matches("^[a-zA-Z0-9_]{4,}$")) {
            throw new ValidationModelException("El username solo puede tener letras sin acentos, "
                    + "números y guiones bajos");
        }

        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ValidationModelException {
        if (nombre == null || nombre.trim().equals("")) {
            throw new ValidationModelException("El nombre es requerido");
        }

        if (!nombre.trim().matches("^(?!\\s*$)(?!.*[^a-zñáéíóúA-ZÑÁÉÍÓÚ0-9 \\s]).{2,}$")) {
            throw new ValidationModelException("El nombre solo puede tener letras, "
                    + "números y espacios en blanco");
        }
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws ValidationModelException {
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationModelException("La contraseña es requerida");
        }

        if (password.length() < 8) {
            throw new ValidationModelException("La contraseña debe tener al menos 8 caracteres");
        }

        if (!password.matches(".*[a-zA-Z].*")) {
            throw new ValidationModelException("La contraseña debe tener al menos una letra mayúscula y minúscula");
        }

        if (!password.matches(".*\\d.*")) {
            throw new ValidationModelException("La contraseña debe tener al menos un número");
        }
        this.password = password;
    }

}
