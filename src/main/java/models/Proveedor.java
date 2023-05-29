package models;

import exceptions.ValidationModelException;

/**
 *
 * @author ili
 */
public class Proveedor {

    private long id;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(long id, String nombre, String direccion, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) throws ValidationModelException {
        if (id < 0) {
            throw new ValidationModelException("El id del producto debe ser positivo");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ValidationModelException {
        NombreValido(nombre);
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) throws ValidationModelException {
        DireccionValida(direccion);
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidationModelException {
        EmailValido(email);
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws ValidationModelException {
        TelefonoValido(telefono);
        this.telefono = telefono;
    }

    public static boolean NombreValido(String nombre) throws ValidationModelException {
        if (nombre.trim().isEmpty()) {
            throw new ValidationModelException("Nombre es un dato obligatorio");
        }
        if (nombre.length() > 255) {
            throw new ValidationModelException("La nombre debe ser menor a 255 caracteres");

        }
        if (!nombre.matches("^(?!\\s*$)(?!.*[^a-zñáéíóúA-ZÑÁÉÍÓÚ0-9 \\s]).{2,}$")) {
            throw new ValidationModelException("el nombre no debe contener caracteres especiales  ");
        }
        return true;
    }

    public static boolean DireccionValida(String direccion) throws ValidationModelException {
        if (direccion.trim().isEmpty()) {
            throw new ValidationModelException("Direccion requerida");
        }
        if (direccion.length() > 255) {
            throw new ValidationModelException("La dirección debe ser menor a 255 caracteres");

        }
        if (!direccion.matches("^[\\p{L}0-9\\s#\\-'áéíóúÁÉÍÓÚ]+$")) {
            throw new ValidationModelException("La direccion no debe contener caracteres especiales, solo permite #.");
        }
        return true;
    }

    public static boolean EmailValido(String email) throws ValidationModelException {
        if (email.trim().isEmpty()) {
            throw new ValidationModelException("Email es un campo obligatorio");
        }
        if (email.length() > 40) {
            throw new ValidationModelException("La email debe ser menor a 40 caracteres");

        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new ValidationModelException("email debe contener un dominio valido");
        }
        return true;
    }

    public static boolean TelefonoValido(String telefono) throws ValidationModelException {
        if (telefono.length() != 10) {
            throw new ValidationModelException("El telefono debe contener 10 dígitos");
        }
        if (!telefono.matches("\\d+")) {
            throw new ValidationModelException("El teléfono solo debe contener números.");
        }
        return true;
    }

}
