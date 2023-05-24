/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Raul
 */
public class ProductoVenta extends Producto {

    private long stock;

    public ProductoVenta() {
    }

    public ProductoVenta(long id, int proveedorId, String nombre,
            String codigoBarras, double precioPublico, double costo,
            String categoria, String marca, int stock) {
        super(id, proveedorId, nombre, codigoBarras, precioPublico, costo, categoria);
        this.stock = stock;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return super.toString() + ", ProductoVenta{" + "stock=" + stock + '}';
    }

}
