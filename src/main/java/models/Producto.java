package models;

import exceptions.ValidationModelException;
import java.text.DecimalFormat;

public class Producto {

    private long id;
    private long idProveedor;
    private String nombre;
    private String codigoBarras;
    private double precioPublico;
    private double costo;
    private String categoria;

    public Producto(long id, long proveedorId, String nombre, String codigoBarras, double precioPublico,
            double costo, String categoria) {
        this.id = id;
        this.idProveedor = proveedorId;
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.precioPublico = precioPublico;
        this.costo = costo;
        this.categoria = categoria;
    }

    public Producto() {

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

    public long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(long idProveedor) throws ValidationModelException {
        if (idProveedor < 0) {
            throw new ValidationModelException("El id '" + idProveedor + "' del proveedor debe ser positivo");
        }
        this.idProveedor = idProveedor;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws ValidationModelException {
        ensureValidText(categoria, "categoria");
        this.categoria = categoria;
    }

    private void ensureValidText(String t, String prop) throws ValidationModelException {
        String regex = "^(?!\\s*$)(?!.*[^a-zñáéíóúA-ZÑÁÉÍÓÚ0-9 \\s]).{2,}$";
        if (t == null || !t.matches(regex)) {
            throw new ValidationModelException(prop + " '" + t + "' del producto debe contener al menos 2 letras o numeros sin caracteres especiales");
        }
    }
}
