package models;

import exceptions.ValidationModelException;

/**
 *
 * @author ili
 */
public class Proveedor {

    private int id;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;

    public Proveedor(int id, String nombre, String direccion, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws ValidationModelException {
        if (id < 0) {
            throw new ValidationModelException("El id del producto debe ser positivo");
        }
        this.id = id;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) throws ValidationModelException {
        if (direccion == null || !direccion.matches("^(?=.*[^ \\d])[\\w#]+$")) {
            throw new ValidationModelException("La direccion debe contener al menos 2 caracteres validos ");
        }
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidationModelException {
        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new ValidationModelException("email debe contener un dominio valido");
        }
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws ValidationModelException {
        if (telefono.length() != 10) {
            throw new ValidationModelException("El telefono debe contener al menos 10 numeros");
        }
        this.telefono = telefono;
    }

}
