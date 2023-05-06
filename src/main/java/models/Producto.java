package models;

import exceptions.ValidationModelException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Producto {

    private int id;
    private int proveedorId;
    private String nombre;
    private String codigoBarras;
    private double precioPublico;
    private double costo;
    private LocalDate fechaCaducidad;
    private String categoria;
    private String marca;
    private String edicion;

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
        ensureValidText(nombre, "nombre");
        this.nombre = nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) throws ValidationModelException {
        if (!codigoBarras.matches("^(\\d{8}|\\d{12}|\\d{13})$")) {
            throw new ValidationModelException("El codigo de barras '" + codigoBarras + "' no cumple el estandar EAN-13 o UPC-A");
        }

        this.codigoBarras = codigoBarras;
    }

    public double getPrecioPublico() {
        return precioPublico;
    }

    public void setPrecioPublico(double precioPublico) throws ValidationModelException {
        if (precioPublico <= 0) {
            throw new ValidationModelException("El precio '" + precioPublico + "' debe ser mayor a 0");
        }

        DecimalFormat df = new DecimalFormat("#.##");
        this.precioPublico = Double.parseDouble(df.format(precioPublico));
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) throws ValidationModelException {
        if (costo <= 0) {
            throw new ValidationModelException("El costo '" + costo + "' debe ser mayor a 0");
        }

        DecimalFormat df = new DecimalFormat("#.##");
        this.costo = Double.parseDouble(df.format(costo));
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public String getFechaCaducidadFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaCaducidad.format(formatter);
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws ValidationModelException {
        ensureValidText(categoria, "categoria");
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) throws ValidationModelException {
        ensureValidText(marca, "marca");
        this.marca = marca;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) throws ValidationModelException {
        ensureValidText(edicion, "edicion");
        this.edicion = edicion;
    }

    private void ensureValidText(String t, String prop) throws ValidationModelException {
        String regex = "^(?!\\s*$)(?!.*[^a-zñáéíóúA-ZÑÁÉÍÓÚ0-9 \\s]).{2,}$";
        if (t == null || !t.matches(regex)) {
            throw new ValidationModelException(prop + " '" + t + "' del producto debe contener al menos 2 letras o numeros sin caracteres especiales");
        }
    }
}
