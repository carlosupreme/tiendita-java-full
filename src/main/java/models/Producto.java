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
        esTextoValido(nombre);
        this.nombre = nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) throws ValidationModelException {
        esCodigoValido(codigoBarras);
        this.codigoBarras = codigoBarras;
    }

    public double getPrecioPublico() {
        return precioPublico;
    }

    public void setPrecioPublico(double precioPublico) throws ValidationModelException {
        esDineroValido(precioPublico);

        DecimalFormat df = new DecimalFormat("#.##");
        this.precioPublico = Double.parseDouble(df.format(precioPublico));
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) throws ValidationModelException {
        esDineroValido(costo);

        DecimalFormat df = new DecimalFormat("#.##");
        this.costo = Double.parseDouble(df.format(costo));
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws ValidationModelException {
        esTextoValido(categoria);
        this.categoria = categoria;
    }

    public static boolean esTextoValido(String t) throws ValidationModelException {
        if (t.length() > 100) {
            throw new ValidationModelException("Debe ser menor a 100 caracteres");
        }

        if (t.trim().isEmpty()) {
            throw new ValidationModelException("Es requerido");
        }

        // Valida que no sean espacios en blanco y que no contenga caracteres especiales
        if (!t.matches("^(?!\\s*$)(?!.*[^a-zñáéíóúA-ZÑÁÉÍÓÚ0-9 \\s]).{1,100}$")) {
            throw new ValidationModelException("No admite caracteres especiales");
        }

        return true;
    }

    public static boolean esCodigoValido(String codigoBarras) throws ValidationModelException {
        if (codigoBarras.trim().isEmpty()) {
            throw new ValidationModelException("El codigo de barras es requerido");
        }

        if (!codigoBarras.matches("^(\\d{8}|\\d{12}|\\d{13})$")) {
            throw new ValidationModelException("El codigo de barras debe ser de 8, 12 o 13 diigtos");
        }

        return true;
    }

    public static boolean esDineroValido(double n) throws ValidationModelException {
        if (n <= 0) {
            throw new ValidationModelException("Debe ser mayor a 0");
        }

        if (n >= Double.MAX_VALUE || n <= Double.MIN_VALUE) {
            throw new ValidationModelException("Debe ser un valor valido");
        }

        return true;
    }
}
