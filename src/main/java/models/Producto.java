package models;

import exceptions.ValidationModelException;

public class Producto {
    
    private int id;
    private int codigoBarras;
    private int proveedorId;
    private String nombre;
    private String descripcion;
    private double precio;

    public int getId() {
        return id;
    }

    public void setId(int id) throws ValidationModelException {
        if (id < 0) {
            throw new ValidationModelException("El id del producto debe ser positivo");
        }
        this.id = id;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) throws ValidationModelException {
        if (proveedorId < 0) {
            throw new ValidationModelException("El id '" + proveedorId + "' del proveedor debe ser positivo");
        }
        this.proveedorId = proveedorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ValidationModelException {
        String regex = "^(?=.{2,})([a-zA-Z0-9ñÑáÁéÉíÍóÓúÚ]+(\\s+[a-zA-Z0-9ÑñáÁéÉíÍóÓúÚ]+)*)$";
        if (nombre == null || !nombre.matches(regex)) {
            throw new ValidationModelException("El nombre'" + nombre + "' del producto debe contener al menos 2 letras o numeros");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) throws ValidationModelException {
        String regex = "^(?=.{2,})([a-zA-Z0-9ÑñáÁéÉíÍóÓúÚ]+(\\s+[a-zA-Z0-9ÑñáÁéÉíÍóÓúÚ]+)*)$";
        if (descripcion == null || !descripcion.matches(regex)) {
            throw new ValidationModelException("La descripcion '" + descripcion + "' del producto debe contener al menos 2 letras o numeros");
        }
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws ValidationModelException {
        if (precio <= 0) {
            throw new ValidationModelException("El precio '" + precio + "' debe ser mayor a 0");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", nombre: " + nombre + ", descripcion: " + descripcion + ", precio: " + precio + ", proveedor_id: " + proveedorId + "}";
    }
}
